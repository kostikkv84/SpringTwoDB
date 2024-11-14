package org.example.springdbtest.db2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student", schema = "public")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long  id;

        @Column(name = "firstname", nullable = false)
        private String firstname;

        @Column(name = "lastname", nullable = false)
        private String lastname;

        @Column(name = "email")
        private String email;

        @Column(name = "age", nullable = false)
        private Integer age;

    }
