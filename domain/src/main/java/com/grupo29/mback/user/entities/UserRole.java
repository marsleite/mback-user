package com.grupo29.mback.user.entities;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRole {
    Long id;
    String name;
}
