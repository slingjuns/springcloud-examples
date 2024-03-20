package com.example.management.repositories;

import com.example.management.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface EmployeeJPARepo extends JpaRepository<Employee, Long> {
    // Custom query to find an employee by ID using @Query
    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Employee findEmployeeById(@Param("id") Long id);

    // Custom update method using @Modifying and @Query
    @Modifying
    @Query("UPDATE Employee e SET e.name = :name, e.age = :age, e.salary = :salary WHERE e.id = :id")
    void updateEmployee(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age, @Param("salary") BigDecimal salary);

    // Custom delete method using @Modifying and @Query
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = :id")
    void deleteEmployee(@Param("id") Long id);
}
