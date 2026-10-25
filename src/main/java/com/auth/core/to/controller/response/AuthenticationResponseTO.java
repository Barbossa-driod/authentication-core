package com.auth.core.to.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseTO {

	private String accessToken;
	private String refreshToken;

}
