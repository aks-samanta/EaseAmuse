package com.EaseAmuse.services;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.CurrentUserSession;

public interface SessionServices {

	public CurrentUserSession getSessionByKey(String key) throws ResourceNotFoundException;

}
