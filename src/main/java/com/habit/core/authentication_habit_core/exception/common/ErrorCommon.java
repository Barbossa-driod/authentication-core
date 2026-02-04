package com.habit.core.authentication_habit_core.exception.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorCommon {

	private String message;
	private String errorCode;

}
