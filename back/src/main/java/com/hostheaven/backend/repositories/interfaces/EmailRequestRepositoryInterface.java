package com.hostheaven.backend.repositories.interfaces;

import com.hostheaven.backend.models.EmailRequest;

public interface EmailRequestRepositoryInterface {

	// Guarda un mensaje en base de datos
	public String saveMessage(EmailRequest emailRequest);
	
	
}
