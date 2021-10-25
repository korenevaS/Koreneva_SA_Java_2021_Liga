package com.github.korenevaS.queue.repository;

import com.github.korenevaS.queue.repository.model.Role;
import com.github.korenevaS.queue.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
