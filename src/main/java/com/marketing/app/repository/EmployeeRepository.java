package com.marketing.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketing.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
