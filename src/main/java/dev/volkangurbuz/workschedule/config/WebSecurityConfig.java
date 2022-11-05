package dev.volkangurbuz.workschedule.config;

import dev.volkangurbuz.workschedule.model.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/workerInfo")
        .authenticated()
        .antMatchers("/register")
        .permitAll()
        .antMatchers("/admin")
        .hasAuthority(ERole.ROLE_ADMIN.name()).antMatchers("/getWorkerRoles").hasAuthority(ERole.ROLE_USER.name())
        .and()
        .httpBasic();
    return httpSecurity.build();
  }
}
