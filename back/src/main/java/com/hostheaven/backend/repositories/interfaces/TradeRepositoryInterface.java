package com.hostheaven.backend.repositories.interfaces;

import com.hostheaven.backend.models.HostingPackage;
import com.hostheaven.backend.models.HostingPackageTradeDTO;
import com.hostheaven.backend.models.Trade;

public interface TradeRepositoryInterface {

	// Crea una nueva transacción
	public String createTrade(Trade trade);

	// Obtiene todos los datos de una transacción por su identificador
	public Trade getTradeByUserId(int id);

}
