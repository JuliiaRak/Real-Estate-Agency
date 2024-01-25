package com.solvd.service;

import com.solvd.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void create(Employee employee);
    void deleteById(long id);
    void update(Employee employee);
    Employee getById(long id);
    List<Employee> getAll();
}
