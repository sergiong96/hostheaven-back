package com.hostheaven.backend.repositories.interfaces;

import com.hostheaven.backend.models.User;
import com.hostheaven.backend.models.UserDTO;

public interface UserRepositoryInterface {

	// Crea un nuevo usuario
	public String createUser(User user);

	// Obtiene los datos de un usuario según su identificador
	public User getUserById(int id);

	// // Devuelve lso datos del usuario para el servicio de inicio de sesión
	public User getUserDataByEmail(String email);
	
	// Actualiza los datos de un usuario y devuelve un String en función de si la operación ha tenido éxito o no
	public String updateUser(User user);

	// Cambia la contraseña de un usuario
	public String changePassword(User user, String newPassword);
	
	// Elimina un usuario según su identificador y devuelve un String en función de si la operación ha tenido éxito o no
	public String deleteUser(User user);
}
