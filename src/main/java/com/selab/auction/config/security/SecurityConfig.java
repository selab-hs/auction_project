package com.selab.auction.config.security;

import com.selab.auction.member.signin.security.AuthEntryPoint;
import com.selab.auction.member.signin.security.TokenAuthenticationFilter;
import com.selab.auction.member.signin.service.CustomMemberDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomMemberDetailService customUserDetailService;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: 2022. 11. 15. 추후 antMatchers 재설정해야 함. (USER, ADMIN ROLE 구분) -sonrose
        http
                .cors()
        .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling().authenticationEntryPoint(new AuthEntryPoint())
        .and()
                .authorizeRequests()
                    .antMatchers("/","/error","/favicon.ico", "/**/*.png", "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()
/*                .antMatchers("/api/v1/auction/sign-up").permitAll()
                .antMatchers("/api/v1/auction/sign-in").permitAll()
                .antMatchers("/api/v1/auction/{memberId}").access("hasRole('USER')")*/
                .anyRequest().permitAll();
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}