package com.solvd.persistence.impl;

import com.solvd.domain.Employee;
import com.solvd.persistence.Config;
import com.solvd.persistence.EmployeeRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryMybatisImpl implements EmployeeRepository {
    @Override
    public void create(Employee employee) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.deleteById(id);
        }
    }

    @Override
    public void update(Employee employee) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.update(employee);
        }
    }

    @Override
    public Optional<Employee> findById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findById(id);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findAll();
        }
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findByEmail(email);
        }
    }

    @Override
    public Optional<Employee> findByPhoneNumber(String phoneNumber) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findByPhoneNumber(phoneNumber);
        }
    }
}
