package com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		//不走安全认证
		web.ignoring().antMatchers("/tablet/*", "/tablet/*");
		web.ignoring().antMatchers("/tablet/**/*", "/tablet/**/*");
		web.ignoring().antMatchers("/keepalive.html*", "/keepalive.html*");
		web.ignoring().antMatchers("/**/*.html*", "/**/*.html*");
		web.ignoring().antMatchers("/Public/**/*", "/Public/**/*");
		web.ignoring().antMatchers("/**/*.ico", "/**/*.ico");
		
		web.ignoring().antMatchers("/save","/get");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		//  /和/home不需要登录，其他的需要登录
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	// 前端送 username=user   password=111111有效
        auth
            .inMemoryAuthentication()
                .withUser("user").password("111111").roles("USER");
        
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setPasswordEncoder(passwordEncoder());
//		authProvider.setUserDetailsService(userDetailsService());
//
//		auth.authenticationProvider(authProvider);
    }
    
//    @Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder passwordEncoder = new DESPasswordEncoder();
//		return passwordEncoder;
//	}
}
