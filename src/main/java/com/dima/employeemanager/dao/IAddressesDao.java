package com.dima.employeemanager.dao;

import com.dima.employeemanager.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface IAddressesDao extends CrudRepository<Address, Long> {
}
