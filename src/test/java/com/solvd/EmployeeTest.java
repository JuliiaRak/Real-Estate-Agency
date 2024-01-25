package com.solvd;

import com.solvd.domain.Employee;
import com.solvd.persistence.impl.EmployeeRepositoryMybatisImpl;
import com.solvd.service.EmployeeService;
import com.solvd.service.impl.EmployeeServiceImpl;

import java.util.Date;

public class EmployeeTest {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeRepositoryMybatisImpl());

        Employee employee = new Employee(0L, null, "Rak", "juliarak@gmail.com", "+098-56-156-74", "Administrator", new Date(), 30000L);
        employeeService.create(employee);
//        System.out.println(employeeService.getById(employee.getId()));
//
//        employee.setFirstName("Olena");
//        employeeService.update(employee);
//        System.out.println(employeeService.getById(employee.getId()));
//        System.out.println(employeeService.getAll());

        //employeeService.deleteById(employee.getId());
    }
}
