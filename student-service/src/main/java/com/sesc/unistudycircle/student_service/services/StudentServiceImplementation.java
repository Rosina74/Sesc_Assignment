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
// save student in database
    @Override
    public Student saveStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        System.out.println(studentRepository.findAll() + "all students crteated");
        return savedStudent;//studentRepository.save(student);

    }

//get student by Id
    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("stu"+studentId));
    }
// Deleting Student by ID
    @Override
    public void deleteStudentById(Long studentId) {
        if (!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("student not found with ID: " + studentId);
        }

    }
// Updating Student by studentId
    @Override
    public Student updateStudentById(String studentId, Student updatedStudent) {
        if (!studentRepository.existsByExternalStudentId(studentId)) {
            throw new StudentNotFoundException("student not found with ID: " + studentId);
        }
        //if student is found update the student
        System.out.println("attempting to update student with ID: " + studentId);
        Student student = studentRepository.findByExternalStudentId(studentId);
        if(student != null){
            System.out.println("finally updating student with ID: " + studentId);
            updatedStudent.setId(student.getId());
            return studentRepository.save(updatedStudent);
        }
        System.out.println("failed to update student with ID:. throwinf new erro " + studentId);
        throw new StudentNotFoundException("student not found with ID: " + studentId);
    }
// get all student from database
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

//get student by email if the student exist in the database
    @Override
    public Student getStudentByEmail(String email) {
        if(studentRepository.existsStudentByEmail(email)){
            return studentRepository.findByEmail(email).getFirst();
        }
        else return null;
    }
//get student by externel id if the student exist in the database
    @Override
    public Student getStudentByExternalId(String externalStudentId) {
        if(studentRepository.existsByExternalStudentId(externalStudentId)){
            return studentRepository.findByExternalStudentId(externalStudentId);
        }
        else return null;
    }
}
