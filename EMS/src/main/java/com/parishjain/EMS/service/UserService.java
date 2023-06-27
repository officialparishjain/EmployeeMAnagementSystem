package com.parishjain.EMS.service;

import com.parishjain.EMS.DTO.SignInInputDto;
import com.parishjain.EMS.DTO.SignInOutputDto;
import com.parishjain.EMS.models.AuthenticationToken;
import com.parishjain.EMS.models.Employee;
import com.parishjain.EMS.models.User;
import com.parishjain.EMS.repository.IUserRepo;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;



    @Autowired
    AuthenticationService authService;


    public void saveUser(User user) {
        userRepo.save(user);
    }

    public ResponseEntity<SignInOutputDto> signIn(SignInInputDto signInInputDto) {

        String email = signInInputDto.getEmail();

        // First Checking the email is present or not
        boolean isPresent = userRepo.existsByUserEmail(email);

        if(!isPresent){
            return ResponseEntity.badRequest().body(new SignInOutputDto("Failure","User not present.."));
        }
        else{
            // User is Present
            String encryptedPassword = null;
            try{
                encryptedPassword = generateEncryptedPassword(signInInputDto.getPassword());
            }
            catch (Exception ex){
                return ResponseEntity.badRequest().body(new SignInOutputDto("Failure","Error Occurred .."));
            }

            // Now we will match the password that the user has entered with the password in database
            User user = userRepo.getUserByUserEmail(email);
            boolean match = user.getUserPassword().equals(encryptedPassword);

            if(!match){
                return ResponseEntity.badRequest().body(new SignInOutputDto("Failure","Password does not Match"));
            }
            else{
                // Now we are generating the Authentication token for the user
                AuthenticationToken authenticationToken = new AuthenticationToken(user);
                authService.save(authenticationToken);
                return ResponseEntity.ok().body(new SignInOutputDto(authenticationToken.getAuthToken(),"Success"));

            }

        }
    }

    public String generateEncryptedPassword(String password) throws Exception{

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        // Calculation message digest 5  hash
        byte[] digested = messageDigest.digest();
        return DatatypeConverter.printHexBinary(digested);
    }


    @Transactional
    public void deleteUserByEmployee(Employee employee) {
        userRepo.deleteByEmployee(employee);
    }

    public ResponseEntity<String> signOut(String email) {
        User user = userRepo.getUserByUserEmail(email);
        return authService.signOut(user);
    }
}
