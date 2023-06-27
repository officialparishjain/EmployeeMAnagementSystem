package com.parishjain.EMS.repository;

import com.parishjain.EMS.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendanceRepo extends JpaRepository<Attendance,Long> {

    List<Attendance> findAllByEmployeeId(Long id);
}
