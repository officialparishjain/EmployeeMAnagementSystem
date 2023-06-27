package com.parishjain.EMS.repository;

import com.parishjain.EMS.models.HR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHRRepo extends JpaRepository<HR,Long> {

}
