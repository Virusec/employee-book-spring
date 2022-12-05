package com.example.employee.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final int department;
    private final int salary;
    private final int id;
    private static int counter;

    public Employee(String firstName, String lastName, int department, int salary) {
        if (Objects.equals(firstName, StringUtils.capitalize(firstName))) {
            this.firstName = firstName;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (Objects.equals(lastName, StringUtils.capitalize(lastName))) {
            this.lastName = lastName;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        this.department = department;
        this.salary = salary;
        this.id = counter++;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }
}