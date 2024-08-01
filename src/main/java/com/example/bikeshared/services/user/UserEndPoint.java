package com.example.bikeshared.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.soap.user.EmailUserRequest;
import xml.soap.user.LoginRequest;
import xml.soap.user.LogoutRequest;
import xml.soap.user.User;
import xml.soap.user.UserCreat;
import xml.soap.user.UserResponse;
import xml.soap.user.UserUpdatePasswordRequest;
import xml.soap.user.UserUpdateRequest;
import xml.soap.user.UsersListResponse;


//import java.security.NoSuchAlgorithmException;

@Endpoint
@Component
public class UserEndPoint {
	
	private static final String NAMESPACE_URI = "http://user.soap.xml";
	
	@Autowired
	private UserService userService;

	/**
	 *
	 * @param {@link UserRequest}
	 * @return {@link UserResponse}
	 */
	//
	//
			// ENDPOINT CADASTRAR USER 
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "UserCreat")
	@ResponsePayload
	public User addUser (@RequestPayload UserCreat request) {
		System.out.println(" == Entrando no serviço addUser");
		return userService.addUser(request);
	}
	//
	//
			// ENDPOINT GET ALL USER  
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "UsersAll")
	@ResponsePayload
	public UsersListResponse getAllUser () {
		System.out.println(" == ENTRANDO NO SERVIÇO GET ALL USERS");
		return userService.getAllUsers();
	}
	//
	//
			// ENDPOINT UPDATE USER  
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "UserUpdateRequest")
	@ResponsePayload
	public UserResponse updateUser (@RequestPayload UserUpdateRequest request) {
		System.out.println(" == ENTRANDO NO SERVIÇO USER UPDATE");
		return userService.upadteUser(request);
	}
	//
	//
	// ENDPOINT UPDATE PASSWORD  
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "UserUpdate/password")
	@ResponsePayload
	public UserResponse updateUserPassword (@RequestPayload UserUpdatePasswordRequest request) {
		System.out.println(" == ENTRANDO NO SERVIÇO USER UPDATE PASSWORD");
		return userService.upadtePasswordUser(request);
	}
	//
	//
			// ENDPOINT DELETE USER  
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "EmailUserRequest")
	@ResponsePayload
	public UserResponse deleteUser (@RequestPayload EmailUserRequest request) {
		System.out.println(" == ENTRANDO NO SERVIÇO USER DELETE");
		return userService.deleteUser(request);
	}


	//
	//
			// ENDPOINT LOGOUT  
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "UserLogout")
	@ResponsePayload
	public UserResponse logout (LogoutRequest request) {
		System.out.println("ENTRANDO NO SERVIÇO LOGOUT");
		return userService.logout(request);
	}

}

	/* 
	 	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginRequest")
	@ResponsePayload
	public UserResponse login (@RequestPayload LoginRequest request) {
		System.out.println("Entrando no serviço startSession");
		return userService.login(request);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "UserIdRequest")
	@ResponsePayload
	public UserResponse getUserId (@RequestPayload UserIdRequest request) {
		System.out.println("Entrando no serviço getUserById");
		return (UserResponse) userService.getUser(request);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "LogoutRequest")
	@ResponsePayload
	public UserResponse logout (@RequestPayload LogoutRequest request) {
		System.out.println("Entrando no serviço getUserById");
		return userService.logout(request);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "Users")
	@ResponsePayload
	public UserListResponse getAllUsers (@RequestPayload AllUsersRequest request) {
		System.out.println("Entrando no serviço getUserById");
		return userService.getAllUsers(request);
	}

	*/




