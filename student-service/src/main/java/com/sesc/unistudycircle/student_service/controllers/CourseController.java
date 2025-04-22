package com.sesc.unistudycircle.student_service.controllers;

import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins={"http://localhost:63342", "http://localhost:8081"})
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourse() {
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
    }

    @PostMapping("/enroll/course/{courseId}/student/{studentId}/email{email}")
    public ResponseEntity<Invoice> enrollInCourse(@PathVariable Long courseId, @PathVariable String studentId, @PathVariable String email) {
        Invoice invoice = courseService.enrollStudent(courseId, studentId, email);

        System.out.println(invoice);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

        @GetMapping("/viewEnrolledCourse/{studentId}/{email}")
    public ResponseEntity<List<Course>>getEnrolledCourse(@PathVariable String studentId, @PathVariable String email) {
        List<Course> enrolledCourse = courseService.getEnrolledCourseByStudentEmail(email);
        return new ResponseEntity<>(enrolledCourse, HttpStatus.OK);
    }


}
