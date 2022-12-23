package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private List<Employee> actualEmployees;

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee("Petr", "Lisin", 2, 3000);
        Employee employee2 = new Employee("Artem", "Petrov", 2, 1000);
        Employee employee3 = new Employee("Ivan", "Ivanov", 1, 6000);
        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3));
        when(employeeService.getAllEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void shouldReturnEmployeesForDepartment() {
        final int department = 1;
        final List<Employee> actual = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeesFor(department);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSumSalaryForDepartment() {
        final int department = 1;
        final int actual = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
        final int expected = departmentService.getSumSalaryFor(department);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMaxSalaryForDepartment() {
        final int department = 1;
        final int actual = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .max().orElse(0);
        final int expected = departmentService.getMaxSalaryFor(department);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMinSalaryForDepartment() {
        final int department = 1;
        final int actual = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .min().orElse(0);
        final int expected = departmentService.getMinSalaryFor(department);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllEmployeesByDepartment() {
        final Map<Integer, List<Employee>> actual = actualEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        final Map<Integer, List<Employee>> expected = departmentService.getAllEmployeesByDepartment();
        assertEquals(expected, actual);
    }
}