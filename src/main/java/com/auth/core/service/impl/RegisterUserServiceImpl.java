package com.auth.core.service.impl;

import static com.auth.core.util.PasswordEncoderUtil.passwordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.core.entities.User;
import com.auth.core.enums.Role;
import com.auth.core.enums.Status;
import com.auth.core.exception.UserException;
import com.auth.core.repository.UserRepository;
import com.auth.core.service.RegisterUserService;
import com.auth.core.to.controller.request.RegisterUserInfoRequestTO;
import com.auth.core.to.controller.response.UserResponseRequestTO;

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
