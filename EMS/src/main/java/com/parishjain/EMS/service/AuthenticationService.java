package com.parishjain.EMS.service;

import com.parishjain.EMS.models.AuthenticationToken;
import com.parishjain.EMS.models.User;
import com.parishjain.EMS.repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;

    public boolean authenticate(String email, String token) {
        if(email == null || token == null) return false;

        AuthenticationToken authToken = authenticationRepo.findFirstByAuthToken(token);

        if(authToken == null) return false;

        String authEmail = authToken.getUser().getUserEmail();

        return authEmail.equals(email);
    }

    public void save(AuthenticationToken authenticationToken) {
        authenticationRepo.save(authenticationToken);
    }

    public ResponseEntity<String> signOut(User user) {
        authenticationRepo.deleteByUser(user);
        return ResponseEntity.ok().body("You have Sign Out Successfully ..");
    }
}
