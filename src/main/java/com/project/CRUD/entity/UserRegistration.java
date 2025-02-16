package com.project.CRUD.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "UserRegistration")
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public Integer getDate() {
        return Date;
    }

    public void setDate(Integer date) {
        Date = date;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getStrongPassword() {
        return StrongPassword;
    }

    public void setStrongPassword(String strongPassword) {
        StrongPassword = strongPassword;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public UserRegistration(String user, Integer date, String password, String strongPassword, String firstName, String lastName, String email) {

        User = user;
        Date = date;
        Password = password;
        StrongPassword = strongPassword;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
    }

    private  String User;

    private Integer Date;

    private String Password;

    private String StrongPassword;

    private String FirstName;

    private String LastName;

    private String Email;

    public UserRegistration(){

    }

}
