package com.dima.employeemanager.dao;

import com.dima.employeemanager.model.Child;
import org.springframework.data.repository.CrudRepository;

public interface IChildrenDao extends CrudRepository<Child, Long> {
}
