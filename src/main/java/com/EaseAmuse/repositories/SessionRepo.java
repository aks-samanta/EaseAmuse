package com.EaseAmuse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.UserType;

public interface SessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findBySessionKey(String sessionKey);

	public CurrentUserSession findByUserIdAndUserType(Integer userId, UserType userType);

}
