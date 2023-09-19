package com.local.localgram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Ioc
@EnableWebSecurity // 해당 파일로 시큐리티 활성화
public class SecurityConfig {

    // 비밀번호 암호화
    @Bean
    BCryptPasswordEncoder encoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") // GET
                .loginProcessingUrl("/auth/signin") // POST -> 스프링시큐리티가 로그인 프로세스 진행
                .defaultSuccessUrl("/")
                .and()
                .build();
    }

}
