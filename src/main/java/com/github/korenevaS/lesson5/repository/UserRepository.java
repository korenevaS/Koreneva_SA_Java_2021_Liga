package com.github.korenevaS.lesson5.repository;

import com.github.korenevaS.lesson5.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository {
    List<User> findAllBySchoolId(Integer schoolId, Sort sort);
}
