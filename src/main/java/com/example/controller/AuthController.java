package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bayram.auth.TokenManager;
import com.bayram.model.RequestLogin;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private AuthenticationManager autManager;

	@PostMapping
	public ResponseEntity<String> login(@RequestBody RequestLogin requestLogin) {

		try {
			autManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestLogin.getUsername(), requestLogin.getPassword()));

			return ResponseEntity.ok(tokenManager.generateToken(requestLogin.getUsername()));
		} catch (Exception e) {
			throw e;
		}
	}
}
