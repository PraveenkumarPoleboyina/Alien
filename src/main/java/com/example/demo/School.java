package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class School {

    @Id
    private Long id;
    private String fullname;
    private String jobtitle;
    private String email;
    private String salary;
    private String gender;

    public School() {}

    public School(Long id, String fullname,String jobtitle,String email, String salary, String gender) {
        this.id = id;
        this.fullname = fullname;
        this.jobtitle = jobtitle;
        this.email = email;
        this.salary = salary;
        this.gender = gender;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
