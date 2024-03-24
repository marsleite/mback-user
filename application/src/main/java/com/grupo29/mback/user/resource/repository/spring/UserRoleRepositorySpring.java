package com.grupo29.mback.user.resource.repository.spring;

import com.grupo29.mback.user.entities.UserRole;
import com.grupo29.mback.user.resource.repository.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepositorySpring extends JpaRepository<UserRoleEntity, Long> {
}
