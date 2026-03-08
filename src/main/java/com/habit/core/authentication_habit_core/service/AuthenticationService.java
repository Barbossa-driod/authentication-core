package com.habit.core.authentication_habit_core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.habit.core.authentication_habit_core.exception.RefreshTokenException;
import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.to.controller.request.AuthenticationRequestTO;
import com.habit.core.authentication_habit_core.to.controller.request.RefreshTokenRequestTO;
import com.habit.core.authentication_habit_core.to.controller.response.AuthenticationResponseTO;

public interface AuthenticationService {

	AuthenticationResponseTO login(AuthenticationRequestTO authenticationRequestTO) throws UserException;

	AuthenticationResponseTO refresh(RefreshTokenRequestTO refreshTokenRequestTO) throws RefreshTokenException;

	void logout(HttpServletRequest request, HttpServletResponse response);

}
