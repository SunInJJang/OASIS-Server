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
                .antMatchers("/auth/refresh").permitAll() // token 재발급
                .antMatchers("/auth/signup").permitAll() // 회원가입
                .antMatchers("/auth/login").permitAll() // 로그인
                .antMatchers("/auth/logout").authenticated() // 로그아웃
                .antMatchers("/auth/find/id").permitAll() // 아이디 찾기(이메일통해)

                // user
                .antMatchers("/user/withdrawal").authenticated() // 회원탈퇴
                .antMatchers("/user/setting/").authenticated() // 설정 메인
                .antMatchers("/user/setting/change/nickname").authenticated() // 닉네임 변경
                .antMatchers("/user/setting/change/password").authenticated() // 비밀번호 변경
                .antMatchers("/user/setting/reminder/anniversarytime").authenticated() // 기념일 알림 시간 변경

                // diary
                .antMatchers("/diary/create").authenticated() // 일기 생성
                .antMatchers("/diary/edit/**").authenticated() // 일기 수정
                .antMatchers("/diary/detail/**").authenticated() // 일기 상세
                .antMatchers("/diary/delete/**").authenticated() // 일기 삭제
                .antMatchers("/diary/list").authenticated() // 일기 목록

                // question
                .antMatchers("/question/**").authenticated() // 질문 답변
                .antMatchers("/question/detail/**").authenticated() // 질문 상세
                .antMatchers("/question/list/**").authenticated() // 질문 목록

                // mypage
                .antMatchers("/mypage/").authenticated() // 마이페이지(메인페이지)

                // heart
                .antMatchers("/heart/").authenticated() // 하트 페이지

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
