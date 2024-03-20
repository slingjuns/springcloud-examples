package com.example.management.controllers;

import com.example.management.entities.Department;
import com.example.management.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.management.services.HibernateService;

@RestController
@RequestMapping("/hibernate")
public class HibernateController {
    private final HibernateService hibernateService;

    @Autowired
    public HibernateController(HibernateService hibernateService){
        this.hibernateService = hibernateService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Long> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(hibernateService.createEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(hibernateService.findEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("/employees/")
    public ResponseEntity<Long> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(hibernateService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        hibernateService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/departments/{id}")
    public Department findDepartmentById(@PathVariable  Long id){
        return this.hibernateService.findDepartmentById(id);
    }

    @PostMapping("/departments/")
    public Long createDepartment(@RequestBody Department department){
        return this.hibernateService.createDepartment(department);
    }

    @PutMapping("/departments/")
    public Long updateDepartment(@RequestBody Department department){
        return this.hibernateService.updateDepartment(department);
    }
}
