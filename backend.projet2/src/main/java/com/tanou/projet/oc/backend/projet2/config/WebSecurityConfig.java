package com.tanou.projet.oc.backend.projet2.config;

import com.tanou.projet.oc.backend.projet2.security.AuthEntryPointJwt;
import com.tanou.projet.oc.backend.projet2.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private AuthEntryPointJwt authEntryPoint;

  @Autowired
  public WebSecurityConfig(AuthEntryPointJwt authEntryPoint) {
    this.authEntryPoint = authEntryPoint;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf((csrf) -> csrf.disable())
      .exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(authEntryPoint))
      .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests((authorize) -> {
        authorize.requestMatchers(
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "/swagger-ui.html",
          "/swagger-resources/**",
          "/webjars/**",
          "/error",
          "/uploads/**"
        ).permitAll();
        authorize.requestMatchers("/api/auth/register", "/api/auth/login").permitAll();
        authorize.anyRequest().authenticated();
      })
      .httpBasic(Customizer.withDefaults());

    http.addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public JwtAuthFilter JWTAuthenticationFilter() {
    return new JwtAuthFilter();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
