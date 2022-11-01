package com.suninjjang.oasis.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suninjjang.oasis.global.security.filter.ExceptionFilter;
import com.suninjjang.oasis.global.security.filter.JwtTokenFilter;
import com.suninjjang.oasis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .cors().disable() //cors 정책 비활성화
                .csrf().disable() // 세션을 사용하지 않고 JWT 토큰을 활용하여 진행, csrf토큰검사를 비활성화
                .formLogin().disable() // 접근이 차단된 페이지 클릭시 이동하는거 비활설화

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() //cors preflight 요청에 대해 401 에러 해결을 위해

                // auth
                .antMatchers("/auth/refresh").permitAll()
                .antMatchers("/auth/signup").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/logout").authenticated()
                .antMatchers("/auth/find/id").permitAll()

                // user
                .antMatchers("/user/withdrawal").authenticated()
                .antMatchers("/user/setting/").authenticated()
                .antMatchers("/user/setting/change/nickname").authenticated()
                .antMatchers("/user/setting/change/password").authenticated()
                .antMatchers("/user/setting/reminder/anniversarytime").authenticated()

                // diary
                .antMatchers("/diary/create").authenticated()
                .antMatchers("/diary/edit/**").authenticated()
                .antMatchers("/diary/detail/**").authenticated()
                .antMatchers("/diary/delete/**").authenticated()
                .antMatchers("/diary/list").authenticated()

                // question

                // mypage

                // heart

                .anyRequest().denyAll()
                .and()




                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                .and()

                .addFilterAfter(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new ExceptionFilter(objectMapper),UsernamePasswordAuthenticationFilter.class)
                .build();




    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
