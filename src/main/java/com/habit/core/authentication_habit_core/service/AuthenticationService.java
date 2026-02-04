package com.habit.core.authentication_habit_core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.to.controller.request.AuthenticationRequestTO;

public interface AuthenticationService {

	String login(AuthenticationRequestTO authenticationRequestTO) throws UserException;

	void logout(HttpServletRequest request, HttpServletResponse response);

}
