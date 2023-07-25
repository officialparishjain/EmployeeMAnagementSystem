package com.parishjain.EMS.controllers;

import com.parishjain.EMS.models.Employee;
import com.parishjain.EMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // GET ALL EMPLOYEES

    @GetMapping(value = "/check")
    public String check(){
        return "Checked";
    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam String email, @RequestParam String token){

        HttpStatus status;
        List<Employee> employeeList = null;
        if(employeeService.authenticate(email,token)){
            employeeList = employeeService.getAllEmployee();
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
        }
        return ResponseEntity.status(status).body(employeeList);
    }

    // Update Employee
    @PutMapping(value = "/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee,
                                                 @RequestParam String email,
                                                 @RequestParam String token){
        HttpStatus status;
        String response;
        if(employeeService.authenticate(email,token)){
            return employeeService.updateEmployee(employee,email);
        }
        else{
            return ResponseEntity.badRequest().body("User not Authenticated..");
        }
    }

}
