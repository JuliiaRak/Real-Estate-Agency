package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EntityAlreadyExistExeption;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.service.EmployeeService;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void create(Employee employee) throws EntityAlreadyExistExeption {
        validate(employee);
        employeeEmailAndPhoneNumberNotExistYetCheck(employee);

        employeeRepository.create(employee);
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void update(Employee employee) throws EntityAlreadyExistExeption, EntityNotFoundException {
        validate(employee);
        employeeEmailAndPhoneNumberNotExistYetCheck(employee);

        if (employeeRepository.findById(employee.getId()).isEmpty()) {
            throw new EntityNotFoundException("Employee", employee.getId());
        }
        employeeRepository.update(employee);
    }

    @Override
    public Employee getById(long id) throws EntityNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee", id));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
  
    public void validate(Employee employee) {
        // Перевірка, що ім'я та прізвище не є порожніми
        if (employee.getFirstName()==null || employee.getLastName()==null  || employee.getFirstName().isEmpty() || employee.getLastName().isEmpty() || employee.getFirstName().isBlank() || employee.getLastName().isBlank()) {
            throw new IllegalArgumentException("The first and last name cannot be empty");
        }

        // Перевірка, що адреса електронної пошти є коректною
        if (!employee.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-z]+$")) {
            throw new IllegalArgumentException("Email address does not match the pattern 'yourname@mail.com'");
        }

        // Перевірка, що номер телефону є коректним
        if (employee.getPhoneNumber()==null || !employee.getPhoneNumber().matches("^\\+?[0-9]{12}$")) {
            throw new IllegalArgumentException("The phone number does not match the pattern '+380980307445'");
        }

        // Перевірка, що дата прийняття на роботу є у минулому
        if (employee.getHireDate()==null || employee.getHireDate().after(new Date())) {
            throw new IllegalArgumentException("Hiring date cannot be in the future");
        }

        // Перевірка, що зарплата не є від'ємною
        if (employee.getSalary() < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
    }

    private void employeeEmailAndPhoneNumberNotExistYetCheck(Employee employee) throws EntityAlreadyExistExeption {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new EntityAlreadyExistExeption("Employee", "email", employee.getEmail());
        }

        if (employeeRepository.findByPhoneNumber(employee.getPhoneNumber()).isPresent()) {
            throw new EntityAlreadyExistExeption("Employee", "phone number", employee.getPhoneNumber());
        }
    }
}
