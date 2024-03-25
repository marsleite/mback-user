package com.grupo29.mback.user.resource.repository.entity;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`user`")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private List<UserRoleEntity> roles;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public static UserEntity fromDomain(User user) {
        AddressEntity addressEntity = AddressEntity.builder()
                .id(user.getAddress().getId())
                .street(user.getAddress().getStreet())
                .city(user.getAddress().getCity())
                .state(user.getAddress().getState())
                .cep(user.getAddress().getCep())
                .build();

        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(UserRoleEntity::fromDomain)
                        .collect(Collectors.toList()))
                .address(addressEntity)
                .build();
    }

    public User toDomain() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .roles(this.roles.stream().map(UserRoleEntity::toDomain)
                        .collect(Collectors.toList()))
                .address(this.address.toDomain())
                .build();
    }
}
