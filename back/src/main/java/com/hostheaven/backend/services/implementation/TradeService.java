package com.hostheaven.backend.services.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hostheaven.backend.models.HostingPackage;
import com.hostheaven.backend.models.HostingPackage.hostingType;
import com.hostheaven.backend.models.HostingPackageTradeDTO;
import com.hostheaven.backend.models.Trade;
import com.hostheaven.backend.models.Trade.paymentMethod;
import com.hostheaven.backend.models.Trade.tradeState;
import com.hostheaven.backend.models.User;
import com.hostheaven.backend.repositories.implementation.TradeRepository;
import com.hostheaven.backend.services.interfaces.TradeServiceInterface;

@Service
public class TradeService implements TradeServiceInterface {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HostingPackageService hostingPackageService;

	@Autowired
	private TradeRepository tradeRepository;
	
	
	@Override
	public String createTrade(Map<String, String> trade) throws ParseException {

		//Capturar la propiedad custom que podria venir en el trade si es personalizado (es un Map con todas las propiedades) ok
		//Si es personalizado, capturar todas las propiedades necesarias para insertar un nuevo hostingPackage ok
		//Si es estandar, pasar directamente a createTrade, creando un objeto trade a raiz del Map
		//Despues, con el id que devuelva insertCustomPackage debo crear un objeto trade con las propiedades necesarias del Object + el id
		String response="";
		Trade tradeObj=new Trade();
		
		tradeObj.setId_user(Integer.parseInt(trade.get("id_user")));
		
		
		tradeObj.setAmount(Double.parseDouble(trade.get("package_price")));
		
		Date date_start_parsed=new SimpleDateFormat("yyy-MM-dd").parse(trade.get("date_start"));
		tradeObj.setDate_start(date_start_parsed);
		
		Date date_end_parsed=new SimpleDateFormat("yyy-MM-dd").parse(trade.get("date_end"));
		tradeObj.setDate_end(date_end_parsed);

		paymentMethod paymentParsed=paymentMethod.valueOf(trade.get("payment_method"));
		tradeObj.setPayment_method(paymentParsed);

		tradeState stateParsed=tradeState.valueOf(trade.get("state"));
		tradeObj.setState(stateParsed);
		System.out.println("Trade generico: "+tradeObj);
		if(Boolean.parseBoolean((String)trade.get("custom"))==true){
			HostingPackage hostingPackage=new HostingPackage();
			hostingPackage.setApp_installation(Boolean.parseBoolean((String)trade.get("app_installation")));
			hostingPackage.setCdn(Boolean.parseBoolean((String)trade.get("cdn")));
			hostingPackage.setCustom(Boolean.parseBoolean((String)trade.get("custom")));
			hostingPackage.setDatabases(Integer.parseInt(trade.get("databases")));
			hostingPackage.setDomains(Integer.parseInt(trade.get("domains")));
			hostingPackage.setEmail_account(Integer.parseInt(trade.get("email_account")));
			hostingPackage.setFtp_server(Boolean.parseBoolean((String)trade.get("ftp_server")));
			hostingType hostingTypeParsed=hostingType.valueOf(trade.get("hosting_type"));
			hostingPackage.setHosting_type(hostingTypeParsed);
			hostingPackage.setMigration(Boolean.parseBoolean((String)trade.get("cdn")));
			hostingPackage.setMonthly_bandwidth(Integer.parseInt(trade.get("monthly_bandwidth")));
			hostingPackage.setPackage_name((String)trade.get("package_name"));
			hostingPackage.setPackage_price(Double.parseDouble((String)trade.get("package_price")));
			hostingPackage.setPurchase_quantity(Integer.parseInt(trade.get("purchase_quantity")));
			hostingPackage.setSsl(Boolean.parseBoolean((String)trade.get("ssl")));
			hostingPackage.setStorage(Integer.parseInt(trade.get("storage")));
			hostingPackage.setTechnical_support_24h(Boolean.parseBoolean((String)trade.get("technical_support_24h")));
			System.out.println("Paquete a insertar: "+hostingPackage);
			
			int id_package_custom=hostingPackageService.createHostingPackage(hostingPackage);
			
			tradeObj.setId_package(id_package_custom);

			response=this.tradeRepository.createTrade(tradeObj);
		}else {
	
			tradeObj.setId_package(Integer.parseInt(trade.get("id_package")));
			response=this.tradeRepository.createTrade(tradeObj);
		}


		return response;
	}
	
	
	@Override
	public Trade getTradeByUserId(int id_user) {
		Trade trade = this.tradeRepository.getTradeByUserId(id_user);
		return trade;
	}


}
