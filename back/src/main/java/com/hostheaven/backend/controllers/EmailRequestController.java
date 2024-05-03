package com.hostheaven.backend.controllers;

import java.util.List;

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
import com.hostheaven.backend.models.EmailRequest;
import com.hostheaven.backend.services.implementation.EmailRequestService;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = { "http://localhost:3000", "https://main--hostheaven.netlify.app",
		"https://hostheaven.netlify.app" })
public class EmailRequestController {

	@Autowired
	private EmailRequestService emailRequestService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
		String response = emailRequestService.sendEmail(emailRequest);
		JSONObject responseJson = new JSONObject();
		responseJson.put("response", response);
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}

	@PostMapping("/getTickets/{user_id}")
	public ResponseEntity<List<EmailRequest>> getTickets(@PathVariable int user_id) {
		List<EmailRequest> tickets = emailRequestService.getTickets(user_id);
		if (tickets != null) {
			return ResponseEntity.ok(tickets);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
