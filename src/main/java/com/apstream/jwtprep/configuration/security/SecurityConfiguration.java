package com.apstream.jwtprep.configuration.security;

import com.apstream.jwtprep.filter.CustomAuthenticationFiler;
import com.apstream.jwtprep.filter.CustomAuthorizationFilter;
import com.apstream.jwtprep.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


  private final PasswordEncoder passwordEncoder;

    @Autowired
    private final CustomUserDetailService userService;

    public SecurityConfiguration(PasswordEncoder passwordEncoder, CustomUserDetailService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .authenticationProvider(authenticationProvider());

        auth
                .userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFiler authenticationFiler = new CustomAuthenticationFiler(authenticationManagerBean());

        authenticationFiler.setFilterProcessesUrl("/api/login");

        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/signup/**", "/api/login/**", "/api/test/**").permitAll()
                .antMatchers("/connection/main").hasAnyRole("ROLE_USER", "ROLE_SUPER_ADMIN")
                .antMatchers("/connection/user").hasAnyRole("ROLE_ADMIN", "ROLE_SUPPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(authenticationFiler)
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        ;

//                .and()
//                .httpBasic();


    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//           DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//           provider.setPasswordEncoder(passwordEncoder);
//           provider.setUserDetailsService(userService);
//
//           return provider;
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
