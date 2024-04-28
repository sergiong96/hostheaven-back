package com.hostheaven.backend.repositories.implementation;

import com.hostheaven.backend.models.HostingPackage;
import com.hostheaven.backend.models.HostingPackageTradeDTO;
import com.hostheaven.backend.models.Trade;
import com.hostheaven.backend.repositories.interfaces.HostingPackageRepositoryInterface;
import com.hostheaven.backend.services.implementation.TradeService;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HostingPackageRepository implements HostingPackageRepositoryInterface {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public int createHostingPackage(HostingPackage hostingPackage) {
		int id_pack = -1;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.persist(hostingPackage);
			transaction.commit();
			id_pack = hostingPackage.getId_package();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

		return id_pack;
	}

	@Override
	public HostingPackage getHostingPackageById(int id_package) {
		Session session = null;
		Transaction transaction = null;
		HostingPackage hostingPackage = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			String hql = "FROM HostingPackage WHERE id_package=:id_package";
			Query<HostingPackage> query = session.createQuery(hql, HostingPackage.class);
			query.setParameter("id_package", id_package);
			hostingPackage = query.uniqueResult();

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		} finally {
			if (session != null) {
				session.close();
			}

		}

		return hostingPackage;
	}

	
	@Override
	public List<HostingPackage> getAllStandardHostingPackages() {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		String hql = "FROM HostingPackage WHERE custom=:custom";
		Query<HostingPackage> query = session.createQuery(hql, HostingPackage.class);
		query.setParameter("custom", false);
		List<HostingPackage> hostingPackages = query.getResultList();

		transaction.commit();
		session.close();

		return hostingPackages;
	}
	
	@Override
	public HostingPackageTradeDTO getHostingPackageByUserId(int id_user) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Trade trade=tradeRepository.getTradeByUserId(id_user);
		int id_package=trade.getId_package();
		
		HostingPackage hostingPackage=this.getHostingPackageById(id_package);
		
		HostingPackageTradeDTO hostingDTO=new HostingPackageTradeDTO();
		hostingDTO.setHostingPackage(hostingPackage);
		hostingDTO.setDate_start(trade.getDate_start());
		hostingDTO.setDate_end(trade.getDate_end());
		hostingDTO.setAmount(trade.getAmount());
		
		transaction.commit();
		session.close();

		return hostingDTO;
		
	}

}
