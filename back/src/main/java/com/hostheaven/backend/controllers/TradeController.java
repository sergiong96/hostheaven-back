package com.hostheaven.backend.controllers;

import java.text.ParseException;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostheaven.backend.models.HostingPackage;
import com.hostheaven.backend.models.Trade;
import com.hostheaven.backend.services.implementation.TradeService;

@RestController
@RequestMapping("/trades")
@CrossOrigin(origins = "http://localhost:3000") // CAMBIAR EN ENTORNO DE PRODUCCION
public class TradeController {

	@Autowired
	private TradeService tradeService;

	@PostMapping("/create")
	public ResponseEntity<String> createTrade(@RequestBody Map<String, String> trade) throws ParseException {
		String response = tradeService.createTrade(trade);
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("response", response);
		return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
	}


}
