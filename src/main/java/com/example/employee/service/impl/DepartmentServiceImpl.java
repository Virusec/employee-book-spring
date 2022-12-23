package com.example.employee.service.impl;

import com.example.employee.model.Employee;
import com.example.employee.service.DepartmentService;
import com.example.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployeesFor(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public int getSumSalaryFor(int department) {
        return getEmployeesFor(department).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public int getMaxSalaryFor(int department) {
        return getEmployeesFor(department).stream()
                .mapToInt(Employee::getSalary)
                .max().orElseThrow();
    }

    @Override
    public int getMinSalaryFor(int department) {
        return getEmployeesFor(department).stream()
                .mapToInt(Employee::getSalary)
                .min().orElseThrow();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
