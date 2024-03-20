package com.example.management.controllers;

import com.example.management.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.management.services.JPAService;

@RestController
@RequestMapping("/jpa")
public class JpaController {
    private final JPAService jpaService;

    @Autowired
    public JpaController(JPAService jpaService){
        this.jpaService = jpaService;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id){
        return new ResponseEntity<>(this.jpaService.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Long> createNewEmployee(@RequestBody Employee e){
        return new ResponseEntity<>(this.jpaService.createEmployee(e), HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<Long> updateEmployee(@RequestBody Employee e){
        return new ResponseEntity<>(this.jpaService.updateEmployee(e), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        this.jpaService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
