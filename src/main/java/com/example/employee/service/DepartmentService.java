package com.example.employee.service;

import com.example.employee.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getEmployeesFor(int department);

    int getSumSalaryFor(int department);

    int getMaxSalaryFor(int department);

    int getMinSalaryFor(int department);

    Map<Integer, List<Employee>> getAllEmployeesByDepartment();
}
