package com.habit.core.authentication_habit_core.service.impl;

import static com.habit.core.authentication_habit_core.util.PasswordEncoderUtil.passwordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habit.core.authentication_habit_core.entities.User;
import com.habit.core.authentication_habit_core.enums.Role;
import com.habit.core.authentication_habit_core.enums.Status;
import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.repository.UserRepository;
import com.habit.core.authentication_habit_core.service.RegisterUserService;
import com.habit.core.authentication_habit_core.to.controller.request.RegisterUserInfoRequestTO;
import com.habit.core.authentication_habit_core.to.controller.response.UserResponseRequestTO;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

	private final UserRepository userRepository;

	public RegisterUserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(rollbackFor = UserException.class)
	public UserResponseRequestTO registerUser(RegisterUserInfoRequestTO registerUserInfoRequestTO) throws UserException {

		if (userRepository.findByUserName(registerUserInfoRequestTO.getUserName()).isPresent()) {
			throw new UserException("This username is not available");
		}

		if (userRepository.findByEmail(registerUserInfoRequestTO.getEmail()).isPresent()) {
			throw new UserException("Email is already registered");
		}

		User user = new User()
				.setUserName(registerUserInfoRequestTO.getUserName())
				.setEmail(registerUserInfoRequestTO.getEmail())
				.setPassword(passwordEncoder().encode(registerUserInfoRequestTO.getPassword()))
				.setRole(Role.USER)
				.setStatus(Status.ACTIVE);

		userRepository.save(user);

		return new UserResponseRequestTO(user.getUserName(), user.getEmail());
	}

}
