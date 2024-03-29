package com.example.privateadsystem.config;

import com.example.privateadsystem.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/sorts/**", "/searches/**", "/chats/**",
                        "/users/**/**", "/posts/**", "/categories/**", "/subCategories/**").permitAll()
                .antMatchers("/users/**", "/posts/**").hasAnyRole("USER")
                .antMatchers("/users/**", "/categories/**",
                        "/subCategories/**").hasAnyRole("ADMIN")
                .antMatchers("/auth").anonymous()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService);
    }
}
