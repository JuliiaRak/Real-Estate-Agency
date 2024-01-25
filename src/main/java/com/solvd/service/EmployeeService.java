package com.solvd.service;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void create(Employee employee);
    void deleteById(long id);
    void update(Employee employee);
    Employee getById(long id) throws EntityNotFoundException;
    List<Employee> getAll();
}
