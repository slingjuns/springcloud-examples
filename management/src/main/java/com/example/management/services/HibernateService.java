package com.example.management.services;

import com.example.management.entities.Department;
import com.example.management.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.management.repositories.DepartmentHibernateRepo;
import com.example.management.repositories.EmployeeHibernateRepo;

@Service
@Transactional
public class HibernateService {
    private final EmployeeHibernateRepo employeeRepo;
    private final DepartmentHibernateRepo departmentRepo;
    @Autowired
    public HibernateService(EmployeeHibernateRepo employeeRepo, DepartmentHibernateRepo departmentRepo){
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    @Transactional(readOnly = true)
    public Employee findEmployeeById(Long id){
        return this.employeeRepo.findByID(id);
    }

    public Long createEmployee(Employee employee){
        return employeeRepo.createEmployee(employee);
    }

    public Long updateEmployee(Employee employee){
        return employeeRepo.updateEmployee(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployee(id);
    }

    public Department findDepartmentById(Long id){
        return this.departmentRepo.findByID(id);
    }

    public Long createDepartment(Department department) {
        return departmentRepo.createDepartment(department);
    }

    public Long updateDepartment(Department department) {
        return departmentRepo.updateDepartment(department);
    }
}
