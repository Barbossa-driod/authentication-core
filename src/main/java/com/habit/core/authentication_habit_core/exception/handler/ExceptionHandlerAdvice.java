package com.habit.core.authentication_habit_core.exception.handler;

import com.habit.core.authentication_habit_core.exception.RefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.habit.core.authentication_habit_core.exception.UserException;
import com.habit.core.authentication_habit_core.exception.common.ErrorCommon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ErrorCommon handleUserException(UserException exception) {
		return new ErrorCommon().setMessage(exception.getMessage());
	}

    @ExceptionHandler(RefreshTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorCommon handleRefreshTokenException(RefreshTokenException exception) {
        return new ErrorCommon().setMessage(exception.getMessage());
    }

}
