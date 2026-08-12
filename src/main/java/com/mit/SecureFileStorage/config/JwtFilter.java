//package com.mit.SecureFileStorage.config;
//
//
//
//import com.mit.SecureFileStorage.auth.config.JWTTokenHelper;
//import com.mit.SecureFileStorage.service.UserDetailsServiceImpl;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//
//
//import java.io.IOException;
//
//public class JwtFilter extends GenericFilter {
//
//    private final JWTTokenHelper jwTokenHelper;
//    private final UserDetailsServiceImpl userDetailsService;
//
//    public JwtFilter(JWTTokenHelper jwTokenHelper, UserDetailsServiceImpl uds) {
//        this.jwTokenHelper = jwTokenHelper;
//        this.userDetailsService = uds;
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        String header = request.getHeader("Authorization");
//
//        if (header != null && header.startsWith("Bearer ")) {
//            String token = header.substring(7);
//            if (jwTokenHelper.validateToken(token)) {
//                String username = jwTokenHelper.getUsernameFromToken(token);
//                var userDetails = userDetailsService.loadUserByUsername(username);
//
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        chain.doFilter(req, res);
//    }
//}
