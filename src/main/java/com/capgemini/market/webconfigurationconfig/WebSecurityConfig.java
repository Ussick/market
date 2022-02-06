package com.capgemini.market.webconfigurationconfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthProvider customAuthProvider;
    private final UserDetailsService userDetailsService;


    private final CustomFilter customFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("innn configure http" + " " + userDetailsService);
        http.httpBasic();
        http.addFilterAfter(customFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/login/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .csrf().disable()//без этого добавления не работает метод post
                    .formLogin()
//                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("innn configure auth");
        auth
                .authenticationProvider(customAuthProvider)//логинится и без этого метода с вызовом кастомного класса
                .userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//                .passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        int salt = ("myBestSolt".getBytes(StandardCharsets.UTF_8).length); // salt length in bytes
//        int hashLength = 16; // hash length in bytes
//        int parallelism = 1; // currently is not supported
//        int memory = 16; // memory costs
//        int iterations = 2;
//        return new Argon2PasswordEncoder(salt, hashLength, parallelism, memory, iterations);
//    }
}


