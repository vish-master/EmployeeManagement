package com.dima.employeemanager.entities;

import javax.persistence.*;

@Entity
@Table(name = "Spouses")
public class Spouse {

    @Id
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne
    private Employee employee;

    public Spouse() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
