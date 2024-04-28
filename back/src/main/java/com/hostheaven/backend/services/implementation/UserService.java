package com.hostheaven.backend.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hostheaven.backend.models.User;
import com.hostheaven.backend.models.UserDTO;
import com.hostheaven.backend.repositories.implementation.UserRepository;
import com.hostheaven.backend.services.interfaces.UserServiceInterface;

import java.util.Map;

import org.json.*;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	// Comprueba que el email no esté en la base dedatos, encripta la contraseña y
	// registra al usuario
	@Override
	public String createUser(User user) { // ok
		String response = "";
		String email = user.getEmail();
		boolean emailExists = this.emailExists(email);

		if (!emailExists) {
			String pass = user.getPassword();
			System.out.println("pass= " + pass);
			String encriptedPass = securityService.generateHash(pass);
			System.out.println("Encoded pass= " + encriptedPass);
			user.setPassword(encriptedPass);
			response = this.userRepository.createUser(user);
		} else {
			response = "El correo electrónico ya existe en la base de datos";
		}

		return response;
	}

	public boolean emailExists(String email) { // ok
		boolean response = false;
		response = userRepository.verifyEmail(email);
		return response;
	}

	@Override
	public User getUserById(int id) { // ok
		User usuario = this.userRepository.getUserById(id);
		return usuario;
	}
	
	

	//public UserDTO getUserDTOById(int id) { // ok
	//	UserDTO usuario = this.userRepository.getUserDTOById(id);
	//	return usuario;
	//}
	

	@Override
	public JSONObject verifyCredentials(String credentials) { // ok
		JSONObject credentialsOBJ = new JSONObject(credentials);
		String inputEmail = credentialsOBJ.getString("email");
		String inputPassword = credentialsOBJ.getString("password");
		String token = "";
		JSONObject tokenJSON = new JSONObject();
		boolean samePassword = false;

		User userData = userRepository.getUserDataByEmail(inputEmail);
		if (userData != null) {
			String userPassword = userData.getPassword();
			samePassword = securityService.verifyPassword(inputPassword, userPassword);
		}

		if (samePassword) {
			String userEmail = userData.getEmail();
			int id_user = userData.getId_user();
			String name = userData.getName();
			token = securityService.createToken(id_user, name, userEmail);
			tokenJSON.put("token", token);
		}

		return tokenJSON;
	}

	@Override
	public String updateUser(User user) { // ok
		String mensaje = this.userRepository.updateUser(user);
		return mensaje;
	}

	@Override
	public String changePassword(Map<String, String> passwordData) { // ok

		User user = userRepository.getUserById(Integer.parseInt(passwordData.get("id_user")));
		String hashedUserPassword = user.getPassword();

		String rawPasswordToVerificate = passwordData.get("actual_pass");

		String newPassword = passwordData.get("new_pass");
		String newPasswordHash = securityService.generateHash(newPassword);

		String response = "";

		if (securityService.verifyPassword(rawPasswordToVerificate, hashedUserPassword)) {
			response = userRepository.changePassword(user, newPasswordHash);
		} else {
			response = "La contraseña introducida no es correcta";
		}

		return response;

	}

	@Override
	public String deleteUser(int user_id, String rawPassword) {

		String message = "";
		User user = userRepository.getUserById(user_id);

		if (user != null) {
			String hashedUserPassword = user.getPassword();
			message = securityService.verifyPassword(rawPassword, hashedUserPassword)
					? this.userRepository.deleteUser(user)
					: "La contraseña introducida no es correcta";
		} else {
			message = "Usuario no encontrado";
		}

		return message;
	}
}
