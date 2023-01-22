package com.EaseAmuse.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.repositories.SessionRepo;

public class SessionServicesImpl implements SessionServices {

	@Autowired
	private SessionRepo sessionRepo;

	@Override
	public CurrentUserSession getSessionByKey(String key) throws ResourceNotFoundException {

		CurrentUserSession currentSession = sessionRepo.findBySessionKey(key);

		if (currentSession == null)
			throw new ResourceNotFoundException("Session", "session key", key);

		return currentSession;

	}

}
