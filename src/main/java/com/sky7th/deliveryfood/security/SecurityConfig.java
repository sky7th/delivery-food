package com.sky7th.deliveryfood.security;

import com.sky7th.deliveryfood.security.filter.JwtAuthorizationFilter;
import com.sky7th.deliveryfood.security.provider.MemberAuthenticationProvider;
import com.sky7th.deliveryfood.security.provider.OwnerAuthenticationProvider;
import com.sky7th.deliveryfood.security.provider.RiderAuthenticationProvider;
import com.sky7th.deliveryfood.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationEntryPoint jwtEntryPoint;
  private final JwtAuthorizationFilter jwtAuthorizationFilter;
  private final MemberAuthenticationProvider memberAuthenticationProvider;
  private final OwnerAuthenticationProvider ownerAuthenticationProvider;
  private final RiderAuthenticationProvider riderAuthenticationProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(memberAuthenticationProvider);
    auth.authenticationProvider(ownerAuthenticationProvider);
    auth.authenticationProvider(riderAuthenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .headers()
        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"))
        .frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers(
            "/favicon.ico",
            "/**/*.json",
            "/**/*.xml",
            "/**/*.properties",
            "/**/*.woff2",
            "/**/*.woff",
            "/**/*.ttf",
            "/**/*.ttc",
            "/**/*.ico",
            "/**/*.bmp",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.jpeg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/h2-console", "/h2-console**", "/h2-console/", "/h2-console/**").permitAll()
        .antMatchers("/", "/error", "/**/login", "/**/register").permitAll()
        .antMatchers("/members/**").hasRole(UserRole.ROLE_MEMBER.getRoleName())
        .antMatchers("/owners/**").hasRole(UserRole.ROLE_OWNER.getRoleName())
        .antMatchers("/riders/**").hasRole(UserRole.ROLE_RIDER.getRoleName())
        .anyRequest().authenticated();

    http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
