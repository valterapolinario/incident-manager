package com.br.manager.utils.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    public static final String AUTH = "/auth";
    public static final String AUTHORIZATION = "Authorization";
    public static final String PREFIX = "Bearer ";
    private final JWTUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtRequestFilter(JWTUtils jwtUtils, @Lazy UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().startsWith(AUTH);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(AUTHORIZATION);

        String username = null;
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX)) {
            token = authorizationHeader.replace(PREFIX,EMPTY);
            username = jwtUtils.extractUsername(token);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails authUser = userDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(token, authUser)) {
                UsernamePasswordAuthenticationToken userAuth =
                        new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
                userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userAuth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
