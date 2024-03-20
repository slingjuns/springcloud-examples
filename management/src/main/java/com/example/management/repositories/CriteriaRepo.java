package com.example.management.repositories;

import com.example.management.DTO.CriteriaDTO;
import com.example.management.entities.Department;
import com.example.management.entities.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaRepo {
    private final EntityManager em;
    private final CriteriaBuilder cb;

    @Autowired
    public CriteriaRepo(EntityManager entityManager){
        this.em = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
    }

    public List<CriteriaDTO> findEmployeesByAgeAndDepartmentName(Integer age, String departmentName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CriteriaDTO> query = cb.createQuery(CriteriaDTO.class);
        Root<Employee> employee = query.from(Employee.class);
        Join<Employee, Department> department = employee.join("department", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        if (age != null) {
            predicates.add(cb.greaterThan(employee.get("age"), age));
        }
        if (departmentName != null) {
            predicates.add(cb.equal(department.get("name"), departmentName));
        }

        query.multiselect(employee.get("name").alias("name"), employee.get("age").alias("age"), employee.get("salary").alias("salary"),
                        department.get("name").alias("departmentName"))
                .where(predicates.toArray(new Predicate[0]));
        for (CriteriaDTO info : em.createQuery(query).getResultList()) {
            System.out.println("Name: " + info.getName() + ", Age: " + info.getAge() + ", Salary: " + info.getSalary() + ", Department: " + info.getDepartmentName());
        }
        return em.createQuery(query).getResultList();
    }
}
