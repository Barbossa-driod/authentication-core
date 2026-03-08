package com.habit.core.authentication_habit_core.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habit.core.authentication_habit_core.entities.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

	@EntityGraph(attributePaths = "user")
	Optional<RefreshToken> findByTokenAndExpirationTimeAfter(String token, OffsetDateTime time);

	void deleteByToken(String token);

}
