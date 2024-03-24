package com.grupo29.mback.user.beans;

import com.grupo29.mback.user.gateway.UserRepositoryGateway;
import com.grupo29.mback.user.usecase.CreateUserUseCase;
import com.grupo29.mback.user.usecase.DeleteUserUseCase;
import com.grupo29.mback.user.usecase.FindUserUseCase;
import com.grupo29.mback.user.usecase.UpdateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDomains {

    @Bean
    public CreateUserUseCase createUser(UserRepositoryGateway userRepositoryGateway) {
        return new CreateUserUseCase(userRepositoryGateway);
    }

    @Bean
    public UpdateUserUseCase updateUser(UserRepositoryGateway userRepositoryGateway) {
        return new UpdateUserUseCase(userRepositoryGateway);
    }

    @Bean
    public FindUserUseCase findUser(UserRepositoryGateway userRepositoryGateway) {
        return new FindUserUseCase(userRepositoryGateway);
    }

    @Bean
    public DeleteUserUseCase deleteUser(UserRepositoryGateway userRepositoryGateway) {
        return new DeleteUserUseCase(userRepositoryGateway);
    }

}

