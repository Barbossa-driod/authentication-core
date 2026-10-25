package com.auth.core.to.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseRequestTO {

	private String userName;
	private String email;

}
