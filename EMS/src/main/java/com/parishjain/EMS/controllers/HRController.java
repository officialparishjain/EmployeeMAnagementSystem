package com.parishjain.EMS.controllers;

import com.parishjain.EMS.models.Attendance;
import com.parishjain.EMS.models.Employee;
import com.parishjain.EMS.models.HR;
import com.parishjain.EMS.service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class HRController {


    @Autowired
    HRService hrService;

    // Registering the HR
    @PostMapping(value = "/registerAdmin")
    private ResponseEntity<String> registerHR(@RequestBody HR hr){
        return hrService.registerHR(hr);
    }

    // End Point for Adding the Employee
    @PostMapping("/registerEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        HttpStatus status;
        String response;
        try{
            hrService.addEmployee(employee);
            status = HttpStatus.OK;
            response = "Employee Added Successfully";
        }
        catch (Exception ex){
            status = HttpStatus.BAD_REQUEST;
            response = "Error Occurred" + ex;
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(value = "/markAttendance")
    public ResponseEntity<String> markAttendance(@RequestBody Attendance attendanceRequest) {
        HttpStatus status;
        String response;
        try {
            boolean res = hrService.markAttendance(attendanceRequest);
            if(res){
                status = HttpStatus.OK;
                response = "Attendance Marked Successfully";
            }
            else{
                status = HttpStatus.BAD_REQUEST;
                response = "Error Occurred while marking attendance";
            }

        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error Occurred: " + ex.getMessage();
        }
        return ResponseEntity.status(status).body(response);
    }

    // Generate Attendance Report By id
    @GetMapping(value = "/attendanceReport/{id}")
    private ResponseEntity<byte[]> generateAttendanceReport(@PathVariable Long id){

        Employee employee = hrService.getEmployeeById(id);
        if(employee != null){
            return hrService.generateAttendanceReport(id);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Get Employee By Id
    @GetMapping(value = "/emp/{id}")
    private ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = hrService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // Delete Employee
    @DeleteMapping(value = "/delete/{empId}")
    private ResponseEntity<String> deleteEmployeeById(@PathVariable Long empId){

        HttpStatus status;
        String response;

        try{
            hrService.deleteEmpById(empId);
            status = HttpStatus.OK;
            response = "Employee Deleted Successfully";
        }
        catch (Exception ex){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = "Something went wrong";
        }
        return ResponseEntity.status(status).body(response);
    }

}
