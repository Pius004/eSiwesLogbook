package com.datican.siweslogbook.repository;

import com.datican.siweslogbook.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByMatricNumberAndPassword(Long matricNumber, String password);

    // Method to find a student by matric number only
    Optional<Student> findByMatricNumber(Long matricNumber);
}
