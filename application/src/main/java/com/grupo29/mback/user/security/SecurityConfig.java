package com.grupo29.mback.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) ->
                        authz.requestMatchers("/api/users/admin/**").hasRole("ADMIN")
                                .requestMatchers("/mback-ecommerce/itens/registrar").hasRole("VENDEDOR")
                                .requestMatchers("/mback-ecommerce/itens/atualizar").hasRole("VENDEDOR")
                                .requestMatchers("/mback-ecommerce/itens/remover").hasRole("VENDEDOR")
                                .requestMatchers("/mback-ecommerce/shopping").hasRole("CLIENTE")
                                .requestMatchers("/mback-ecommerce/itens").hasAnyRole("VENDEDOR", "CLIENTE")
                                .requestMatchers("/mback/pagamento").hasRole("CLIENTE")
                                .requestMatchers("/mback/itens/**").hasAnyRole("VENDEDOR", "CLIENTE"))
                .httpBasic(withDefaults());
        return http.build();
    }
}
