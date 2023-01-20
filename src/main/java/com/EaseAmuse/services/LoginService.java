package com.EaseAmuse.services;

import com.EaseAmuse.exceptions.LoginException;
import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.UserType;
import com.EaseAmuse.payloads.LoginDto;

public interface LoginService {

	public CurrentUserSession logIn(LoginDto loginDTO) throws LoginException;

	public String logOut(Integer userId, UserType userType) throws LoginException;

}
