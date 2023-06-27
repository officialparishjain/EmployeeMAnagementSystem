package com.parishjain.EMS.controllers;

import com.parishjain.EMS.DTO.SignInInputDto;
import com.parishjain.EMS.DTO.SignInOutputDto;
import com.parishjain.EMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {



    @Autowired
    UserService userService;

    // Sign in
    @PostMapping(value = "/signIn")
    public ResponseEntity<SignInOutputDto> signIn(@RequestBody SignInInputDto signInInputDto){
        return  userService.signIn(signInInputDto);
    }

    // sign Out
    @DeleteMapping(value = "/signOut/{email}")
    public ResponseEntity<String> signOut(@PathVariable String email){
        return userService.signOut(email);
    }
}
