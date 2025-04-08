package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.exceptions.StudentNotFoundException;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository repository;
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository repository, StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return repository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("stu"+studentId));
    }

    @Override
    public void deleteStudentById(Long studentId) {
        if (!repository.existsById(studentId)){
            throw new StudentNotFoundException("student not found with ID: " + studentId);
        }

    }

    @Override
    public Student updateStudentById(long studentId, Student updatedStudent) {
        if (!repository.existsById(studentId)) {
            throw new StudentNotFoundException("student not found with ID: " + studentId);
        }
        updatedStudent.setId(studentId);
        return repository.save(updatedStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByIdJson(long id) {
        return null;
    }
}
