package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{author.name.validator.msg}")
    private String name;
    @Past(message = "Date of Birth must be Past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "Please enter something")
    private String interested;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Author(String name, LocalDate dateOfBirth, String interested, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.interested = interested;
        this.gender = gender;
    }
}
