package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    Course getCourseById(Long id);
    Course createCourse(Course course);
    Invoice enrollStudent(Long course_id, String student_id, String email);

    List<Course> getEnrolledCourseByStudentEmail(String email);
}
