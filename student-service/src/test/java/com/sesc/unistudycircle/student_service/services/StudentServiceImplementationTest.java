package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplementationTest {
@Mock
private StudentRepository studentRepository;

@InjectMocks
private StudentServiceImplementation studentServiceImplementation;

private Student student;
    @BeforeEach
    void setUp() {
        student = new Student();
        student.setFirstName("Roku");
        student.setEmail("rojina@gmail.com");
        student.setLastName("Rai");
        student.setPassword("123456");
        student.setExternalStudentId("c1234");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student student1 = studentServiceImplementation.saveStudent(student);
        assertNotNull(student1);
    }

    @Test
    void updateStudentById() {
        Student newstudent = new Student();
        newstudent.setFirstName("Rojina");

        when(studentRepository.existsByExternalStudentId(student.getExternalStudentId())).thenReturn(true);
        when(studentRepository.findByExternalStudentId(student.getExternalStudentId())).thenReturn(newstudent);
        when(studentRepository.save(any(Student.class))).thenReturn(newstudent);

        Student student1 = studentServiceImplementation.
                updateStudentById(student.getExternalStudentId(), newstudent);

        assertNotNull(student1);
    }

    @Test
    void getStudentByExternalId() {
        when(studentRepository.existsByExternalStudentId(student.getExternalStudentId())).thenReturn(true);
        when(studentRepository.findByExternalStudentId(student.getExternalStudentId())).thenReturn(student);

        Student student1 = studentServiceImplementation.getStudentByExternalId(student.getExternalStudentId());
        assertNotNull(student1);
    }
}