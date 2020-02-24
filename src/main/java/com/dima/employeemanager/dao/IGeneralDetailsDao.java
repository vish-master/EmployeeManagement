package com.dima.employeemanager.dao;

import com.dima.employeemanager.model.GeneralDetails;
import org.springframework.data.repository.CrudRepository;

public interface IGeneralDetails extends CrudRepository<GeneralDetails, Long> {
}
