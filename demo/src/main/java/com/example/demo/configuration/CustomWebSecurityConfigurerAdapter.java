package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
//@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); // #3  忽略任何以”/resources/”开头的请求
	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/signup", "/about").permitAll() // #4 任何人(包括没有经过验证的)都可以访问”/signup”和”/about”
				.antMatchers("/admin/**").hasRole("ADMIN") // #6 “/admin/”开头的URL必须要是管理员用户，譬如”ADMIN”用户 
				//我们使用了“hasRole()”方法，我们也没有添加”ROLE_”前缀，而在XML中我们则添加了。这也是我们应该知道的惯例:”hasRole()”会自动添加”ROLE_”前缀。如果你不想要“ROLE_”前缀，你可以使用”access()”方法
				.anyRequest().authenticated() // #7 所有其他的URL都需要用户进行验证
				.and()
				.formLogin() // #8  使用Java配置默认值设置了基于表单的验证。使用POST提交到”/login”时，需要用”username”和”password”进行验证。
				.loginPage("/login") // #9  注明了登陆页面，意味着用GET访问”/login”时，显示登陆页面
				.permitAll(); // #5  任何人(包括没有经过验证的)都可以访问”/login”和”/login?error”。permitAll()是指用户可以访问formLogin()相关的任何URL

	}
}
