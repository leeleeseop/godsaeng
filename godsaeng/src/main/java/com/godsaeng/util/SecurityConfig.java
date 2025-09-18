package com.godsaeng.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/member/**", "/css/**", "/js/**", "/images/**").permitAll()
	            .anyRequest().permitAll()
	        )
	        .formLogin(form -> form
	            .loginPage("/member/login")
	            .loginProcessingUrl("/member/login") 
	            .usernameParameter("memberId")
	            .passwordParameter("memberPw")
	            .defaultSuccessUrl("/member/login", true)
	            .failureUrl("/member/login?error=true")
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/member/logout")
	            .logoutSuccessUrl("/")
	            .permitAll()
	        )
	        .csrf(csrf -> csrf.disable());

	    return http.build();
	}

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
