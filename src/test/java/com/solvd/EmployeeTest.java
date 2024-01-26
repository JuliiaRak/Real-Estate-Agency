package com.solvd;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.impl.EmployeeRepositoryMybatisImpl;
import com.solvd.service.EmployeeService;
import com.solvd.service.impl.EmployeeServiceImpl;

import java.util.Date;

public class EmployeeTest {
    public static void main(String[] args) throws EntityNotFoundException {

        EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeRepositoryMybatisImpl());

//        Employee employee = new Employee("K]Julia", "Ral", "juliarak@gmail.com", "+380947385663", "Administrator", null, 30000L);
//        employeeService.create(employee);
//        System.out.println(employeeService.getById(1L));
//
//        employee.setFirstName("Olena");
//        employeeService.update(employee);
//        System.out.println(employeeService.getById(employee.getId()));
//        System.out.println(employeeService.getAll());

        employeeService.deleteById(1L);
    }
}
