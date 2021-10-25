package com.github.korenevaS.queue.service;

import com.github.korenevaS.queue.repository.RoleRepository;
import com.github.korenevaS.queue.repository.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role create(Role someRole) {
        Role repositoryRole = new Role();
        repositoryRole.setId(someRole.getId());
        repositoryRole.setName(someRole.getName());
        return roleRepository.save(repositoryRole);
    }
}
