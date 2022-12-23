package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.record.EmployeeRequest;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    EmployeeService employeeService;
    Employee employee1;
    Employee employee2;
    Employee employee3;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
        employee1 = employeeService.addEmployee(new EmployeeRequest("Petr", "Lisin", 2, 3000));
        employee2 = employeeService.addEmployee(new EmployeeRequest("Artem", "Petrov", 2, 1000));
        employee3 = employeeService.addEmployee(new EmployeeRequest("Ivan", "Ivanov", 1, 6000));
    }

    @Test
    public void shouldReturnAllEmployees() {
        Collection<Employee> actual = employeeService.getAllEmployees();
        assertEquals(3, actual.size());
        Assertions.assertTrue(actual.containsAll(List.of(employee1, employee2, employee3)));
    }

    @Test
    public void shouldAddCorrectEmployee() {
        EmployeeRequest actual = new EmployeeRequest("Petr", "Lisin", 2, 3000);
        Employee expected = employeeService.addEmployee(actual);
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getDepartment(), actual.getDepartment());
    }

    @Test
    public void shouldAddIncorrectFirstNameEmployee() {
        EmployeeRequest actual = new EmployeeRequest("1111", "test", 2, 1000);
        assertThrows(ResponseStatusException.class, () -> employeeService.addEmployee(actual));
    }

    @Test
    public void shouldAddIncorrectLastNameEmployee() {
        EmployeeRequest actual = new EmployeeRequest("test", "1111", 2, 1000);
        assertThrows(ResponseStatusException.class, () -> employeeService.addEmployee(actual));
    }

    @Test
    public void shouldAddSameEmployee() {
        employeeService.addEmployee(new EmployeeRequest("Ivan", "Ivanov", 1, 6000));
        employeeService.addEmployee(new EmployeeRequest("Ivan", "Ivanov", 1, 6000));
        assertEquals(5, employeeService.getAllEmployees().size());
    }

    @Test
    public void shouldReturnSalarySum() {
        assertEquals(10000, employeeService.getSalarySum());
    }

    @Test
    public void shouldReturnEmployeeMinSalary() {
        assertEquals(employee2, employeeService.getEmployeeMinSalary());
    }

    @Test
    public void shouldReturnEmployeeMaxSalary() {
        assertEquals(employee3, employeeService.getEmployeeMaxSalary());
    }

    @Test
    public void getEmployeeAvgSalary() {
        assertIterableEquals(List.of(employee3), employeeService.getEmployeeAvgSalary());
    }
}