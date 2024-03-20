package com.example.management.repositories;

import com.example.management.entities.Department;

public interface IDepartmentRepo {

    Department findByID(Long departmentId);
    Long createDepartment(Department department);
    Long updateDepartment(Department department);
}
