package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.exceptions.StudentNotFoundException;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("stu"+studentId));
    }

    @Override
    public void deleteStudentById(Long studentId) {
        if (!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("student not found with ID: " + studentId);
        }

    }

    @Override
    public Student updateStudentById(String studentId, Student updatedStudent) {
        if (!studentRepository.existsByExternalStudentId(studentId)) {
            throw new StudentNotFoundException("student not found with ID: " + studentId);
        }
        Student student = studentRepository.findByExternalStudentId(studentId);
        updatedStudent.setId(student.getId());
        return studentRepository.save(updatedStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByEmail(String email) {
        if(studentRepository.existsStudentByEmail(email)){
            System.out.println(studentRepository.existsStudentByEmail(email));
            return studentRepository.findByEmail(email).getFirst();
        }
        else return null;
    }

    @Override
    public Student getStudentByExternalId(String externalStudentId) {
        if(studentRepository.existsByExternalStudentId(externalStudentId)){
            return studentRepository.findByExternalStudentId(externalStudentId);
        }
        else return null;
    }
}
