package com.EaseAmuse.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.exceptions.LoginException;
import com.EaseAmuse.models.Admin;
import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.Customer;
import com.EaseAmuse.models.Manager;
import com.EaseAmuse.models.UserType;
import com.EaseAmuse.payloads.LoginDto;
import com.EaseAmuse.repositories.AdminRepo;
import com.EaseAmuse.repositories.CustomerRepo;
import com.EaseAmuse.repositories.ManagerRepo;
import com.EaseAmuse.repositories.SessionRepo;

import net.bytebuddy.utility.RandomString;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo sessionRepo;

	@Autowired
	private ManagerRepo managerRepo;

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public CurrentUserSession logIn(LoginDto loginDTO) throws LoginException {

		CurrentUserSession session = null;

		if (loginDTO.getUserType() == UserType.CUSTOMER) {

			Customer currentCustomer = customerRepo.findByEmail(loginDTO.getEmail());

			if (currentCustomer == null) {
				throw new LoginException("Customer not present with email " + loginDTO.getEmail());
			}

			Optional<CurrentUserSession> currentSession = sessionRepo.findById(currentCustomer.getCustomerId());

			if (currentSession.isPresent())
				throw new LoginException("Customer Already Logged in with this E-mail Id");

			if (currentCustomer.getPassword().equals(loginDTO.getPassword())) {
				String sessionKey = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setSessionKey(sessionKey);
				currentUserSession.setUserId(currentCustomer.getCustomerId());
				currentUserSession.setUserType(loginDTO.getUserType());
				currentUserSession.setTimeStamp(LocalDateTime.now());

				session = sessionRepo.save(currentUserSession);

			} else
				throw new LoginException("Please Enter a valid Password");

		} else if (loginDTO.getUserType() == UserType.MANAGER) {

			Manager currentManager = managerRepo.findByEmail(loginDTO.getEmail());

			if (currentManager == null) {
				throw new LoginException("Manager not present with email " + loginDTO.getEmail());
			}

			Optional<CurrentUserSession> currentSession = sessionRepo.findById(currentManager.getManagerId());

			if (currentSession.isPresent())
				throw new LoginException("Manager Already Logged in with this E-mail Id");

			if (currentManager.getPassword().equals(loginDTO.getPassword())) {
				String sessionKey = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setSessionKey(sessionKey);
				currentUserSession.setUserId(currentManager.getManagerId());
				currentUserSession.setUserType(loginDTO.getUserType());
				currentUserSession.setTimeStamp(LocalDateTime.now());

				session = sessionRepo.save(currentUserSession);

			} else
				throw new LoginException("Please Enter a valid Password");

		} else {
			Admin currentAdmin = adminRepo.findByEmail(loginDTO.getEmail());

			if (currentAdmin == null) {
				throw new LoginException("Admin not present with email " + loginDTO.getEmail());
			}

			Optional<CurrentUserSession> currentSession = sessionRepo.findById(currentAdmin.getAdminId());

			if (currentSession.isPresent())
				throw new LoginException("Admin Already Logged in with this E-mail Id");

			if (currentAdmin.getPassword().equals(loginDTO.getPassword())) {
				String sessionKey = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setSessionKey(sessionKey);
				currentUserSession.setUserId(currentAdmin.getAdminId());
				currentUserSession.setUserType(loginDTO.getUserType());
				currentUserSession.setTimeStamp(LocalDateTime.now());

				session = sessionRepo.save(currentUserSession);

			} else
				throw new LoginException("Please Enter a valid Password");
		}

		return session;

	}

	@Override
	public String logOut(Integer userId, UserType userType) throws LoginException {

		CurrentUserSession session = sessionRepo.findByUserIdAndUserType(userId, userType);

		if (session == null) {
			throw new LoginException("No " + userType.name() + "logged in with User ID " + userId);
		}

		return "Logged Out Successfully !";

	}

}
