package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.persistence.impl.EmployeeRepositoryMybatisImpl;
import com.solvd.service.EmployeeService;
import com.solvd.service.PersonService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.date.NotNullDateValidator;
import com.solvd.service.validators.date.PastDateValidator;
import com.solvd.service.validators.integer.MaxIntegerValidator;
import com.solvd.service.validators.integer.MinIntegerValidator;
import com.solvd.service.validators.object.NotNullObjectValidator;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryMybatisImpl();
    }

    @Override
    public void create(Employee employee) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        validate(employee);
        employeeEmailAndPhoneNumberNotExistYetCheck(employee);

        employeeRepository.create(employee);
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void update(Employee employee) throws EmailAlreadyExistsException, EntityNotFoundException, PhoneNumberAlreadyExistsException {
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

    @Override
    public boolean existsById(long id) {
        return employeeRepository.findById(id).isPresent();
    }

    private void validate(Employee employee) {
        Validator<Object> objectValidator = new NotNullObjectValidator();
        objectValidator.validate("employee", employee);

        PersonService.validate(employee.getFirstName(), employee.getFirstName(), employee.getEmail(), employee.getPhoneNumber());

        Validator<Date> dateValidator = new PastDateValidator(new NotNullDateValidator());
        dateValidator.validate("hire date", employee.getHireDate());

        Validator<Integer> integerValidator = new MaxIntegerValidator(new MinIntegerValidator());
        integerValidator.validate("salary", employee.getSalary());
    }

    private void employeeEmailAndPhoneNumberNotExistYetCheck(Employee employee) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Employee", "email", employee.getEmail());
        }

        if (employeeRepository.findByPhoneNumber(employee.getPhoneNumber()).isPresent()) {
            throw new PhoneNumberAlreadyExistsException("Employee", "phone number", employee.getPhoneNumber());
        }
    }
}
