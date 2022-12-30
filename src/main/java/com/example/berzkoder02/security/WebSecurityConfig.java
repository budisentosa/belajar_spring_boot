package com.example.berzkoder02.security;

import com.example.berzkoder02.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// ref : https://javatechonline.com/spring-security-userdetailsservice-using-spring-boot-3/#Step4_Create_AppConfig_class_to_instantiate_BCryptPasswordEncoder
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/welcome", "/api/users/register").permitAll()
                .anyRequest().authenticated()
                .and()
	        	.authenticationProvider(daoAuthenticationProvider());

        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return  provider;
    }

//    @Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.authorizeHttpRequests()
//		.requestMatchers("/home","/register","/saveUser").permitAll()
//		.requestMatchers("/welcome").authenticated()
//		.requestMatchers("/admin").hasAuthority("Admin")
//		.requestMatchers("/mgr").hasAuthority("Manager")
//		.requestMatchers("/emp").hasAuthority("Employee")
//		.requestMatchers("/hr").hasAuthority("HR")
//		.requestMatchers("/common").hasAnyAuthority("Employeee,Manager,Admin")
//		.anyRequest().authenticated()

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//            return (web) -> web.ignoring().antMatchers("/js/**", "/images/**");
//    }

//    http
//    .antMatcher("/api/**")
//        .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/api/user").permitAll()
//            .anyRequest().hasRole("USER")
//    @Override 50.28
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().
//    }

//    @Configuration
//public class SecurityConfiguration {
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//    }
//
//}


}
