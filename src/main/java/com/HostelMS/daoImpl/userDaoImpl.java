package com.HostelMS.daoImpl;

import org.hibernate.Session;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.userDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public class userDaoImpl implements userDao {

	@Override
	public user viewRoom(int uId) {                           // to print room details
		
		try (Session session = HibernateUtil.getSession()) {                         

			user user2 = session.get(user.class, uId);
			return user2;
		}

	}

	
	@Override
	public user viewProfile(int uId) {        // getting profile of the user
		try (Session session = HibernateUtil.getSession()) {

			user user2 = session.get(user.class, uId);
			return user2;
		}
	}


	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException {

		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			user user1 = session.get(user.class, uId);
			if (user1.getUserPassword().equals(oldPwd)) {
				int status = session.createQuery("update user set userPassword=:newPwd where userId=:uId")
						.setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				session.getTransaction().commit();
				return status;
			} else {
				throw new GlobalException("To update password you have to enter current password");
			}
		}
	}

	
	@Override
	public int viewDueAmmount(int uId) {          // getting dueamount
		try (Session session = HibernateUtil.getSession()) {
			int amount = (int) session.createQuery("select userFee from user where userId=:uId").setParameter("uId", uId)
					.uniqueResult();
			return amount;
		}
	}

	@Override
	public int changePhonenumber(int uId, String phone) {
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			int status = session.createQuery("update user set userPhone=:phone where userId=:uId")
					.setParameter("phone", phone).setParameter("uId", uId).executeUpdate();
			session.getTransaction().commit();
			return status;
		}
	}
}