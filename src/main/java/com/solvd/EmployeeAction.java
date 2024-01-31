package com.solvd;

import com.solvd.domain.Employee;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.EmployeeService;
import com.solvd.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Scanner;

public class EmployeeAction {
    private static final EmployeeService EMPLOYEE_SERVICE = new EmployeeServiceImpl();

    public static Employee chooseEmployee(Scanner scanner) throws EntityNotFoundException, FieldValidationException {
        System.out.println("Here the list of employees, choose one to show you the property");
        List<Employee> employees = EMPLOYEE_SERVICE.getAll();

        System.out.println(Employee.getTableHeader());
        for (Employee empl : employees) {
            System.out.println(empl);
        }

        System.out.print("Input the id of employee: ");
        String emplId = scanner.nextLine();
        return EMPLOYEE_SERVICE.getById(parseLong(emplId));
    }

    private static long parseLong(String choice) throws FieldValidationException {
        try {
            return Long.parseLong(choice);
        } catch (NullPointerException | NumberFormatException e) {
            throw new FieldValidationException(String.format("Specified incorrect id: %s", choice), e);
        }
    }
}
