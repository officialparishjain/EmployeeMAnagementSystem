package com.parishjain.EMS.repository;

import com.parishjain.EMS.models.Employee;
import com.parishjain.EMS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {

    Boolean existsByUserEmail(String email);

    User getUserByUserEmail(String email);

    void deleteByEmployee(Employee employee);
}
