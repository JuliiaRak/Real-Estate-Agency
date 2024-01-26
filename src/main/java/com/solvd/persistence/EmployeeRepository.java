package com.solvd.persistence;

import com.solvd.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void create(Employee employee);
    void deleteById(long id);
    void update(Employee employee);
    Optional<Employee> findById(long id);
    List<Employee> findAll();
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByPhoneNumber(String phoneNember);
}
