package com.bayram.auth;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserDatailsService implements UserDetailsService {
	
	
	private Map<String, String> users=new HashMap<String, String>();
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@PostConstruct
	public void init() {
		users.put("sbayram", encoder.encode("12345"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(users.containsKey(username)) {
			
			return new User(username, users.get(username),new ArrayList<>());
		}
		throw new UsernameNotFoundException(username);
	}

}
