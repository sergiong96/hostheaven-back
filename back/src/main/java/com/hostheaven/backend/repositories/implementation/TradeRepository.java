package com.hostheaven.backend.repositories.implementation;

import com.hostheaven.backend.models.HostingPackage;
import com.hostheaven.backend.models.HostingPackageTradeDTO;
import com.hostheaven.backend.models.Trade;
import com.hostheaven.backend.models.User;
import com.hostheaven.backend.repositories.interfaces.TradeRepositoryInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TradeRepository implements TradeRepositoryInterface {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public String createTrade(Trade trade) {
		String response = "";
		System.out.println("Trade a insertar: " + trade);
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.persist(trade);
			transaction.commit();
			response = "La operación de inserción ha sido exitosa";
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			response = "Error al insertar la transaccion: " + e.getMessage();
		} finally {
			session.close();
		}

		return response;
		
		
	}

	@Override
	public Trade getTradeByUserId(int user_id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "FROM Trade WHERE id_user=:id_user";
		Query<Trade> query = session.createQuery(hql, Trade.class);
		query.setParameter("id_user", user_id);
		query.setMaxResults(1);
		Trade trade = query.uniqueResult();
		
		transaction.commit();
		session.close();
		
		
		return trade;
	}

}
