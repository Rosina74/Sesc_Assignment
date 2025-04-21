package com.sesc.unistudycircle.student_service.repositories;

import com.sesc.unistudycircle.student_service.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByEmail(String email);

    boolean existsStudentByEmail(String email);
    
    boolean existsByExternalStudentId(String externalId);

    Student findByExternalStudentId(String externalStudentId);
}
