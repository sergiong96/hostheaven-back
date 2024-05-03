package com.hostheaven.backend.repositories.interfaces;

import java.util.List;
import com.hostheaven.backend.models.EmailRequest;


public interface EmailRequestRepositoryInterface {

	// Guarda un mensaje en base de datos
	public String saveMessage(EmailRequest emailRequest);

	// Devuelve todos los tickets de un usuario
	public List<EmailRequest> getTickets(int user_id);
}