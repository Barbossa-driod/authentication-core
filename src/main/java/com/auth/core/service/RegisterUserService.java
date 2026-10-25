package com.auth.core.service;

import com.auth.core.exception.UserException;
import com.auth.core.to.controller.request.RegisterUserInfoRequestTO;
import com.auth.core.to.controller.response.UserResponseRequestTO;

public interface RegisterUserService {

	UserResponseRequestTO registerUser(RegisterUserInfoRequestTO registerUserInfoRequestTO) throws UserException;

}
