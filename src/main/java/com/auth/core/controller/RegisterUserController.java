package com.auth.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.core.exception.UserException;
import com.auth.core.service.RegisterUserService;
import com.auth.core.to.controller.request.RegisterUserInfoRequestTO;
import com.auth.core.to.controller.response.UserResponseRequestTO;

@RestController
public class RegisterUserController {

	private final RegisterUserService registerUserService;

	public RegisterUserController(RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseRequestTO> registerUser(
			@RequestBody RegisterUserInfoRequestTO registerUserInfoRequestTO)
			throws UserException {
		return ResponseEntity.ok(registerUserService.registerUser(registerUserInfoRequestTO));
	}

}
