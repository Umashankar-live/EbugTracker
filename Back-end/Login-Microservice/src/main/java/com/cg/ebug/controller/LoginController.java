package com.cg.ebug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ebug.entity.Login;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.service.LoginService;
import com.cg.ebug.service.UserServiceInterface;

/*
 * @Autowired - The process of injection spring bean dependencies while initializing it
 * @RequestMapping - for configuring URI mapping in controller handler methods 
 * @PathVariable -  for mapping dynamic values from the URI to handler method arguments.
 * @CrossOrigin - enables cross-origin resource sharing only for this specific method. By default, its allows all origins, 
 *                all headers, and the HTTP methods specified in the @RequestMapping annotation
 * @ResponseBody - annotation maps the HttpRequest body to a transfer or domain object
 */

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/EtrackingSystem")
public class LoginController {

	@Autowired
	private LoginService service;
	
	@Autowired
	private UserServiceInterface userService ;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee_Table loginUser(@RequestBody Login user) {
		String tempUsername = user.getUserName();
		String tempPassword = user.getPassword();
		Employee_Table userObj ;
		if (tempUsername != null && tempPassword != null) {
			userObj = service.getUserByUserNameAndPassword(tempUsername, tempPassword);
			return userObj ;
		}
			
	   return null ;

	}
	
	    //http://localhost:9002/user/addUser
		@RequestMapping(value = "user/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Employee_Table addUser(  @RequestBody Employee_Table user) {
			user.setRole("customer");
			return this.userService.addUser(user);
		}
		

		//http://localhost:9002/user/getAllUser
		@GetMapping(value = "user/getAllUser")
		public List<Employee_Table> getAllTest() {
			return this.userService.getAllUser();
		}

		//http://localhost:9002/user/deleteUser/12
		@DeleteMapping(value = "user/deleteUser/{userId}")
		public void deleteUser(@PathVariable Long userId) {
			this.userService.deleteUser(userId);
		}

		//http://localhost:9002/user/searchUser/1
		@GetMapping(value = "user/searchUser/{userId}")
		public Employee_Table searchUserById(@PathVariable Long userId) {
			return this.userService.searchUser(userId);
		}
		
		//http://localhost:9002/user/updateUser/1
		@RequestMapping(value = "user/updateUser/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Employee_Table updateUser(@RequestBody Employee_Table user,@PathVariable Long userId) {
			Employee_Table newUser = this.userService.searchUser(userId);
			newUser.setUserName(user.getUserName());
			newUser.setPassword(user.getPassword());
			newUser.setMobileNo(user.getMobileNo());
			newUser.setEmailId(user.getEmailId());	
			newUser.setRole("customer");
			return this.userService.updateUser(newUser);
			
		}
		

}
