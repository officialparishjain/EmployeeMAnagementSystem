package com.parishjain.EMS.service;
import com.parishjain.EMS.models.Employee;
import com.parishjain.EMS.repository.IEmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    IEmployeeRepo employeeRepo;

    @Autowired
    AuthenticationService authenticationService;

    public void addEmployee(Employee employee) {

        employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployee() {

        return employeeRepo.findAll();
    }

    public boolean authenticate(String email, String token) {
        return authenticationService.authenticate(email, token);
    }

    public ResponseEntity<String> updateEmployee(Employee employee, String email) {

        // First we are checking whose record is updating is the same as the authenticated user
        if(!employee.getEmployeeEmail().equals(email)){
            return ResponseEntity.badRequest().body("You cannot update another user data");
        }
        else{
            Employee emp = employeeRepo.getEmployeeByEmployeeEmail(email);
            emp.setEmpFirstName(employee.getEmpFirstName());
            emp.setEmployeeAddress(employee.getEmployeeAddress());
            emp.setEmpLastName(employee.getEmpLastName());
            emp.setEmployeePhoneNumber(employee.getEmployeePhoneNumber());
            employeeRepo.save(emp);
            return ResponseEntity.ok().body("Success.. Record Updated Successfully..");
        }

    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findByEmpId(id);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepo.findById(id);
    }

    @Transactional
    public void deleteEmployeeById(Long id) {

    }
}
