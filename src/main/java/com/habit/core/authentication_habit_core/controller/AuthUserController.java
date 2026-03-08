package com.habit.core.authentication_habit_core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.habit.core.authentication_habit_core.exception.RefreshTokenException;
import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.service.AuthenticationService;
import com.habit.core.authentication_habit_core.to.controller.request.AuthenticationRequestTO;
import com.habit.core.authentication_habit_core.to.controller.request.RefreshTokenRequestTO;
import com.habit.core.authentication_habit_core.to.controller.response.AuthenticationResponseTO;

@RestController
public class AuthUserController {

	private final AuthenticationService authenticationService;

	public AuthUserController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseTO> login(@RequestBody AuthenticationRequestTO authenticationRequestTO)
			throws UserException {
		return ResponseEntity.ok(authenticationService.login(authenticationRequestTO));
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthenticationResponseTO> refresh(@RequestBody RefreshTokenRequestTO refreshTokenRequestTO)
			throws RefreshTokenException {
		return ResponseEntity.ok(authenticationService.refresh(refreshTokenRequestTO));
	}

	@PostMapping("/logout")
	@PreAuthorize("hasAuthority('developers:read')")
	@ResponseStatus(value = HttpStatus.OK)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		authenticationService.logout(request, response);
	}

	@PostMapping("/check")
	@PreAuthorize("hasAuthority('developers:read')")
	@ResponseStatus(value = HttpStatus.OK)
	public void check(HttpServletRequest request, HttpServletResponse response) {
	}



}
