package com.ijse.posDatabase.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ijse.posDatabase.Service.UserDetailsService;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder for strong security
    }
    @Bean
public JwtTokenProvider jwtTokenProvider() {
    // Configure JwtTokenProvider with secret key, validity, etc.
    return new JwtTokenProvider("your-secret-key", 3600L); // Example configuration
}
@Bean
public JWTAuthenticationFilter jwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
    return new JWTAuthenticationFilter(tokenProvider);
}

@Bean
public JWTAuthenticationFilter jwtAuthorizationFilter(ApplicationContext context) {
    return new JWTAuthenticationFilter(context);
}



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login").permitAll() // Allow unauthenticated access to /login
            .antMatchers("/admin/**").hasRole("ADMIN") // Require ADMIN role for /admin endpoints
            .anyRequest().authenticated() // Require authentication for all other requests
            .and()
            .formLogin() // Enable form-based login
            .loginPage("/login") // Set the login page
            .permitAll() // Allow unauthenticated access to the login page
            .and()
            .logout() // Enable logout functionality
            .permitAll(); // Allow unauthenticated access to the logout endpoint
            Filter jwtAuthenticationFilter;
            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
Filter jwtAuthorizationFilter;
http.addFilter(jwtAuthorizationFilter);

    }
}
