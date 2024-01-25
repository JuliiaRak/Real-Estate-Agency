package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.service.EmployeeService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void create(Employee employee) {
        employeeRepository.create(employee);
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public Employee getById(long id) throws EntityNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee", id));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
