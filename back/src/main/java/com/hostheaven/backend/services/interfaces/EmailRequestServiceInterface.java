package com.hostheaven.backend.services.interfaces;

import com.hostheaven.backend.models.EmailRequest;

public interface EmailRequestServiceInterface {
	
	// Envía un email y lo guarda en base de datos
	public String sendEmail(EmailRequest emailRequest);
	

}
