package com.parishjain.EMS.service;

import com.parishjain.EMS.models.Attendance;
import com.parishjain.EMS.models.Employee;
import com.parishjain.EMS.models.HR;
import com.parishjain.EMS.models.User;
import com.parishjain.EMS.repository.IHRRepo;
import jakarta.transaction.Transactional;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.DatatypeConverter;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class HRService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    IHRRepo hrRepo;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    UserService userService;
    
    public void addEmployee(Employee employee) throws NoSuchAlgorithmException {
        employeeService.addEmployee(employee);
        User user = new User();
        user.setUserEmail(employee.getEmployeeEmail());
        String password = employee.getEmpFirstName() + '@' + "123";
        String encryptPassword = generateEncryptPassword(password);
        user.setUserPassword(encryptPassword);
        user.setEmployee(employee);
        userService.saveUser(user);
    }

    private String generateEncryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        // Get the user's password as a byte array and update the message digest with it
        messageDigest.update(password.getBytes());
        // Calculate the MD5 hash of the password
        byte[] digested = messageDigest.digest();
        // Concert Byte Array to hexadecimal String
        return DatatypeConverter.printHexBinary(digested);

    }

    public Boolean markAttendance(Attendance attendanceRequest) {

        Long id = attendanceRequest.getEmployeeId();
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if(optionalEmployee.isEmpty()){
            return false;
        }
        else{
            Attendance attendance = new Attendance(attendanceRequest.getEmployeeId(),attendanceRequest.getStatus());
            attendanceService.save(attendance);
            return true;
        }
    }


    public Employee getEmployeeById(Long id) {
        return employeeService.getEmployeeById(id);
    }

    public ResponseEntity<byte[]> generateAttendanceReport(Long id) {

        try {
            // Create a new workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Attendance Data");
            List<Attendance> attendanceList = attendanceService.getAttendanceById(id);

            // Add headings to the sheet
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Sr No.");
            headerRow.createCell(1).setCellValue("Date");
            headerRow.createCell(2).setCellValue("Status");

            // Convert attendance data to Excel rows and cells
            int rowNum = 1;
            int j = 1;
            for (Attendance attendance : attendanceList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(j ++);
                row.createCell(1).setCellValue(attendance.getLocalDate().toString());
                row.createCell(2).setCellValue(attendance.getStatus().toString());
                // Add more cells as needed for other attendance fields
            }

            // Generate the Excel file as a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String filename = "Attendance_Emp_" + id;
            headers.setContentDispositionFormData("attachment", filename +".xlsx");

            // Return the Excel file as the HTTP response
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }




    }

    public ResponseEntity<String> registerHR(HR hr) {
        hrRepo.save(hr);
        return ResponseEntity.ok().body("HR Registered Successfully");
    }


    public void deleteEmpById(Long id) {

        Employee employee = employeeService.getEmployeeById(id);
        userService.deleteUserByEmployee(employee);
    }
}
