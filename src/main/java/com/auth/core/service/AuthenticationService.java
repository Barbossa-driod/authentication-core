package com.auth.core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth.core.exception.RefreshTokenException;
import com.auth.core.exception.UserException;
import com.auth.core.to.controller.request.AuthenticationRequestTO;
import com.auth.core.to.controller.request.RefreshTokenRequestTO;
import com.auth.core.to.controller.response.AuthenticationResponseTO;

public interface AuthenticationService {

	AuthenticationResponseTO login(AuthenticationRequestTO authenticationRequestTO) throws UserException;

	AuthenticationResponseTO refresh(RefreshTokenRequestTO refreshTokenRequestTO) throws RefreshTokenException;

	void logout(HttpServletRequest request, HttpServletResponse response);

}
