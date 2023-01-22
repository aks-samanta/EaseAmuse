package com.EaseAmuse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.UserType;

@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findBySessionKey(String sessionKey);

	public CurrentUserSession findByUserIdAndType(Integer userId, UserType userType);

}
