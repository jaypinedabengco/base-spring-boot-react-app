package com.bengco.app.react.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bengco.app.react.BaseSpringBootReactAppApplication;
import com.bengco.app.react.dto.LoginRequestDto;
import com.bengco.app.react.dto.SignupRequestDto;
import com.bengco.app.react.model.Role;
import com.bengco.app.react.model.enums.RoleName;
import com.bengco.app.react.repository.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.MOCK, 
  classes = BaseSpringBootReactAppApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired 
    private ObjectMapper mapper;

    // general request dto
    private static final SignupRequestDto SIGNUP_REQUEST_DTO;
    
    static {
    	SIGNUP_REQUEST_DTO = new SignupRequestDto();
    	SIGNUP_REQUEST_DTO.setEmail("hello@yopmail.com");
    	SIGNUP_REQUEST_DTO.setName("johndoe");
    	SIGNUP_REQUEST_DTO.setPassword("helloWorld");
		SIGNUP_REQUEST_DTO.setUsername("johndoetest123");    	
    }
	
	@Test
	public void givenUserSignup_then_get_2xx() throws Exception {
		SignupRequestDto signupRequestDTO = getInitialSignupRequestDtoCopy();

		mvc
			.perform(
					post("/api/auth/signup")
						.content(mapper.writeValueAsString(signupRequestDTO))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().is2xxSuccessful())
						.andExpect(content()
								.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.success", is(true)))
						.andExpect(jsonPath("$.message", is("User registered successfully"))
					);
	}
	
	@Test
	public void givenUserSignupWithExistingUsername_then_fail_signup_and_get_400() throws Exception {

		SignupRequestDto signupRequestDTO = getInitialSignupRequestDtoCopy();
		signupRequestDTO.setEmail("anotherEmail@test.com"); // change email
		
		mvc
			.perform(
					post("/api/auth/signup")
						.content(mapper.writeValueAsString(signupRequestDTO))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().is(400))
						.andExpect(content()
								.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.success", is(false)))
						.andExpect(jsonPath("$.message", is("Username is already taken!"))
					);
	}
	
	@Test
	public void givenUserSignupWithExistingEmail_then_get_400() throws Exception {

		SignupRequestDto signupRequestDTO = getInitialSignupRequestDtoCopy();
		signupRequestDTO.setUsername("anotherUsername"); // change username
		
		mvc
			.perform(
					post("/api/auth/signup")
						.content(mapper.writeValueAsString(signupRequestDTO))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().is(400))
						.andExpect(content()
								.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.success", is(false)))
						.andExpect(jsonPath("$.message", is("Email Address already in use!"))
					);
	}	
	
	@Test
	public void givenUserHasSignedUp_then_login_via_username_then_get_2xx_and_auth_token() throws Exception {
		
		LoginRequestDto loginRequest = new LoginRequestDto();
		loginRequest.setUsernameOrEmail(SIGNUP_REQUEST_DTO.getUsername());
		loginRequest.setPassword(SIGNUP_REQUEST_DTO.getPassword());
		
		mvc
			.perform(
					post("/api/auth/signin")
						.content(mapper.writeValueAsString(loginRequest))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().is2xxSuccessful())
						.andExpect(content()
								.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.tokenType", is("Bearer")))
						.andExpect(jsonPath("$.accessToken", is((instanceOf(String.class))))
					);	
	}
	
	@Test
	public void givenUserHasSignedUp_then_login_via_email_then_get_2xx_and_auth_token() throws Exception {
		
		LoginRequestDto loginRequest = new LoginRequestDto();
		loginRequest.setUsernameOrEmail(SIGNUP_REQUEST_DTO.getEmail());
		loginRequest.setPassword(SIGNUP_REQUEST_DTO.getPassword());
		
		mvc
			.perform(
					post("/api/auth/signin")
						.content(mapper.writeValueAsString(loginRequest))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().is2xxSuccessful())
						.andExpect(content()
								.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.tokenType", is("Bearer")))
						.andExpect(jsonPath("$.accessToken", is((instanceOf(String.class))))
					);	
	}	
	
	@Test
	public void givenUserHasSignedUp_then_login_via_invalid_username_then_fail_login_and_get_401_error() throws Exception {
		
		LoginRequestDto loginRequest = new LoginRequestDto();
		loginRequest.setUsernameOrEmail("ThisIsAndInvalidUsername");
		loginRequest.setPassword(SIGNUP_REQUEST_DTO.getPassword());
		
		mvc
			.perform(
					post("/api/auth/signin")
						.content(mapper.writeValueAsString(loginRequest))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isUnauthorized());	
	}		

	@Test
	public void givenUserHasSignedUp_then_login_via_invalid_email_then_fail_login_and_get_401_error() throws Exception {
		
		LoginRequestDto loginRequest = new LoginRequestDto();
		loginRequest.setUsernameOrEmail("ThisIsAndInvalidUsername@test.com");
		loginRequest.setPassword(SIGNUP_REQUEST_DTO.getPassword());
		
		mvc
			.perform(
					post("/api/auth/signin")
						.content(mapper.writeValueAsString(loginRequest))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isUnauthorized());	
	}		
	
	
	/**
	 * 
	 * @return
	 */
	private SignupRequestDto getInitialSignupRequestDtoCopy() {
		SignupRequestDto signupRequestDTOCopy = new SignupRequestDto();
		signupRequestDTOCopy.setUsername(SIGNUP_REQUEST_DTO.getUsername());
		signupRequestDTOCopy.setEmail(SIGNUP_REQUEST_DTO.getEmail());
		signupRequestDTOCopy.setPassword(SIGNUP_REQUEST_DTO.getPassword());
		signupRequestDTOCopy.setName(SIGNUP_REQUEST_DTO.getName());
		return signupRequestDTOCopy;

	}
}
