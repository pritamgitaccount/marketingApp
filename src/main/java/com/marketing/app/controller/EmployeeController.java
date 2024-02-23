package com.marketing.app.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
//import org.springframework.ui.Model;
import com.marketing.app.dto.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import com.marketing.app.dto.EmpDto;
import com.marketing.app.entity.Employee;
import com.marketing.app.service.EmpService;
//import com.marketing.app.util.EmailService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmpService empService;
    @Autowired
    private ModelMapper modelMapper;
//	@Autowired
//	private EmailService emailService;

    // http://localhost:8080/api/employees

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmpDto empDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(),
                    HttpStatus.BAD_REQUEST);
        } else {
            EmpDto dto = empService.createEmployee(empDto);
//			emailService.sendSimpleMail(empDto.getEmail(), "Test", "test Email");
//			model.addAttribute("msg", "Record saved");
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
    }

    //http://localhost:8080/api/employees?pageNo=0&pageSize=3&sortBy=email&sortDir=desc
    @GetMapping
    public EmployeeResponse getAllEmployees(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        // Adjust the method in your service layer to handle pagination and sorting
        EmployeeResponse employeeResponse = empService.getAllEmployees(pageNo, pageSize, sortBy, sortDir);

              return employeeResponse;
    }


    // http://localhost:8080/api/employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") long id) {
        try {
            empService.deleteEmployeeById(id);
            return ResponseEntity.ok("Employee is deleted");
        } catch (EntityNotFoundException ex) {
            // Handle the case where the employee with the given ID was not found
            // OR //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not
            // found");
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id, @RequestBody EmpDto empDto) {
        Employee updatedEmployee = empService.updateEmployee(id, empDto);

        // Check if the updated employee is not null, indicating a successful update
        if (updatedEmployee != null) {
            // Convert the updated employee to DTO
            EmpDto updatedEmpDto = modelMapper.map(updatedEmployee, EmpDto.class);

            // Return the updated employee DTO and an appropriate HTTP status
            return ResponseEntity.ok(updatedEmpDto);
        } else {
            // Handle the case where the employee is not found
            return new ResponseEntity<>("Employee id not found", HttpStatus.NOT_FOUND);
        }
    }
}