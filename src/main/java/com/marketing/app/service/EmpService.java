package com.marketing.app.service;

import java.util.Optional;

import com.marketing.app.dto.EmpDto;
import com.marketing.app.dto.EmployeeResponse;
import com.marketing.app.entity.Employee;

public interface EmpService {

    EmpDto createEmployee(EmpDto empDto);

      EmployeeResponse getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir);

    void deleteEmployeeById(long id);

    Employee updateEmployee(long id, EmpDto empDto);

    Optional<Employee> getEmployeeById(long id);
}
