package com.solvd.service;

import com.solvd.domain.Client;
import com.solvd.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void create(Employee employee);
    void deleteById(long id);
    void update(Employee employee);
    Optional<Employee> getById(long id);
    List<Employee> getAll();
}
