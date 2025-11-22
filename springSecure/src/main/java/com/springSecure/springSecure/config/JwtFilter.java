package com.springSecure.springSecure.config;

import com.springSecure.springSecure.service.JwtService;
import com.springSecure.springSecure.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;
   private MyUserDetailsService myUserDetailsService;
    public JwtFilter(JwtService jwtService, MyUserDetailsService myUserDetailsService) {
        this.jwtService = jwtService;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
         if (authorization!=null & authorization.startsWith("Bearer ")){
             String substring = authorization.substring(7);
             String userName = jwtService.validateToken(substring);
             if (userName!=null ){
                 UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
                 // have to set the context so that urls can be accessed
                 var authtoken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                 authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authtoken);
             }
         }
        filterChain.doFilter(request, response);

    }
}
