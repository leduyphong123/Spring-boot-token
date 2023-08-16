package com.codegym.token.config;

import com.codegym.token.entity.Acount;
import com.codegym.token.repository.AcountRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtTokenFilters extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AcountRepository acountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        String userName = null;
        String jwtToken = null;
        if (token != null && token.startsWith(BEARER)){
            jwtToken = token.substring(7);
            if (jwtTokenUtil.isTokenExpired(jwtToken)) {
                throw new ExpiredJwtException(null, null, "Jwt expired");
            }
                userName = jwtTokenUtil.getUsernameFromToken(jwtToken);

        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        if (userName != null){
            Acount acount = acountRepository.findByUserName(userName);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    acount,
                    null,
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);

    }
}
