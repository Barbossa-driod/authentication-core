package com.habit.core.authentication_habit_core.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

	private PasswordEncoderUtil() {

	}

	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}
