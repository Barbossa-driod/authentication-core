package com.habit.core.authentication_habit_core.security.jwt;

import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.habit.core.authentication_habit_core.exception.RefreshTokenException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.habit.core.authentication_habit_core.entities.RefreshToken;
import com.habit.core.authentication_habit_core.entities.User;
import com.habit.core.authentication_habit_core.exception.JwtAuthenticationException;
import com.habit.core.authentication_habit_core.repository.RefreshTokenRepository;

import io.jsonwebtoken.*;

@Component
public class RefreshTokenProvider {

	@Value("${jwt.token.refresh.secret}")
	private String secretKey;

	@Value("${jwt.token.refresh.expiration}")
	private long validityInDays;

	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshTokenProvider(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(User user) {
		String token = UUID.randomUUID().toString();
		saveToken(token, user);
		return token;
	}

	public void saveToken(String token, User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(hashToken(token));
		refreshToken.setUser(user);
		refreshToken.setExpirationTime(OffsetDateTime.now().plusDays(validityInDays));
		refreshTokenRepository.save(refreshToken);
	}

	public String hashToken(String token) {
		return DigestUtils.sha256Hex(token.concat(secretKey));
	}


	public RefreshToken validateToken(String token) throws RefreshTokenException {
		Optional<RefreshToken> refreshToken =
				refreshTokenRepository.findByTokenAndExpirationTimeAfter(hashToken(token), OffsetDateTime.now());

		if (refreshToken.isEmpty()) {
			throw new RefreshTokenException("Refresh token is expired or invalid");
		}

		return refreshToken.get();
	}

	public void revokeToken(String token) {
		refreshTokenRepository.deleteByToken(hashToken(token));
	}

}
