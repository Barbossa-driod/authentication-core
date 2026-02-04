package com.habit.core.authentication_habit_core.service.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.habit.core.authentication_habit_core.entities.User;
import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.repository.UserRepository;
import com.habit.core.authentication_habit_core.security.jwt.JwtTokenProvider;
import com.habit.core.authentication_habit_core.service.AuthenticationService;
import com.habit.core.authentication_habit_core.to.controller.request.AuthenticationRequestTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	public AuthenticationServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String login(AuthenticationRequestTO authenticationRequestTO) throws UserException {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestTO.getEmail(),
					authenticationRequestTO.getPassword()));

			User user = userRepository.findByEmail(authenticationRequestTO.getEmail())
					.orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

			return jwtTokenProvider.createToken(authenticationRequestTO.getEmail(), user.getRole().toString());

		} catch (AuthenticationException e) {

			throw new UserException("Invalid email/password combination");
		}

	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
	}

}
