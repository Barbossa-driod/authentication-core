package com.habit.core.authentication_habit_core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.service.RegisterUserService;
import com.habit.core.authentication_habit_core.to.controller.request.RegisterUserInfoRequestTO;
import com.habit.core.authentication_habit_core.to.controller.response.UserResponseRequestTO;

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
