package com.parishjain.EMS.repository;

import com.parishjain.EMS.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee,Long> {

    Employee getEmployeeByEmployeeEmail(String email);

    Employee findByEmpId(Long empId);
}
