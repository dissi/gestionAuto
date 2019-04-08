package com.genieLogiciel.gestionAuto.config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dissirama
 */
//package org.o7planning.sbsecurity.config;
 
import com.genieLogiciel.gestionAuto.service.UserDetailsServiceImpl;
import com.genieLogiciel.gestionAuto.service.PersonnelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    PersonnelServiceImpl personnelServiceImpl;
    @Autowired
    FonctionIdToFonctionconverter fonctionIdToFonctionconverter;
 
    @Autowired
    ProduitIdToProduitconverter produitIdToProduitconverter;
 
    @Autowired
    AgentIdToAgentconverter agentIdToAgentconverter;
    
    @Autowired
    ZoneIdToZoneconverter zoneIdToZoneconverter;
    
    @Autowired
    EntrepotIdToEntrepotconverter entrepotIdToEntrepotconverter;
    
    @Autowired
    DemandeIdToDemandeconverter demandeIdToDemandeconverter;
 
 
    @Autowired
    VehiculeIdToVehiculeconverter vehiculeIdToVehiculeconverter;
 
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
     
     
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
 
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
 
        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_AGT', 'ROLE_ADMIN')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/agentTerrain").access("hasRole('ROLE_AGT')");
        http.authorizeRequests().antMatchers("/fonction","/fonction/listeFonction").access("hasRole('ROLE_ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
 
    }
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/adminlte/**").addResourceLocations("classpath:/static/adminlte/");
    }
    
    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(fonctionIdToFonctionconverter);
        registry.addConverter(agentIdToAgentconverter);
        registry.addConverter(zoneIdToZoneconverter);
        registry.addConverter(entrepotIdToEntrepotconverter);
        registry.addConverter(produitIdToProduitconverter);
        registry.addConverter(demandeIdToDemandeconverter);
        registry.addConverter(vehiculeIdToVehiculeconverter);
    }
     
}