package com.dima.employeemanager;

import com.dima.employeemanager.dao.IEmployeesDao;
import com.dima.employeemanager.exceptions.ApplicationException;
import com.dima.employeemanager.model.*;
import com.dima.employeemanager.service.EmployeesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeemanagerApplicationTests {

    @Mock
    private IEmployeesDao employeesDao;

    @InjectMocks
    private EmployeesService employeesService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveEmployee() throws ApplicationException {
        Employee employee = createEmployeeObject();
        when(employeesDao.save(employee)).thenReturn(employee);
        Employee result = employeesService.save(employee);
        assertEquals(employee.getGeneralDetails(), result.getGeneralDetails());
        assertEquals(employee.getAddresses(), result.getAddresses());
        assertEquals(employee.getChildren(), result.getChildren());
        assertEquals(employee.getSpouse(), result.getSpouse());

    }


    @org.junit.Test
    public void getEmployeeById() throws ApplicationException {
        Employee employee = createEmployeeObject();
        when(employeesDao.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> result = employeesService.getEmployeeById(1);
        assertEquals(employee.getGeneralDetails(), result.get().getGeneralDetails());
        assertEquals(employee.getAddresses(), result.get().getAddresses());
        assertEquals(employee.getChildren(), result.get().getChildren());
        assertEquals(employee.getSpouse(), result.get().getSpouse());
    }

    @org.junit.Test
    public void getAllEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        Employee employee = createEmployeeObject();
        employees.add(employee);
        when(employeesDao.findAll()).thenReturn(employees);

        List<Employee> result = employeesService.getAllEmployees();
        assertEquals(1, result.size());
    }

    @Test
    public void deleteEmployeeByID() throws ApplicationException {
        Employee employee = createEmployeeObject();
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeesDao.findById(1L)).thenReturn(optionalEmployee);

        employeesService.deleteEmployeeByID(employee.getId());


        verify(employeesDao, Mockito.times(1)).deleteById(employee.getId());
    }



    public Employee createEmployeeObject(){
        Employee employee = new Employee();
        employee.setId(1);
        List<Address> addresses = new ArrayList<Address>();
        List<Child> children = new ArrayList<>();
        GeneralDetails generalDetails = new GeneralDetails();
        generalDetails.setFirstName("TestFirstName");
        generalDetails.setLastName("TestLastName");
        generalDetails.setAge(18);
        Spouse spouse = new Spouse();
        spouse.setId(1);
        spouse.setAge(22);
        spouse.setName("TestSpouse");
        spouse.setEmployee(employee);
        Address address = new Address();
        address.setCity("TestCity");
        address.setStreet("TestStreet");
        address.setNumber(1);
        address.setEmployee(employee);
        address.setId(0);
        addresses.add(address);
        Child child = new Child();
        child.setName("TestName");
        child.setAge(2);
        child.setEmployee(employee);
        child.setId(0);
        children.add(child);

        employee.setGeneralDetails(generalDetails);
        employee.setChildren(children);
        employee.setAddresses(addresses);
        employee.setSpouse(spouse);

        return employee;
    }
}