package com.example.demo.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class DemoSecurityAdapter extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		// web.ignoring().antMatchers("/**/*.css");
		// web.ignoring().antMatchers("/**/*.js");
	}
	
	@Bean
	public DemoUserPasswordAuthFilter demoAuthFilter() throws Exception {
		DemoUserPasswordAuthFilter filter = new DemoUserPasswordAuthFilter();
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		filter.setAuthenticationManager(this.authenticationManager());
		filter.setAuthenticationSuccessHandler(new DemoLoginSuccessHandle());//验证成功处理
		filter.setAuthenticationFailureHandler(new DemoLoginFailureHandle());//验证失败处理
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 关闭csrf
		http.csrf().disable();
		// 在UsernamePasswordAuthenticationFilter.class 之前加自定义过滤器,验证用户名密码
//		http.addFilterBefore(demoAuthFilter(), UsernamePasswordAuthenticationFilter.class);

		http.authorizeRequests().antMatchers("/", "/home").permitAll() // 所有都可以访问 "/","/home"
				.antMatchers("/admin").hasAnyRole("ADMIN") // ADMIN 角色才能访问"/admin"
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")// ADMIN 并且DBA才能访问"/db/**"
				.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/hello")
				.failureUrl("/login?error=1")
//				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
				.permitAll()
			.and()
			.logout().permitAll();
		
		http.rememberMe();
		// session管理
		// session失效后跳转
		http.sessionManagement().invalidSessionUrl("/login");
		// 只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
		http.sessionManagement().maximumSessions(1).expiredUrl("/login");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
		return passwordEncoder;
	}
	
	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetailsService userDetailsService = new DemoUserDetailsService();
		return userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());//密码加密
		authProvider.setUserDetailsService(userDetailsService());

		auth.authenticationProvider(authProvider);
	}

}
