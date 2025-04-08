package com.sesc.unistudycircle.student_service.entities;

import lombok.Data;

@Data
public class Account {
    private String id;
    private String studentId;
    private boolean hasOutstandingBalance;
}
