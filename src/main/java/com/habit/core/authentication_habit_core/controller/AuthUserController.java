package com.habit.core.authentication_habit_core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.habit.core.authentication_habit_core.constant.IHeaderConstants;
import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.service.AuthenticationService;
import com.habit.core.authentication_habit_core.to.controller.request.AuthenticationRequestTO;

@RestController
public class AuthUserController {

	private final AuthenticationService authenticationService;

	public AuthUserController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/login")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequestTO authenticationRequestTO) throws UserException {
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION,
						IHeaderConstants.BEARER.concat(authenticationService.login(authenticationRequestTO)))
				.build();
	}

	@PostMapping("/logout")
	@ResponseStatus(value = HttpStatus.OK)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		authenticationService.logout(request, response);
	}

	@PostMapping("/check")
	@ResponseStatus(value = HttpStatus.OK)
	public void check(HttpServletRequest request, HttpServletResponse response) {
	}

}
