package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    Student getStudentById(Long studentId);
   void deleteStudentById(Long studentId);
   Student updateStudentById(String studentId, Student updatedStudent);
    List<Student> getAllStudents();

    Student getStudentByEmail(String email);
    Student getStudentByExternalId(String externalStudentId);
}
