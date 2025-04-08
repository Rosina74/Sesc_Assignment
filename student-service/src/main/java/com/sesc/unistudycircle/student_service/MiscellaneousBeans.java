package com.sesc.unistudycircle.student_service;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import com.sesc.unistudycircle.student_service.services.EnrolmentService;
import com.sesc.unistudycircle.student_service.services.IntegrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Set;

//@Configuration
public class MiscellaneousBeans {
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
//    @Bean
//    CommandLineRunner initDatabase(StudentRepository studentRepository, EnrolmentService enrolmentService, IntegrationService integrationService) {
//        return args -> {
//
//            Course secs = new Course();
//            secs.setTitle("secs");
//            secs.setDescription("secs");
//            secs.setFee(200.0);
//
//            Student rojina = new Student();
//
//            rojina.setForename("rojina");
//            rojina.setSurname("rojina");
//            rojina.setExternalStudentId("c704511");
//            rojina.enrollCourse(secs);
//
//            Student duncan = new Student();
//            duncan.setForename("Duncan");
//            duncan.setSurname("Mullier");
//            duncan.setExternalStudentId("c0223");
//            duncan.enrollCourse(secs);
//
//            studentRepository.saveAllAndFlush(Set.of(rojina, duncan));
//            Account roze = new Account();
//            roze.setStudentId(duncan.getExternalStudentId());
//            roze.setHasOutstandingBalance(true);
//
//            integrationService.createAccount(roze);
//
//            Invoice invoice = new Invoice();
//            invoice.setAmount(234.0);
//            invoice.setDueDate(LocalDate.of(2020, 1, 1));
//            invoice.setType(Invoice.Type.TUITION_FEES);
//            invoice.setAccount(roze);
//            invoice.setReference("roz invoice!");
//
//            enrolmentService.enrolStudentInCourse(rojina, secs, invoice);
//        };
//    }
//
//
}
