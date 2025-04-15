package com.sesc.unistudycircle.student_service.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double fee;
    private String description;

    @ManyToMany(mappedBy = "coursesEnrolledIn", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Student> studentsEnrolledInCourse = new HashSet<>();

}
