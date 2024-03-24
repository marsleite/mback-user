package com.grupo29.mback.user.resource.repository.entity;

import com.grupo29.mback.user.entities.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public static UserRoleEntity fromDomain(UserRole role) {
        return UserRoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public UserRole toDomain() {
        return UserRole.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
