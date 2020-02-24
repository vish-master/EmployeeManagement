package com.dima.employeemanager.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int empId;

    @OneToOne
    private GeneralDetails generalDetails;

    @OneToOne
    private Spouse spouse;

    @OneToMany(mappedBy = "employee")
    private List<Address> addresses;

    @OneToMany(mappedBy = "employee")
    private List<Child> children;

    public Employee() {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public GeneralDetails getGeneralDetails() {
        return generalDetails;
    }

    public void setGeneralDetails(GeneralDetails generalDetails) {
        this.generalDetails = generalDetails;
    }

    public Spouse getSpouse() {
        return spouse;
    }

    public void setSpouse(Spouse spouse) {
        this.spouse = spouse;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
