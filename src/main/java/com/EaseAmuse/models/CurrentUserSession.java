package com.EaseAmuse.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

	@Id
	@Column(unique = true)
	private Integer userId;
	private UserType userType;
	private LocalDateTime timeStamp;
	private String sessionKey;

}
