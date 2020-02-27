package com.dima.employeemanager.model;

import javax.persistence.*;

@Embeddable
public class GeneralDetails {


    private int age;

    private String firstName;

    private String lastName;

    public GeneralDetails() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
