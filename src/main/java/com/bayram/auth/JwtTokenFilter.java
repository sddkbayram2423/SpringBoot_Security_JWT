package com.bayram.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private TokenManager tokenManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token=null;
		String username=null;
		
		
		final String autheader=request.getHeader("Authorization");
		
		if(autheader!=null&&autheader.contains("Bearer")) {
			token=autheader.substring(7);
			try {
				username=tokenManager.getUserNameFromToken(token);
			} catch (Exception e) {
				
				System.out.println(e.getLocalizedMessage());
			}
		}
		if(username!=null
				&&token!=null
				&&SecurityContextHolder.getContext().getAuthentication()==null) {
			
		UsernamePasswordAuthenticationToken  authenticationToken=new UsernamePasswordAuthenticationToken(username, null,new ArrayList<>());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
