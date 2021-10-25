package com.github.korenevaS.queue.security;

import com.github.korenevaS.queue.enums.Role;
import com.github.korenevaS.queue.filter.CustomAuthenticationFilter;
import com.github.korenevaS.queue.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8081"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Origin", "Content-Type", "Accept"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login**", "/api/token/refresh**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/save").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/get-free-slots").hasAnyAuthority(String.valueOf(Role.ROLE_USER));
        http.authorizeRequests().antMatchers(POST, "/api/take-slot").hasAnyAuthority(String.valueOf(Role.ROLE_USER));
        http.authorizeRequests().antMatchers(POST, "/api/approve/**").hasAnyAuthority(String.valueOf(Role.ROLE_USER));
        http.authorizeRequests().antMatchers(POST, "/api/my-receipt/**").hasAnyAuthority(String.valueOf(Role.ROLE_USER));
        http.authorizeRequests().antMatchers(POST, "/api/nearest-receipt").hasAnyAuthority(String.valueOf(Role.ROLE_ADMIN));
        http.authorizeRequests().antMatchers(POST, "/api/use-receipt").hasAnyAuthority(String.valueOf(Role.ROLE_ADMIN));
        http.authorizeRequests().antMatchers(POST, "/api/remove-receipt").hasAnyAuthority(String.valueOf(Role.ROLE_ADMIN));
        http.authorizeRequests().antMatchers(POST, "/api/users").hasAnyAuthority(String.valueOf(Role.ROLE_ADMIN));
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
