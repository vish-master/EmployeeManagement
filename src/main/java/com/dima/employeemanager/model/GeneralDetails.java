package com.dima.employeemanager.entities;

import javax.persistence.*;

@Entity
@Table(name = "GeneralDetails")
public class GeneralDetails {

    @Id
    private int ID;

    @Column
    private int age;

    @OneToOne
    private Employee employee;

    public GeneralDetails() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
