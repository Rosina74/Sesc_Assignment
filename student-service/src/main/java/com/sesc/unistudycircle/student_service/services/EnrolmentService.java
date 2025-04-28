package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class EnrolmentService {

    private final StudentRepository studentRepository;
    private  final IntegrationService integrationService;

    public EnrolmentService(StudentRepository studentRepository, IntegrationService integrationService) {
        this.studentRepository = studentRepository;
        this.integrationService = integrationService;
    }
//enroll student and save
    public Invoice enrolStudentInCourse(Student student, Course course) {
        student.enrollCourse(course);
        studentRepository.save(student);
        if(integrationService.getAccount(student.getExternalStudentId()) != null) {

            // get account by external student id
            Account account = integrationService.getAccount(student.getExternalStudentId());
            Invoice invoice = new Invoice();

            //give account invoice
            invoice.setAccount(account);
            invoice.setType(Invoice.Type.TUITION_FEES);
            invoice.setAmount(course.getFee());
            invoice.setDueDate(LocalDate.now());

            return integrationService.createCourseFeeInvoice(invoice);

        }

        else{
            //create account
            Account account = new Account();
            account.setStudentId(student.getExternalStudentId());
            Invoice invoice = new Invoice();
            invoice.setAccount(account);
            invoice.setType(Invoice.Type.TUITION_FEES);
            invoice.setAmount(course.getFee());
            invoice.setDueDate(LocalDate.now());

            integrationService.createAccount(account);
            return integrationService.createCourseFeeInvoice(invoice);
        }
    }

}
