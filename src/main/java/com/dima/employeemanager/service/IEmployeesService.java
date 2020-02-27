package com.dima.employeemanager.service;

import com.dima.employeemanager.exceptions.ApplicationException;
import com.dima.employeemanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeesService {
    Employee save(Employee employee) throws ApplicationException;

    List<Employee> getAllEmployees() throws  ApplicationException;

    Optional<Employee> getEmployeeById(long ID) throws ApplicationException;

    void deleteEmployeeByID(long ID) throws ApplicationException;
}
