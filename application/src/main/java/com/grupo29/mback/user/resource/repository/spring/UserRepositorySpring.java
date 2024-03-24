package com.grupo29.mback.user.resource.repository.spring;

import com.grupo29.mback.user.resource.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorySpring extends JpaRepository<UserEntity, Long> {
}
