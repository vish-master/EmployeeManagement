package com.dima.employeemanager.service;

import com.dima.employeemanager.dao.IEmployeesDao;
import com.dima.employeemanager.dao.ISpousesDao;
import com.dima.employeemanager.enums.ErrorType;
import com.dima.employeemanager.exceptions.ApplicationException;
import com.dima.employeemanager.model.*;
import com.dima.employeemanager.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("EmployeeService")
public class EmployeesService implements IEmployeesService {

    @Autowired
    private IEmployeesDao employeesDao;

    @Autowired
    private ISpousesDao spousesDao;

    @Override
    public Employee save(Employee employeeEntity) throws ApplicationException {

             validateEmployee(employeeEntity);

             Employee savedEmployeeEntity = this.employeesDao.save(employeeEntity);

            return savedEmployeeEntity;
        }

    @Transactional
    public Employee updateEmployee(Employee newEmployee) throws ApplicationException {
        Optional<Employee> employee = this.employeesDao.findById(newEmployee.getId());
        List<Child> children = newEmployee.getChildren();
        List<Address> addresses = newEmployee.getAddresses();
        if (employee.isPresent()) {
            Employee employeeUpdate = employee.get();
            employeeUpdate.getAddresses().clear();
            employeeUpdate.getAddresses().addAll(addresses);
            employeeUpdate.getChildren().clear();
            employeeUpdate.getChildren().addAll(children);
            newEmployee.getSpouse().setId(employeeUpdate.getId());
            employeeUpdate.setSpouse(newEmployee.getSpouse());
            employeeUpdate.setGeneralDetails(newEmployee.getGeneralDetails());
            validateEmployee(employeeUpdate);
            this.employeesDao.save(employeeUpdate);
            return employeeUpdate;
        } else {
            throw new ApplicationException(ErrorType.EMPLOYEE_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.updateEmployee(), There is no employee with inserted id.");


        }
    }



    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = (List<Employee>) this.employeesDao.findAll();

        if(employees.size() > 0) {
            return employees;
        } else {
            return new ArrayList<Employee>();
        }

    }

    @Override
    public Optional<Employee> getEmployeeById(long id)throws ApplicationException {

        Optional<Employee> employee = this.employeesDao.findById(id);

        if(employee.isPresent()) {
            return employee;
        } else {
            throw new ApplicationException(ErrorType.EMPLOYEE_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.deleteEmployeeByID(), There is no employee with inserted id.");
        }



    }

    @Override
    public void deleteEmployeeByID(long id) throws ApplicationException{
        Optional<Employee> employee = this.employeesDao.findById(id);

        if(employee.isPresent())
        {
            this.employeesDao.deleteById(id);
        } else {
            throw new ApplicationException(ErrorType.EMPLOYEE_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.deleteEmployeeByID(), There is no employee with inserted id.");
        }
    }

    public GeneralDetails getEmployeeGeneralDetails(Long id) throws ApplicationException {
        Optional<Employee> employee = this.getEmployeeById(id);
        GeneralDetails generalDetails = employee.get().getGeneralDetails();

        if(generalDetails != null){

        }else{
            throw new ApplicationException(ErrorType.EMPLOYEE_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.getEmployeeGeneralDetails(), There is no generalDetails with inserted id.");
        }

        return generalDetails;

    }


    public Spouse getEmployeeSpouse(Long id) throws ApplicationException {
        Optional<Employee> employee = this.getEmployeeById(id);
        Spouse spouse = employee.get().getSpouse();

        if(spouse != null){

        }else{
            throw new ApplicationException(ErrorType.EMPLOYEE_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.getEmployeeSpouse(), There is no Spouse with inserted id.");
        }

        return spouse;

    }


    public List<Child> getAllEmployeeChildren(Long id) throws ApplicationException {
        Optional<Employee> employee = this.getEmployeeById(id);
        List<Child> children = new ArrayList<Child>();
        children = employee.get().getChildren();



        if(children.size() > 0){

        }else{
            throw new ApplicationException(ErrorType.EMPLOYEE_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.getAllEmployeeChildren(), There is no children with inserted id.");
        }

        return children;

    }

    public List<Address> getAllEmployeeAddresses(Long id) throws ApplicationException {
        Optional<Employee> employee = this.getEmployeeById(id);
        List<Address> addresses = new ArrayList<Address>();
        addresses = employee.get().getAddresses();



        if(addresses.size() > 0){

        }else{
            throw new ApplicationException(ErrorType.EMPLOYEE_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.getAllEmployeeAddresses(), There is no relevant addresses to employee with inserted id.");
        }

        return addresses;

    }

    private void validateEmployee(Employee employee) throws ApplicationException {

        if (employee == null) {

            throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.validateEmployee(), You must insert an Employee");		}

        if (employee.getGeneralDetails().getFirstName().isEmpty()) {
            throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.validateEmployee(), You must insert a firstName of Employee");
        }

        if (employee.getGeneralDetails().getLastName().isEmpty()) {
            throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.validateEmployee(), You must insert a lastName of Employee");
        }

        if (employee.getGeneralDetails().getAge() <= 0) {
            throw new ApplicationException(ErrorType.INVALID_AGE, DateUtils.getCurrentDateAndTime()
                    + "Error in employeeService.validateEmployee(), You must insert correct age value");
        }

        if(employee.getSpouse() != null){
            if (employee.getSpouse().getName().isEmpty()){
                throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                        + "Error in employeeService.validateEmployee(), You must insert a Name of Spouse");
            }

            if (employee.getSpouse().getAge() <= 0){
                throw new ApplicationException(ErrorType.INVALID_AGE, DateUtils.getCurrentDateAndTime()
                        + "Error in employeeService.validateEmployee(), You must insert correct age value");
            }
        }

        if (employee.getAddresses().size() > 0){
            for (Address address: employee.getAddresses()) {
                if(address.getCity().isEmpty()){
                    throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                            + "Error in employeeService.validateEmployee(), You must insert a Name of City:" + address.getCity());
                }

                if(address.getStreet().isEmpty()){
                    throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                            + "Error in employeeService.validateEmployee(), You must insert a Name of Street:" + address.getStreet());
                }

                if(address.getNumber() <= 0 ){
                    throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                            + "Error in employeeService.validateEmployee(), You must insert correct house number:" + address.getNumber());
                }
            }
        }

        if(employee.getChildren().size() > 0){
            for (Child child: employee.getChildren()) {
                if(child.getName().isEmpty()){
                    throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                            + "Error in employeeService.validateEmployee(), You must insert a Name of Child:" + child.getName());
                }

                if (child.getAge() <= 0){
                    throw new ApplicationException(ErrorType.INVALID_AGE, DateUtils.getCurrentDateAndTime()
                            + "Error in employeeService.validateEmployee(), You must insert correct age value:" + child.getAge());
                }
            }
        }

    }


}
