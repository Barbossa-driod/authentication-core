package com.habit.core.authentication_habit_core.to.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestTO {

	private String userName;
	private String email;
	private String password;

}
