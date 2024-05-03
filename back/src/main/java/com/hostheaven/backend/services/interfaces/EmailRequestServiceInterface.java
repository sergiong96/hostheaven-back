package com.hostheaven.backend.services.interfaces;


import java.util.List;

import com.hostheaven.backend.models.EmailRequest;

public interface EmailRequestServiceInterface {
	
	// Envía un email y lo guarda en base de datos
	public String sendEmail(EmailRequest emailRequest);
	
	// Devuelve todos los tickets de un usuario
	public List<EmailRequest> getTickets(int user_id);
}
