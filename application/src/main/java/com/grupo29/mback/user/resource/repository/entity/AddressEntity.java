package com.grupo29.mback.user.resource.repository.entity;

import com.grupo29.mback.user.entities.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String cep;

    @OneToOne(mappedBy = "address")
    private UserEntity user;

    public Address toDomain() {
        return Address.builder()
                .id(this.id)
                .street(this.street)
                .city(this.city)
                .state(this.state)
                .cep(this.cep)
                .build();
    }
}
