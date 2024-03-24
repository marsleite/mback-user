package com.grupo29.mback.user.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRole {
    Long id;
    String name;
}
