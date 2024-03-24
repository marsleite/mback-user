package com.grupo29.mback.user.resource.repository.spring;

import com.grupo29.mback.user.resource.repository.entity.AddressEntity;
import com.grupo29.mback.user.resource.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositorySpring extends JpaRepository<AddressEntity, Long> {
}
