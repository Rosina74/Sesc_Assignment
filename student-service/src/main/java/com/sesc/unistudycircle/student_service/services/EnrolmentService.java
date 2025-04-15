package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EnrolmentService {

    private final StudentRepository studentRepository;
    private  final IntegrationService integrationService;

    public EnrolmentService(StudentRepository studentRepository, IntegrationService integrationService) {
        this.studentRepository = studentRepository;
        this.integrationService = integrationService;
    }

    public void enrolStudentInCourse(Student student, Course course) {
        student.enrollCourse(course);
        studentRepository.save(student);

        Account account = new Account();
        account.setStudentId(student.getExternalStudentId());

        Invoice invoice = new Invoice();
        invoice.setAccount(account);
        invoice.setType(Invoice.Type.TUITION_FEES);
        invoice.setAmount(course.getFee());
        invoice.setDueDate(LocalDate.now());

        integrationService.createAccount(account);
        integrationService.createCourseFeeInvoice(invoice);
    }
}
