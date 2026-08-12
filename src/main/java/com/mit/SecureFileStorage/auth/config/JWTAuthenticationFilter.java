package com.mit.SecureFileStorage.auth.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

	
	  private final UserDetailsService userDetailsService;
	  private final JWTTokenHelper jwtTokenHelper;
	  
	  public JWTAuthenticationFilter(JWTTokenHelper jwtTokenHelper,UserDetailsService userDetailsService) {
	        this.jwtTokenHelper = jwtTokenHelper;
	        this.userDetailsService = userDetailsService;
	    }

	
	  @Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	          throws ServletException, IOException {

	      String authHeader = request.getHeader("Authorization");

	      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	          filterChain.doFilter(request, response);
	          return;
	      }

	      try {
	          //  Use getToken() to strip "Bearer " safely
	          String authToken = jwtTokenHelper.getToken(request);
	          System.out.println("Authorization Header: " + authHeader);
	          System.out.println("Token: " + authToken);

	          if (authToken != null) {
	              //  Just in case, re-strip if not already stripped
	              if (authToken.startsWith("Bearer ")) {
	                  authToken = authToken.substring(7);
	              }

	              String userName = jwtTokenHelper.getUserNameFromToken(authToken);
	              System.out.println("Username from token: " + userName);

	              if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                  UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
	                  System.out.println("Authorities: " + userDetails.getAuthorities());

	                  if (jwtTokenHelper.validateToken(authToken, userDetails)) {
	                      UsernamePasswordAuthenticationToken authenticationToken =
	                              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                      authenticationToken.setDetails(new WebAuthenticationDetails(request));
	                      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	                  }
	              }
	          }

	      } catch (Exception e) {
	          System.err.println("JWT Filter Error: " + e.getMessage());
	      }

	      filterChain.doFilter(request, response);
	  }

}
