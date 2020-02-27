package com.dima.employeemanager.dao;

import com.dima.employeemanager.model.Spouse;
import org.springframework.data.repository.CrudRepository;

public interface ISpousesDao extends CrudRepository <Spouse, Long> {
}
