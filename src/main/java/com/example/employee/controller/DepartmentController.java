package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getEmployeesFor(@PathVariable(required = false) Integer id) {
        return this.departmentService.getEmployeesFor(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSumSalaryFor(@PathVariable(required = false) Integer id) {
        return this.departmentService.getSumSalaryFor(id);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryFor(@PathVariable(required = false) Integer id) {
        return this.departmentService.getMaxSalaryFor(id);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryFor(@PathVariable(required = false) Integer id) {
        return this.departmentService.getMinSalaryFor(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return this.departmentService.getAllEmployeesByDepartment();
    }
}
