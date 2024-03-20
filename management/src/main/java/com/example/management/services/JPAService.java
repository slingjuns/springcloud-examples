package com.example.management.services;

import com.example.management.entities.Employee;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.management.repositories.EmployeeJPARepo;
import java.util.Optional;

@Service
@Transactional
public class JPAService {
    private final EmployeeJPARepo employeeJPARepo;

    @Autowired
    public JPAService(EmployeeJPARepo employeeJPARepo){
        this.employeeJPARepo = employeeJPARepo;
    }
    public Employee findEmployeeById(Long id){
        return employeeJPARepo.findById(id).get();
    }

    public Long createEmployee(Employee employee) {
        return employeeJPARepo.save(employee).getId();
    }

    public Long updateEmployee(Employee employee){
        // Check if the employee exists
        if (!employeeJPARepo.existsById(employee.getId())) {
            throw new EntityNotFoundException("Employee with ID " + employee.getId() + " not found.");
        }

        // Update the employee
        return employeeJPARepo.save(employee).getId();
    }

    public void deleteEmployee(Long id){
        this.employeeJPARepo.deleteById(id);
    }
}
