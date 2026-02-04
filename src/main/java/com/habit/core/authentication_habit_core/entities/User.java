package com.habit.core.authentication_habit_core.entities;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.habit.core.authentication_habit_core.enums.Role;
import com.habit.core.authentication_habit_core.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@FieldNameConstants
public class User {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private String id;

	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email", unique = true)
	private String email;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private Status status;

}
