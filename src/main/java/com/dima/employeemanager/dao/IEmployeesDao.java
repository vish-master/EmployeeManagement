package com.dima.employeemanager.dao;

import com.dima.employeemanager.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeesDao  extends CrudRepository<Employee, Long> {
}
