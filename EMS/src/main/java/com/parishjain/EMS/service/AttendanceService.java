package com.parishjain.EMS.service;

import com.parishjain.EMS.models.Attendance;
import com.parishjain.EMS.repository.IAttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    IAttendanceRepo attendanceRepo;
    public void save(Attendance attendance) {
        attendanceRepo.save(attendance);
    }

    public List<Attendance> getAttendanceById(Long empId) {
        return attendanceRepo.findAllByEmployeeId(empId);
    }
}
