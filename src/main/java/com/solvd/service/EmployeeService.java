package com.solvd.service;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EmailAlreadyExistException;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;

public interface EmployeeService {
    void create(Employee employee) throws EmailAlreadyExistException;
    void deleteById(long id);
    void update(Employee employee) throws EmailAlreadyExistException, EntityNotFoundException;
    Employee getById(long id) throws EntityNotFoundException;
    List<Employee> getAll();
}