package com.HostelMS.daoImpl;

import org.hibernate.Session;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public class hostelMSDaoImpl implements hostelMSDao {

	@Override
	public int registration(user user1) throws GlobalException {

		try (Session session = HibernateUtil.getSession()) {

			String username = user1.getUserName();
			user user2 = null;
			user2 = (user) session.createQuery("from user where userName=:username").setParameter("username", username)
					.uniqueResult();
			if (user2 == null) {
				session.beginTransaction();
				session.save(user1);
				session.getTransaction().commit();
				return 1;
			} else {
				throw new GlobalException("user already existed");
			} 

		}

	}

	@Override
	public user login(String username, String password) throws GlobalException {

		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();

			user user2 = null;
			user2 = (user) session.createQuery("from user where userName=:username").setParameter("username", username)
					.uniqueResult();
			if (user2 != null) {
				if (user2.getUserPassword().equals(password)) {
					return user2;
				}
				else 
				{
					throw new GlobalException("Wrong Username or Password");
				}
			}
			else
			{
				throw new GlobalException("Wrong Username or Password");
			}

		}

	}

}