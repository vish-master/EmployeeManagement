package com.dima.employeemanager.api;

import com.dima.employeemanager.exceptions.ApplicationException;
import com.dima.employeemanager.model.*;
import com.dima.employeemanager.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeesApi {

    @Autowired
    private EmployeesService employeesService;

    @PostMapping
    public void createEmployee( @RequestBody Employee employee) throws ApplicationException {
        this.employeesService.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) throws ApplicationException {
        employee.setId(id);
        return ResponseEntity.ok().body(this.employeesService.updateEmployee(employee));
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = this.employeesService.getAllEmployees();

        return new ResponseEntity<List<Employee>>(employeeList, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws ApplicationException {
       Optional<Employee> employee = this.employeesService.getEmployeeById(id);

       return  ResponseEntity.ok(employee.get());
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable Long id) throws ApplicationException{

        this.employeesService.deleteEmployeeByID(id);
        return HttpStatus.FORBIDDEN;
    }
    @GetMapping("/getEmployeeGeneralDetails/{id}")
    public ResponseEntity<GeneralDetails> getEmployeeGeneralDetails(@PathVariable Long id) throws ApplicationException{

        GeneralDetails generalDetails = this.employeesService.getEmployeeGeneralDetails(id);

        return new ResponseEntity<GeneralDetails>(generalDetails, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/getEmployeeSpouse/{id}")
    public ResponseEntity<Spouse> getEmployeeSpouse(@PathVariable Long id) throws ApplicationException{

       Spouse spouse = this.employeesService.getEmployeeSpouse(id);

        return new ResponseEntity<Spouse>(spouse, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getEmployeeChildren/{id}")
    public ResponseEntity<List<Child>> getEmployeeChildren(@PathVariable Long id) throws ApplicationException{

        List<Child> children = this.employeesService.getAllEmployeeChildren(id);

        return new ResponseEntity<List<Child>>(children, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getEmployeeAddresses/{id}")
    public ResponseEntity<List<Address>> getEmployeeAddresses(@PathVariable Long id) throws ApplicationException{

        List<Address> addresses = this.employeesService.getAllEmployeeAddresses(id);

        return new ResponseEntity<List<Address>>(addresses, new HttpHeaders(), HttpStatus.OK);
    }

}



