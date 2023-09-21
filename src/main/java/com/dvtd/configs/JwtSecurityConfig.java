/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.configs;

/**
 *
 * @author ACER
 */
import com.dvtd.filters.CustomAccessDeniedHandler;
import com.dvtd.filters.JwtAuthenticationTokenFilter;
import com.dvtd.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author huu-thanhduong
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dvtd.controllers",
    "com.dvtd.repository",
    "com.dvtd.service",
    "com.dvtd.components"})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable crsf cho đường dẫn /rest/**
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/user-register/").permitAll();
        http.authorizeRequests().antMatchers("/api/shipper-register/").permitAll();
     
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/shippers/**/reviews/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/shippers/**/reviews/create/").access("hasRole('ROLE_USER')");
        
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/orders/**").permitAll();
        http.authorizeRequests().antMatchers("/api/orders/**/update-status/").permitAll();
        
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/orders/**/choose-shipper/**/").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/orders/**/bids/create/").access("hasRole('ROLE_SHIPPER')");
        http.authorizeRequests().antMatchers("/api/orders/**/bids/**/").permitAll();
        

        
        
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_SHIPPER')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
