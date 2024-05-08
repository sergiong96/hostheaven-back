package com.hostheaven.backend.controllers;

import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hostheaven.backend.models.User;
import com.hostheaven.backend.services.implementation.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = { "http://localhost:3000", "https://main--hostheaven.netlify.app", "https://hostheaven.netlify.app" })
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/signIn")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		ResponseEntity<String> httpResponse = null;
		String message = userService.createUser(user);
		JSONObject messageJson = new JSONObject();
		messageJson.put("message", message);

		if (message.toLowerCase().contains("error")) {
			httpResponse = ResponseEntity.internalServerError().body(messageJson.toString());
		} else {
			httpResponse = ResponseEntity.ok(messageJson.toString());
		}

		return httpResponse;
	}
	

	@PostMapping("/getUser/{id}")
	public User getUserById(@PathVariable int id) {
		User usuario = userService.getUserById(id);
		return usuario;
	}

	
	@PostMapping("/logIn")
	public String verifyCredentials(@RequestBody String credentials) {
		JSONObject token = userService.verifyCredentials(credentials);
		return token.toString();
	}

	
	@PostMapping("/signInLogIn")
	public ResponseEntity<String> signInAndLogIn(@RequestBody User user) {
		ResponseEntity<String> httpResponse = null;
		String token = "";
		String message = userService.signInAndLogIn(user);
		JSONObject messageJson = new JSONObject();

		if (message.toLowerCase().contains("error")) {
			messageJson.put("message", message);
			httpResponse = ResponseEntity.internalServerError().body(messageJson.toString());
		} else {
			token = message;
			messageJson.put("token", token);
			messageJson.put("message", "Usuario registrado con éxito");
			httpResponse = ResponseEntity.ok(messageJson.toString());
		}

		return httpResponse;
	}

	
	@PostMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		ResponseEntity<String> httpResponse = null;
		String message = userService.updateUser(user);
		JSONObject messageJson = new JSONObject();
		messageJson.put("message", message);

		if (message.toLowerCase().contains("error")) {
			httpResponse = ResponseEntity.internalServerError().body(messageJson.toString());
		} else {
			httpResponse = ResponseEntity.ok(messageJson.toString());
		}

		return httpResponse;
	}
	

	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody Map<String, String> passwordData) {
		ResponseEntity<String> httpResponse = null;
		String message = userService.changePassword(passwordData);
		JSONObject messageJson = new JSONObject();
		messageJson.put("message", message);

		if (message.toLowerCase().contains("error")) {
			httpResponse = ResponseEntity.internalServerError().body(messageJson.toString());
		} else {
			httpResponse = ResponseEntity.ok(messageJson.toString());
		}

		return httpResponse;
	}

	
	@PostMapping("/delete/{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable int user_id, @RequestBody String password) { // ok
		ResponseEntity<String> httpResponse = null;
		String message = userService.deleteUser(user_id, password);
		JSONObject messageJson = new JSONObject();
		messageJson.put("message", message);

		if (message.toLowerCase().contains("error")) {
			httpResponse = ResponseEntity.internalServerError().body(messageJson.toString());
		} else {
			httpResponse = ResponseEntity.ok(messageJson.toString());
		}

		return httpResponse;
	}

}