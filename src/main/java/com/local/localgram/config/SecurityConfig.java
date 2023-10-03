package com.local.localgram.config;

import com.local.localgram.config.oauth2.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration // Ioc
@EnableWebSecurity // 해당 파일로 시큐리티
public class SecurityConfig {

    private final OAuth2DetailsService oAuth2DetailsService;

    // 비밀번호 암호화
    @Bean
    BCryptPasswordEncoder encoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                    .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/auth/signin") // GET
                    .loginProcessingUrl("/auth/signin") // POST -> 스프링시큐리티가 로그인 프로세스 진행
                    .defaultSuccessUrl("/")
                .and()
                    .oauth2Login() // OAuth2 를 통한 로그인 사용
                    .userInfoEndpoint() // 사용자가 로그인에 성공하였을 경우,
                    .userService(oAuth2DetailsService); // 해당 서비스 로직을 타도록 설정
        return http.build();
    }

}
