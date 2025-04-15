package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.CourseRepository;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseServiceImplementation  implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrolmentService enrolmentService;
    private final StudentService studentService;

    public CourseServiceImplementation(CourseRepository courseRepository,
                                       StudentRepository studentRepository,
                                       EnrolmentService enrolmentService,
                                       StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrolmentService = enrolmentService;
        this.studentService = studentService;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void enrollStudent(Long course_id, String student_id, String email) {
        Course course = courseRepository.findById(course_id).get();
        if(studentRepository.existsStudentByEmail(email)){
            Student student = studentRepository.findByEmail(email).getFirst();
            enrolmentService.enrolStudentInCourse(student, course);
            studentService.saveStudent(student);
        }
        else System.out.println("Student not found" + email);
    }
}
