package com.marketing.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.marketing.app.dto.EmployeeResponse;
import com.marketing.app.exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.marketing.app.dto.EmpDto;
import com.marketing.app.entity.Employee;
import com.marketing.app.repository.EmployeeRepository;
import com.marketing.app.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private ModelMapper modelMapper;  //It is a sub library of spring

    @Override
    public EmpDto createEmployee(EmpDto empDto) {

        // Create a new instance of Employee
//        Employee employee = new Employee();
//
//        // Set properties individually
//        employee.setFirstName(empDto.getFirstName());
//        employee.setLastName(empDto.getLastName());
//        employee.setEmail(empDto.getEmail());
//        employee.setMobile(empDto.getMobile());
        Employee employee = mapToEmployee(empDto);


        // Save the Employee entity to the repository
        Employee savedEmployee = employeeRepo.save(employee);

        EmpDto savedEmpDto = new EmpDto();
        savedEmpDto.setId(savedEmployee.getId());
        savedEmpDto.setFirstName(savedEmployee.getFirstName());
        savedEmpDto.setLastName(savedEmployee.getLastName());
        savedEmpDto.setEmail(savedEmployee.getEmail());
        savedEmpDto.setMobile(savedEmployee.getMobile());

        return savedEmpDto;

    }

    @Override
    public EmployeeResponse getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort); //here String sortBy converts to Sort
        Page<Employee> all = employeeRepo.findAll(pageable);

        List<Employee> posts = all.getContent();
        List<EmpDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        EmployeeResponse empResponse = new EmployeeResponse();
        empResponse.setFirstName(dtos);
        empResponse.setPageNo(all.getNumber());
        empResponse.setTotalPages(all.getTotalPages());
        empResponse.setTotalElements((int) all.getNumberOfElements());
        empResponse.setPageSize(all.getSize());
        empResponse.setLast(all.isLast());

        return empResponse;
    }

    EmpDto mapToDto(Employee employee) {
        EmpDto dto = modelMapper.map(employee, EmpDto.class);
        return dto;
    }

    Employee mapToEmployee(EmpDto empDto) {
        Employee emp = modelMapper.map(empDto, Employee.class);
        return emp;
    }


    @Override
    public void deleteEmployeeById(long id) {
        // Check if the employee exists before attempting to delete
        employeeRepo.findById((long) id).orElseThrow(
                () -> new ResourceNotFound("Post is not found with Id" + id));
        employeeRepo.deleteById((long) id);

    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepo.findById(id);
    }




    @Override
    public Employee updateEmployee(long id, EmpDto empDto) {
        Employee updatedEmployee = employeeRepo.findById((long) id).orElseThrow(

                () -> new ResourceNotFound("Employee Id has not found : " + id)
        );
        Employee employee = mapToEmployee(empDto);
        Employee savedEmployee = employeeRepo.save(employee);
        EmpDto dto = mapToDto(employee);
        return updatedEmployee;

        //        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
//
//        if (optionalEmployee.isPresent()) {
//            Employee existingEmployee = optionalEmployee.get();
//
//            // Update the employee properties with the values from the DTO
//            existingEmployee.setFirstName(empDto.getFirstName());
//            existingEmployee.setLastName(empDto.getLastName());
//            existingEmployee.setEmail(empDto.getEmail());
//            existingEmployee.setMobile(empDto.getMobile());
//
//            // Save the updated employee back to the repository
//            employeeRepo.save(existingEmployee);
//        } else {
//            // Handle the case where the employee is not found
//            throw new RuntimeException("Employee not found with id: " + id);
//        }
    }

}
