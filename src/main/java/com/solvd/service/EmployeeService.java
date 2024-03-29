package com.solvd.service;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;

import java.util.List;

public interface EmployeeService {
    void create(Employee employee) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException;

    void deleteById(long id);

    void update(Employee employee) throws EmailAlreadyExistsException, EntityNotFoundException, PhoneNumberAlreadyExistsException, FieldValidationException;

    Employee getById(long id) throws EntityNotFoundException;

    List<Employee> getAll();

    boolean existsById(long id);
}