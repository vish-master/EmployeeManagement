package com.dima.employeemanager.entities;

import javax.persistence.*;

@Entity
@Table(name = "Children")
public class Child {

    @Id
    @GeneratedValue
    private int ID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private int age;

    @ManyToOne
    private Employee employee;


}
