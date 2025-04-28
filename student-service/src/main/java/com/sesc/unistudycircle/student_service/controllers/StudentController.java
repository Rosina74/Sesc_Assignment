package com.sesc.unistudycircle.student_service.controllers;

import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.services.IntegrationService;
import com.sesc.unistudycircle.student_service.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class  StudentController {

    private final IntegrationService integrationService;
    private final StudentService studentService;
    public StudentController(IntegrationService integrationService, StudentService studentService) {
        this.integrationService = integrationService;
        this.studentService = studentService;
    }


//this is used to register student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
// this is used to update student
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable String studentId,
            @RequestBody Student updatedstudent) {
        Student student = studentService.updateStudentById(studentId, updatedstudent);
        if (student == null) {
            System.out.println("Student not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Student was not updated ::::  " + student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
//this is used to delete
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //find student by email
    @PostMapping("/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email, @RequestParam String password) {
        Student student = studentService.getStudentByEmail(email);//student
        if (student != null) {
            if (student.getPassword().equals(password)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        else {
            System.out.println("Student was not found in email's");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
// find student by email or get student invoice

    @GetMapping("/{emailorinvoiceref}")
    public ResponseEntity<Object> getStudentByExternal(@PathVariable String emailorinvoiceref) {
        // checking if string is an email
        if(emailorinvoiceref.contains(".com"))
        {
            //check the student have that email or not
            Student student = studentService.getStudentByEmail(emailorinvoiceref);
            if (student != null) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        else {
            Invoice invoiceStatus = integrationService.getInvoiceById(emailorinvoiceref);
            return new ResponseEntity<>(invoiceStatus,HttpStatus.OK);
        }
    }
}

