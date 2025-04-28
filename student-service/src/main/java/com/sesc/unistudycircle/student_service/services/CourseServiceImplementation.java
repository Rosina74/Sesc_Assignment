package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
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

    // getting all courses
    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

// get course by Id
    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

//creating course
    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

//enroll student
    @Override
    public Invoice enrollStudent(Long course_id, String student_id, String email) {
        //find course by course_id
        Course course = courseRepository.findById(course_id).get();

        // if the student exist in the database
        // save student and invoice
        if(studentRepository.existsStudentByEmail(email)){
            Student student = studentRepository.findByEmail(email).getFirst();
            Invoice invoice = enrolmentService.enrolStudentInCourse(student, course);
            studentService.saveStudent(student);
            return invoice;
        }
        else return null;
    }
// Get enrolled Course by student email
    @Override
    public List<Course> getEnrolledCourseByStudentEmail(String email) {
        // if student exist in the database, find courses enrolled
        Student student = studentRepository.findByEmail(email).getFirst();
        return new ArrayList<>(student.getCoursesEnrolledIn());
    }
}
