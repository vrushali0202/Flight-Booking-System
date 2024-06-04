package com.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;

import com.user.controller.UserController;
import com.user.entity.AuthRequest;
import com.user.entity.UserData;
import com.user.service.JwtService;
import com.user.service.UserDataServiceImp;

@SpringBootTest
class UserApplicationTests {

	@Test
	void contextLoads() {
	}

	 @Mock
     private AuthenticationManager authenticationManager;
 
        @Mock
        private JwtService jwtService;
 
        @InjectMocks
        private AuthRequest authRequest;
   
    @Mock
    private UserDataServiceImp userService;
 
    @InjectMocks
    private UserController userController;
 
    @Test
    public void testAddUser()
    {
        // Mocking behavior for the UserService
        UserData userInput = new UserData("1","vrushali", "vk@gmail.com", "vk@123", "9876543211", "USER_ROLES");
        when(userService.addUser(any(UserData.class))).thenReturn("User added successfully");
 
        // Calling the controller method
        String responseEntity = userController.addUser(userInput);
 
        // Verifying the result
        assertEquals("User added successfully", responseEntity);
 
        // Verifying interactions with the UserService
        Mockito.verify(userService, Mockito.times(1)).addUser(any(UserData.class));
    }
 
//        @Test
//        public void testAddUser_SuccessfulAuthentication() {
//            // Mocking behavior for a successful authentication
//            AuthRequest authRequest = new AuthRequest("validUserName", "validPassword");
//            Authentication authentication = Mockito.mock(Authentication.class);
//            when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
//            when(authentication.isAuthenticated()).thenReturn(true);
//            when(jwtService.generateToken(authRequest.getUserName())).thenReturn("mockedToken");
// 
//            // Calling the controller method
//            String resultToken = userController.addUser(authRequest);
// 
//            // Verifying the result
//            assertEquals("mockedToken", resultToken);
//        }
 
        @Test
        @WithMockUser(authorities = "ADMIN")
        public void testGetAllUsers_AdminRole() {
            // Mocking behavior for the UserService
            List<UserData> expectedUsers = Arrays.asList(
                    new UserData("1","vrushali", "vk@gmail.com", "vk@123", "9876543211", "USER_ROLES"),
                    new UserData("2","pratiksha", "pk@gmail.com", "pk@123", "9876543211", "USER_ROLES")
                   
            );
            when(userService.getAllUser()).thenReturn(expectedUsers);
 
            // Calling the controller method
            List<UserData> resultUsers = userController.getAllUsers();
 
            // Verifying the result
            assertEquals(expectedUsers, resultUsers);
        }
       
        @Test
        @WithMockUser(authorities = "USER")
        public void testGetAllUsers_UserRole() {
            // Calling the controller method with a user lacking the required ADMIN role
            // The method should throw AccessDeniedException or return an empty list, depending on your configuration.
            // This depends on how you've configured your application to handle unauthorized access.
            // Modify this test according to your application's behavior.
 
            // For example, if you expect an empty list for unauthorized access:
            List<UserData> resultUsers = userController.getAllUsers();
            assertEquals(0, resultUsers.size());
        }
       
        @Test
        @WithMockUser(authorities = "USER")
        public void testGetUserByName_UserRole() {
            // Mocking behavior for the UserService
            String expectedUsername = "vrushali";
            Optional<UserData> expectedUser =Optional.of(new UserData("1","vrushali", "vk@gmail.com", "vk@123", "9876543211", "USER_ROLES"));
            when(userService.getUser(expectedUsername)).thenReturn(expectedUser, null);
 
            // Calling the controller method
            Optional<UserData> resultUser = userController.getUsersByName(expectedUsername);
 
            // Verifying the result
            assertEquals(expectedUser, resultUser);
        }
        @Test
        @WithMockUser(authorities = "ADMIN")
        public void testGetUserByName_AdminRole() {
            // Calling the controller method with an admin role
            // The method should throw AccessDeniedException or return null, depending on your configuration.
            // This depends on how you've configured your application to handle unauthorized access.
            // Modify this test according to your application's behavior.
 
            // For example, if you expect null for unauthorized access:
        	Optional<UserData> resultUser = userController.getUsersByName("vrush");
            assertEquals(null, resultUser);
        }
       
//        @Test
//        public void testDeleteUser_SuccessfulDeletion() {
//            // Mocking behavior for the UserService
//            String userNameToDelete = "testUser";
//            Mockito.doNothing().when(userService).deleteUser(userNameToDelete);
// 
//            // Calling the controller method
//            ResponseEntity<String> responseEntity = userController.deleteUser(userNameToDelete);
// 
//            // Verifying the result
//            assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//            assertEquals("User deleted successfully", responseEntity.getBody());
// 
//            // Verifying interactions with the UserService
//            Mockito.verify(userService, Mockito.times(1)).deleteUser(userNameToDelete);
//        }
 
//        @Test
//        public void testDeleteUser_UserNotFound() {
//            // Mocking behavior for the UserService when user is not found
//            String nonExistingUserName = "Pratiksha";
//           
//            Mockito.when(userController.getUserByName(nonExistingUserName)).thenReturn(null);
//            // Calling the controller method
//            ResponseEntity<String> responseEntity = userController.deleteUser(nonExistingUserName);
// 
//            // Verifying the result
//            assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//        }
}
 
//}
