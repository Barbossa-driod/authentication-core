package com.auth.core.entities;

import java.time.OffsetDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@FieldNameConstants
public class RefreshToken {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(nullable = false)
	private String token;

	@Column(nullable = false)
	private OffsetDateTime expirationTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
