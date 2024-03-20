package com.example.management.repositories;

import com.example.management.entities.Employee;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeHibernateRepo implements IEmployeeRepo {
    private final EntityManager entityManager;

    @Autowired
    public EmployeeHibernateRepo(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    // CRUD...
    public Employee findByID(Long employeeId){
        return entityManager.find(Employee.class, employeeId);
    }

    public Long createEmployee(Employee employee){
        entityManager.persist(employee);
        return employee.getId();
    }

    public Long updateEmployee(Employee employee){
        return entityManager.merge(employee).getId();
    }

    public void deleteEmployee(Long employeeId){
        entityManager.createQuery("DELETE FROM Employee e WHERE e.id = :employeeId")
                .setParameter("employeeId", employeeId)
                .executeUpdate();
    }
}
