package com.habit.core.authentication_habit_core.security.jwt;


import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.habit.core.authentication_habit_core.exception.JwtAuthenticationException;

import lombok.Getter;

@Component
@Getter
public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Value("${filter.exclusion.urls}")
	private List<String> exclusionUrls;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String servletPath = request.getServletPath();

		if (exclusionUrls.contains(servletPath)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
		try {
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				if (authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (JwtAuthenticationException e) {
			SecurityContextHolder.clearContext();
			((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
			throw new JwtAuthenticationException("JWT token is expired or invalid");
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}


}
