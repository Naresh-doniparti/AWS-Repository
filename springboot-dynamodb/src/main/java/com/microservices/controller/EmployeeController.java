package com.microservices.controller;

import com.microservices.model.Employee;
import com.microservices.repository.EmployeeDynamoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDynamoDBRepository employeeDynamoDBRepository;

    @GetMapping("{employeeId}/{name}")
    public ResponseEntity<Employee> get(@PathVariable String employeeId, @PathVariable String name){
        Employee employee = employeeDynamoDBRepository.get(employeeId, name);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Employee employee){
        employeeDynamoDBRepository.save(employee);
        return new ResponseEntity<String>("Data is saved successfully", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Employee employee){
        employeeDynamoDBRepository.update(employee);
        return new ResponseEntity<String>("Data is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}/{name}")
    public void delete(@PathVariable String employeeId, @PathVariable String name){
        Employee employee = employeeDynamoDBRepository.get(employeeId, name);
        employeeDynamoDBRepository.delete(employee);
    }

    @GetMapping("/dummy/{message}")
    public ResponseEntity<String> dummyCall(@PathVariable String message){
        String response = "Hello world";
        if (message != null){
            response = "Hello "+ message;
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }


}
