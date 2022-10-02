// admin implementation

package com.HostelMS.daoImpl;

import java.util.List;

import javax.persistence.Query;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.adminDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.*;

import org.hibernate.Session;

public class adminDaoImpl implements adminDao {

	@Override
	public int createRoom(room r1) throws GlobalException { // room creation
		try (Session session = HibernateUtil.getSession()) { // Auto-generated method stub
			session.beginTransaction();

			String roomName = r1.getRoomName();

			room r2 = null;
			// To get data of r2 if available
			r2 = (room) session.createQuery("from room where roomName =:roomName").setParameter("roomName", roomName)
					.uniqueResult();
			if (r2 == null) {
				session.save(r1);
				session.getTransaction().commit();
				return 1;
			} else {
				// throw a new exception if r2 is not null
				throw new GlobalException("room is already exist");
			}

		}

	}

	@Override

	public int deleteUser(int uId) { // delete any user
		try (Session session = HibernateUtil.getSession()) {// TODO Auto-generated method stub
			session.beginTransaction();
			int st = session.createQuery("delete from user where userId =:uId").setParameter("uId", uId)
					.executeUpdate();
			return st;

		}
	}

	@Override
	// method to allot room to user
	public int allotRoom(int rId, int uId) {
		// TODO Auto-generated method stub
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			// HQL query to alloting the room to any user
			int st = session.createQuery("update user set userRoom_roomId =:rId where userId=:uId ")
					.setParameter("uId", uId).setParameter("rId", rId).executeUpdate();
			session.getTransaction().commit();

			return st;
		}
	}

	@Override

	public int paidDueAmount(int amount, int uId) { // total amount to be paid by the user
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			int dueFee = (int) session.createQuery("select userFee from user where userId =:uId")
					.setParameter("uId", uId).uniqueResult();
			dueFee -= amount;
			int st = session.createQuery("update user set userFee =:dueFee where userId =:uId")
					.setParameter("dueFee", dueFee).setParameter("uId", uId).executeUpdate();
			return st;

		}
	}

	@Override
	public user viewUserProfile(int uId) { // method to view user all details
		try (Session session = HibernateUtil.getSession()) { // TODO Auto-generated method stub
			session.beginTransaction();
			user u1 = session.get(user.class, uId);
			return u1;
		}
	}

	@Override

	public int addDueAmount(int amount, int uId) { // method to add due over the user
		try (Session session = HibernateUtil.getSession()) { // TODO Auto-generated method stub
			session.beginTransaction();
			user u = session.get(user.class, uId);
			int dueFee = u.getUserFee();
			dueFee += amount;
			// HQL Query
			int st = session.createQuery("update user set userFee =:dueFee where userId =:uId")
					.setParameter("dueFee", dueFee).setParameter("uId", uId).executeUpdate();
			return st;
		}
	}

	@Override
	public List<user> viewUsers() { // method to view all the users
		try (Session session = HibernateUtil.getSession()) { // TODO Auto-generated method stub
			Query q = session.createQuery("from user");
			@SuppressWarnings("unchecked")
			List<user> ul = q.getResultList();
			return ul;
		}
	}

	@Override
	public List<user> userInARoom(int rId) {
		try (Session session = HibernateUtil.getSession()) { // TODO Auto-generated method stub
			Query q = session.createQuery("from user where userRoom_roomId =:rId").setParameter("rId", rId);
			List<user> rl = q.getResultList();
			return rl;
		}
	}

	@Override
// method to view all the rooms existed in the hostel
	public List<room> viewRooms() {
		// autoclosable session object
		try (Session session = HibernateUtil.getSession()) {
			// getting rows of a room table
			Query q = session.createQuery("from room");
			@SuppressWarnings("unchecked")
			List<room> rl = q.getResultList();
			return rl;
		}
	}
}