package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.record.EmployeeRequest;
import com.example.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/salary/min")
    public Employee getEmployeeMinSalary() {
        return this.employeeService.getEmployeeMinSalary();
    }

    @GetMapping("/salary/max")
    public Employee getEmployeeMaxSalary() {
        return this.employeeService.getEmployeeMaxSalary();
    }

    @GetMapping("/salary/high-salary")
    public List<Employee> getEmployeeAvgSalary() {
        return this.employeeService.getEmployeeAvgSalary();
    }
}
