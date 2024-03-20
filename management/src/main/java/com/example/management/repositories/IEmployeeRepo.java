package com.example.management.repositories;

import com.example.management.entities.Employee;

public interface IEmployeeRepo {

    Employee findByID(Long employeeId);
    Long createEmployee(Employee employee);
    Long updateEmployee(Employee employee);
    void deleteEmployee(Long employeeId);

}
