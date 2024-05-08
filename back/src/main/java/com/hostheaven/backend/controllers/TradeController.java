package com.hostheaven.backend.controllers;

import java.text.ParseException;
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
import com.hostheaven.backend.services.implementation.TradeService;

@RestController
@RequestMapping("/trades")
@CrossOrigin(origins = { "http://localhost:3000", "https://main--hostheaven.netlify.app", "https://hostheaven.netlify.app" })
public class TradeController {

	@Autowired
	private TradeService tradeService;

	
	@PostMapping("/create")
	public ResponseEntity<String> createTrade(@RequestBody Map<String, String> trade) throws ParseException {
		ResponseEntity<String> httpResponse = null;
		String message = tradeService.createTrade(trade);
		JSONObject messageJson = new JSONObject();
		messageJson.put("message", message);

		if (message.toLowerCase().contains("error")) {
			httpResponse = ResponseEntity.internalServerError().body(messageJson.toString());
		} else {
			httpResponse = ResponseEntity.ok(messageJson.toString());
		}

		return httpResponse;
	}

	
	@PostMapping("/update")
	public ResponseEntity<String> updateTrade(@RequestBody Map<String, Object> data) {
		ResponseEntity<String> httpResponse = null;
		String message = tradeService.updateTrade(data);
		JSONObject messageJson = new JSONObject();
		messageJson.put("message", message);

		if (message.toLowerCase().contains("error")) {
			httpResponse = ResponseEntity.internalServerError().body(messageJson.toString());
		} else {
			httpResponse = ResponseEntity.ok(messageJson.toString());
		}

		return httpResponse;
	}

	
	@PostMapping("/delete/{id_trade}/{id_user}")
	public ResponseEntity<String> deleteTrade(@PathVariable int id_trade, @PathVariable int id_user) {
		ResponseEntity<String> httpResponse = null;
		String message = tradeService.deleteTrade(id_trade, id_user);
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
