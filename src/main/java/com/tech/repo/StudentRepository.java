package com.tech.repo;

import com.tech.model.entity.Address;
import com.tech.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByAddressId(Integer addressId);
}
