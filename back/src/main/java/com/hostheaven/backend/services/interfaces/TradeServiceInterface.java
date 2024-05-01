package com.hostheaven.backend.services.interfaces;

import java.text.ParseException;
import java.util.Map;
import com.hostheaven.backend.models.Trade;

public interface TradeServiceInterface {
	// Crea una nueva transacción
	public String createTrade(Map<String, String> trade) throws ParseException;

	// Obtiene todos los datos de una transacción por su identificador
	public Trade getTradeByUserId(int id);
	
	// Actualiza el precio del trade y el paquete de hosting asociado
	public String updateTrade(Map<String, Object> trade);
	
	// Elimina el servicio contratado de un usuario
	public String deleteTrade(int id_trade, int id_user);
}
