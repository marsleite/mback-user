package com.grupo29.mback.user.resource.repository.entity;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserType;
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
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private UserType userType;

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
                .userType(user.getUserType())
                .address(addressEntity)
                .build();
    }

    public User toDomain() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .userType(this.userType)
                .address(this.address.toDomain())
                .build();
    }
}
