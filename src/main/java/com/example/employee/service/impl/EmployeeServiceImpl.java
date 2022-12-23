package com.example.employee.service.impl;

import com.example.employee.model.Employee;
import com.example.employee.record.EmployeeRequest;
import com.example.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    @Override
    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Employee getEmployeeMinSalary() {
        return employees.values().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee getEmployeeMaxSalary() {
        return employees.values().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getEmployeeAvgSalary() {
        double avg = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employees.values().stream()
                .filter(s -> s.getSalary() > avg)
                .collect(Collectors.toList());

    }
}
