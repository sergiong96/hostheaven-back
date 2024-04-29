package com.hostheaven.backend.repositories.interfaces;

import java.util.Map;
import com.hostheaven.backend.models.Trade;

public interface TradeRepositoryInterface {

	// Crea una nueva transacción
	public String createTrade(Trade trade);

	// Obtiene todos los datos de una transacción por su identificador
	public Trade getTradeByUserId(int id);
	
	// Actualiza el precio del trade y el paquete de hosting asociado
	public String updateTrade(Map<String, Object> trade);

}
