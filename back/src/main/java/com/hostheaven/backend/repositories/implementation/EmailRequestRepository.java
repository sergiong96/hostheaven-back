package com.hostheaven.backend.repositories.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hostheaven.backend.models.EmailRequest;
import com.hostheaven.backend.repositories.interfaces.EmailRequestRepositoryInterface;

@Repository
public class EmailRequestRepository implements EmailRequestRepositoryInterface {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public String saveMessage(EmailRequest emailRequest) {

		Session session = null;
		Transaction transaction = null;
		String response = "";

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.persist(emailRequest);

			transaction.commit();
			response = "Mensaje enviado con éxito";

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			response = "Error al enviar el mensaje: " + e.getMessage();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return response;
	}

}
