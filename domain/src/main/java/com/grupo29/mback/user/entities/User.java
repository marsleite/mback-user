package com.grupo29.mback.user.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<UserRole> roles;
    private Address address;
}
