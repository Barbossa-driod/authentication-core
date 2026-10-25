package com.auth.core.service.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.core.entities.RefreshToken;
import com.auth.core.entities.User;
import com.auth.core.exception.RefreshTokenException;
import com.auth.core.exception.UserException;
import com.auth.core.repository.UserRepository;
import com.auth.core.security.jwt.JwtTokenProvider;
import com.auth.core.security.jwt.RefreshTokenProvider;
import com.auth.core.service.AuthenticationService;
import com.auth.core.to.controller.request.AuthenticationRequestTO;
import com.auth.core.to.controller.request.RefreshTokenRequestTO;
import com.auth.core.to.controller.response.AuthenticationResponseTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final RefreshTokenProvider refreshTokenProvider;
	private final AuthenticationManager authenticationManager;

	public AuthenticationServiceImpl(UserRepository userRepository,
			JwtTokenProvider jwtTokenProvider,
			RefreshTokenProvider refreshTokenProvider,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.refreshTokenProvider = refreshTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	// many logic create many refresh tokens
	@Override
	public AuthenticationResponseTO login(AuthenticationRequestTO authenticationRequestTO) throws UserException {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestTO.getEmail(),
					authenticationRequestTO.getPassword()));

			User user = userRepository.findByEmail(authenticationRequestTO.getEmail())
					.orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

			String accessToken = jwtTokenProvider.createToken(authenticationRequestTO.getEmail(), user.getRole().toString());
			String refreshToken = refreshTokenProvider.createToken(user);

			return new AuthenticationResponseTO(accessToken, refreshToken);

		} catch (AuthenticationException e) {

			throw new UserException("Invalid email/password combination");
		}

	}

	@Override
	@Transactional
	public AuthenticationResponseTO refresh(RefreshTokenRequestTO refreshTokenRequestTO) throws RefreshTokenException {

		String refreshToken = refreshTokenRequestTO.getRefreshToken();

		RefreshToken foundRefreshToken = refreshTokenProvider.validateToken(refreshToken);
		User user = foundRefreshToken.getUser();
		String email = user.getEmail();
		String role = user.getRole().toString();

		refreshTokenProvider.revokeToken(refreshToken);

		String updatedAccessToken = jwtTokenProvider.createToken(email, role);
		String updatedRefreshToken = refreshTokenProvider.createToken(user);

		return new AuthenticationResponseTO(updatedAccessToken, updatedRefreshToken);
	}

	// delete after logout refresh token
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
	}

}
