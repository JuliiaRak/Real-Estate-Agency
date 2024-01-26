package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EntityAlreadyExistExeption;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.service.EmployeeService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.date.NotNullDateValidator;
import com.solvd.service.validators.date.PastDateValidator;
import com.solvd.service.validators.number.NotNegativeLongValidator;
import com.solvd.service.validators.string.EmailStringValidator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.PhoneNumberStringValidator;
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

    private void validate(Employee employee) {
        Validator<String> notEmptyStringValidator = new NotEmptyStringValidator(new NotNullStringValidator());
        notEmptyStringValidator.validate("first name", employee.getFirstName());
        notEmptyStringValidator.validate("last name", employee.getLastName());

        Validator<String> emailValidator = new EmailStringValidator(notEmptyStringValidator);
        emailValidator.validate("email", employee.getEmail());

        Validator<String> phoneNumberValidator = new PhoneNumberStringValidator(notEmptyStringValidator);
        phoneNumberValidator.validate("phone number", employee.getPhoneNumber());

        Validator<Date> dateValidator = new PastDateValidator(new NotNullDateValidator());
        dateValidator.validate("hire date", employee.getHireDate());

        Validator<Long> longValidator = new NotNegativeLongValidator();
        longValidator.validate("salary", employee.getSalary());
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
