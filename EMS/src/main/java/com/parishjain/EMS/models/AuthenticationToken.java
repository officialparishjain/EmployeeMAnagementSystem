package com.parishjain.EMS.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authTokenId;
    private String authToken;
    private LocalDate authTokenDate;

    @ManyToOne
    @JoinColumn(nullable = false,name = "fk_user_id")
    private User user;


    public AuthenticationToken( User authUser) {
        this.authToken = UUID.randomUUID().toString();
        this.authTokenDate = LocalDate.now();
        this.user = authUser;
    }
}
