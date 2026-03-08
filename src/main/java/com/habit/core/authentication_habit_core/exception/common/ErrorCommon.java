package com.habit.core.authentication_habit_core.exception.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorCommon {

	private String message;
	private String errorCode;

}
