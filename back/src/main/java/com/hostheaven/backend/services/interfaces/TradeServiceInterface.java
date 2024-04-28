package com.hostheaven.backend.services.interfaces;

import java.text.ParseException;
import java.util.Map;

import com.hostheaven.backend.models.HostingPackage;
import com.hostheaven.backend.models.HostingPackageTradeDTO;
import com.hostheaven.backend.models.Trade;

public interface TradeServiceInterface {
	// Crea una nueva transacción
	public String createTrade(Map<String, String> trade) throws ParseException;

	// Obtiene todos los datos de una transacción por su identificador
	public Trade getTradeByUserId(int id);
	
	
}
