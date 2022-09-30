package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain sfc(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests(req -> {
			req.antMatchers("/","/home","/css/**").permitAll();
			req.antMatchers("/normal/**").hasRole("NORMAL");
			req.antMatchers("/admin/**").hasRole("ADMIN");
			req.anyRequest().authenticated();
		}).formLogin();
		return security.build();
	}
	
	@Bean
	public PasswordEncoder pw() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsManager udm() {
		UserDetails taro = User.withUsername("taro")
				.password(pw().encode("pass"))
				.roles("NORMAL", "ADMIN")
				.build();
		
		UserDetails hana = User.withUsername("hana")
				.password(pw().encode("pass"))
				.roles("NORMAL")
				.build();
		
		UserDetails jiro = User.withUsername("jiro")
				.password(pw().encode("pass"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(taro, hana, jiro);
	}
}
