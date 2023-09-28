package com.local.localgram.config;

import com.local.localgram.config.oauth2.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration // Ioc
@EnableWebSecurity // 해당 파일로 시큐리티 활성화
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
                    .oauth2Login() // form로그인도 하는데, oauth2 로그인도 할꺼야!
                    .userInfoEndpoint() // oauth2 로그인을 하면 최종응답을 회원정보로 바로 받을 수 있다.
                    .userService(oAuth2DetailsService);
        return http.build();
    }

}
