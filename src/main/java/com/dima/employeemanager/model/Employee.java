package com.dima.employeemanager.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")

public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated employee ID")
    private long id;


    @Embedded
    @ApiModelProperty(notes = "The General Details about Employee")
    private GeneralDetails generalDetails;

    @OneToOne(mappedBy = "employee",cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "Employee's spouse")
    private Spouse spouse;


    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true)
    @ApiModelProperty(notes = "List of all employee's addresses")
    private List<Address> addresses;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @ApiModelProperty(notes = "List of all employee's children")
    private List<Child> children;

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long empId) {
        this.id = empId;
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
        if (spouse == null) {
            if (this.spouse != null) {
                this.spouse.setEmployee(null);
            }
        }
        else {
            spouse.setEmployee(this);
        }
        this.spouse = spouse;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {

        for (Address address:addresses) {
            address.setEmployee(this);
        }

        this.addresses = addresses;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        for (Child child:children) {
            child.setEmployee(this);
        }

        this.children = children;
    }
}
