package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Component
public class IntegrationService {

    private final RestClient restClient;

    public IntegrationService(RestClient restClient, RestTemplate restTemplate) {
        this.restClient = restClient;
    }


    public Account createAccount(Account account) {
         return restClient.post()
                .uri("http://localhost:8081/accounts")
                 .body(account)
                 .retrieve()
                 .body(Account.class);
    }
    public Account getAccount(String externalStudentId) {
        try{
            return restClient.get()
                    .uri("http://localhost:8081/accounts/student/"+ externalStudentId)
                    .retrieve()
                    .body(Account.class);
        }
        catch(Exception e){
            return null;
        }

    }
    public Invoice createCourseFeeInvoice(Invoice invoice){
        return restClient.post()
                .uri("http://localhost:8081/invoices/")
                .body(invoice)
                .retrieve()
                .body(Invoice.class);
    }

    public Invoice getInvoiceById(@PathVariable String reference) {
        System.out.println(reference);
         return restClient.get()
                .uri("http://localhost:8081/invoices/reference/"+ reference)
                .retrieve()
                .body(Invoice.class);
    }
}
