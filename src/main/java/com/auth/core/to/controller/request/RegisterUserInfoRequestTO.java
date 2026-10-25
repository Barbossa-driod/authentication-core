package com.auth.core.to.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserInfoRequestTO {

	private String userName;
	private String email;
	private String password;

}
