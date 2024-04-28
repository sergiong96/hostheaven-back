package com.hostheaven.backend.controllers;

import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostheaven.backend.models.HostingPackageTradeDTO;
import com.hostheaven.backend.models.User;
import com.hostheaven.backend.models.UserDTO;
import com.hostheaven.backend.services.implementation.TradeService;
import com.hostheaven.backend.services.implementation.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000") // CAMBIAR EN ENTORNO DE PRODUCCION
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TradeService tradeService;
	

	@PostMapping("/signIn") // ok
	public ResponseEntity<String> createUser(@RequestBody User user) {
		String response = userService.createUser(user);
		JSONObject responseJson = new JSONObject();
		responseJson.put("response", response);
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}

	@PostMapping("/getUser/{id}") // ok
	public User getUserById(@PathVariable int id) {
		User usuario = userService.getUserById(id);
		return usuario;
	}
	

	@PostMapping("/logIn") // ok
	public String verifyCredentials(@RequestBody String credentials) {
		JSONObject token = userService.verifyCredentials(credentials);

		return token.toString();
	}

	@PostMapping("/updateUser") // ok
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		String response = userService.updateUser(user);
		JSONObject responseJson = new JSONObject();
		responseJson.put("response", response);
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}

	@PostMapping("/changePassword") //ok
	public ResponseEntity<String> changePassword(@RequestBody Map<String, String> passwordData) { 
		String response = userService.changePassword(passwordData);
		JSONObject responseJson = new JSONObject();
		responseJson.put("response", response);
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}

	@PostMapping("/delete/{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable int user_id, @RequestBody String password) { // ok
		String response=userService.deleteUser(user_id, password);
		JSONObject responseJson = new JSONObject();
		responseJson.put("response", response);
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}
	
	
	
}
