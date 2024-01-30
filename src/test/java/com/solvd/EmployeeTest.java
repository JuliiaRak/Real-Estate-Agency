package com.solvd;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.persistence.impl.EmployeeRepositoryMybatisImpl;
import com.solvd.service.EmployeeService;
import com.solvd.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class EmployeeTest {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryMybatisImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

    @Test
    public void createEmployeeTest() throws FieldValidationException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        Employee employee = createSimpleEmployee();
        employeeService.create(employee);
        Employee retrievedEmployee = employeeRepository.findById(employee.getId()).orElse(null);

        Assertions.assertNotNull(retrievedEmployee);
        Assertions.assertEquals(employee, retrievedEmployee);
        employeeService.deleteById(employee.getId());
    }

    @Test
    public void deleteEmployeeByIdTest() throws FieldValidationException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        Employee employee = createSimpleEmployee();
        employeeService.create(employee);
        employeeService.deleteById(employee.getId());

        Assertions.assertFalse(employeeRepository.findById(employee.getId()).isPresent());
    }

    @Test
    public void updateEmployeeTest() throws EntityNotFoundException, FieldValidationException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        Employee employee = createSimpleEmployee();
        employeeService.create(employee);
        employee.setEmail("ewjidjei@gmail.com");
        employee.setSalary(4000);
        employeeService.update(employee);
        Employee updateEmployee = employeeRepository.findById(employee.getId()).orElse(null);

        Assertions.assertNotNull(updateEmployee);
        Assertions.assertEquals(employee.getEmail(), updateEmployee.getEmail());
        Assertions.assertEquals(employee.getSalary(), updateEmployee.getSalary());
        employeeService.deleteById(employee.getId());
    }

    @Test
    public void getByIdEmployeeTest() throws FieldValidationException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, EntityNotFoundException {
        Employee employee = createSimpleEmployee();
        employeeService.create(employee);
        Employee employeeFind = employeeService.getById(employee.getId());

        Assertions.assertEquals(employee, employeeFind);
        employeeService.deleteById(employee.getId());
    }

    private Employee createSimpleEmployee() {
        return new Employee("Anna", "Pochunyk", "an2365n43a654@gmail.com", "+380654124273",
                "Agent", new Date(), 3200);
    }
}
