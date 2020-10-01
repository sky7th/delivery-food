package com.sky7th.deliveryfood.config;

import com.sky7th.deliveryfood.utils.resolver.UserContextArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*")
        .allowCredentials(false)
        .maxAge(3600);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new UserContextArgumentResolver());
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public LiteDeviceResolver liteDeviceResolver() {
    return new LiteDeviceResolver();
  }
}
