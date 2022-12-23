package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.record.EmployeeRequest;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Collection<Employee> getAllEmployees();

    Employee addEmployee(EmployeeRequest employeeRequest);

    int getSalarySum();

    Employee getEmployeeMinSalary();

    Employee getEmployeeMaxSalary();

    List<Employee> getEmployeeAvgSalary();
}
