package com.habit.core.authentication_habit_core.service;

import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.to.controller.request.RegisterUserInfoRequestTO;
import com.habit.core.authentication_habit_core.to.controller.response.UserResponseRequestTO;

public interface RegisterUserService {

	UserResponseRequestTO registerUser(RegisterUserInfoRequestTO registerUserInfoRequestTO) throws UserException;

}
