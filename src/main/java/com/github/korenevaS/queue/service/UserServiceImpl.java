package com.github.korenevaS.queue.service;

import com.github.korenevaS.queue.controller.model.RequestUser;
import com.github.korenevaS.queue.error.NoUserException;
import com.github.korenevaS.queue.error.UserAlreadyExistsException;
import com.github.korenevaS.queue.repository.RoleRepository;
import com.github.korenevaS.queue.repository.UserRepository;
import com.github.korenevaS.queue.repository.model.Receipt;
import com.github.korenevaS.queue.repository.model.Role;
import com.github.korenevaS.queue.repository.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in database.");
            throw new UsernameNotFoundException("User not found in database.");
        } else {
            log.info("User found in database: {}.", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Transactional
    @Override
    public User saveUser(RequestUser requestUser) {
        if(userRepository.findByUsername(requestUser.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        user.setFirstName(requestUser.getFirstName());
        user.setLastName(requestUser.getLastName());
        user.setUsername(requestUser.getUsername());
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        Role role = roleRepository.getById(requestUser.getRoleId());
        user.setRole(role);
        log.info("Saving new user {} to the database.", user.getUsername());

        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}.", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users.");
        return userRepository.findAll();
    }

    @Override
    public List<Receipt> getReceipt(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(NoUserException::new);
        return user.getReceipts();
    }


}
