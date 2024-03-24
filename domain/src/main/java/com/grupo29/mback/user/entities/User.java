package com.grupo29.mback.user.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private Address address;
}
