package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "first name cannot be empty.")
    private String firstName;
    @NotEmpty(message = "last name cannot be empty.")
    private String lastName;
    @Transient
    private String fullName;
    @NotEmpty(message = "password cannot be empty.")
    private String password;
    @Email(message = "Email must be  enter.")
    private String email;
    @Transient
    private String confirmPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();

    public User(@NotEmpty(message = "first name cannot be empty.") String firstName, @NotEmpty(message = "last name cannot be empty.") String lastName, @NotEmpty(message = "password cannot be empty.") String password, @Email(message = "Email must be  enter.") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void addRoles(List<Role> roles){
        roles.forEach(this::addRole);
    }
}
