package com.example.management.controllers;

import com.example.management.DTO.CriteriaDTO;
import com.example.management.services.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/criteria")
public class CriteriaController {

    private final CriteriaService criteriaService;
    @Autowired
    public CriteriaController(CriteriaService criteriaService) {
        this.criteriaService = criteriaService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<CriteriaDTO>> findEmployeesByAgeAndDepartment(
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "department", required = false) String departmentName) {
        List<CriteriaDTO> employees = criteriaService.findEmployeesByCriteria(age, departmentName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
