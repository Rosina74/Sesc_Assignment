package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IntegrationService {

    private final RestTemplate restTemplate;
    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void createAccount(Account account) {
        System.out.println("Creating account");
        restTemplate.postForObject("http://localhost:8081/accounts", account, Account.class);
    }
    public Account getStudentAccount(String studentId) {
        return restTemplate.getForObject("http://localhost:8081/accounts/student/" + studentId, Account.class);

    }
    public Invoice createCourseFeeInvoice(Invoice invoice){
        return restTemplate.postForObject("http://localhost:8081/invoices/", invoice, Invoice.class);
    }
}
