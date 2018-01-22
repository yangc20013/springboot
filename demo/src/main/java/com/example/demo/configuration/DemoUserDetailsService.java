package com.example.demo.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.models.Account;
import com.example.demo.repository.AccountRepo;

public class DemoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = accountRepo.findByUsername(username);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		User user = new User(username, account.getPassword(), true, true, true, true, authorities);

		return user;
	}

//	public static void main(String[] args) {
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
//		String result = passwordEncoder.encode("123");
//		System.out.println(result);//$2a$04$hAeloX2naYgB0fIxN83joeCEKas8LFQ9prZgkiHsA8JxIS8i11eB2
//	}

}
