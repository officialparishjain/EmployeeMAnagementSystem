package com.parishjain.EMS.repository;

import com.parishjain.EMS.models.AuthenticationToken;
import com.parishjain.EMS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findFirstByAuthToken(String token);

    void deleteByUser(User user);
}
