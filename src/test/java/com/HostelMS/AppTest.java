package com.HostelMS;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.adminDao;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.dao.userDao;

import com.HostelMS.model.user;

import org.hibernate.Session;
import com.HostelMS.daoImpl.adminDaoImpl;
import com.HostelMS.daoImpl.hostelMSDaoImpl;
import com.HostelMS.daoImpl.userDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.room;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AppTest {

	@Test
	@Disabled
	@DisplayName("Registeration Test")
	public void registrationTest() {

		hostelMSDao dao = new hostelMSDaoImpl();
		user u1 = new user();

		u1.setUserName("sandeep");
		u1.setUserPassword("111999");
		u1.setUserPhone("9717748672");
		u1.setUserRole("coder");
		u1.setUserAddress("delhi");

		user u2 = new user();
		u2.setUserName("ankit ");
		u2.setUserPassword("Ankit123");
		u2.setUserPhone("721068949");
		u2.setUserRole("buisnessmen");
		u2.setUserAddress("dwarika");

		assertAll(
				// positive testing for registration method
				() -> assertEquals(1, dao.registration(u1)),
				// negative testing for registration method using exception
				() -> assertThrows(GlobalException.class, () -> dao.registration(u2)));

	}

	// testing of changePassword method
	@Test
	@Disabled
	public void testChangePassword() {

		userDao dao = new userDaoImpl();
		assertAll(
				// positive testing for changePassword method
				() -> assertEquals(1, dao.changePassword(4, "Ankit123", "AnkitSharma1234")),
				// negative testing for changePassword method using exception
				() -> assertThrows(Exception.class, () -> dao.changePassword(4, "111999", "111999@san"))

		);
	}

	// 3
	// testing over changePhonenumber method
	@Test
	@Disabled
	public void testchangePhone() {

		userDao dao = new userDaoImpl();
		assertAll(
				// positive test case for password changing
				() -> assertEquals(1, dao.changePhonenumber(3, "9717748672")),
				// negative test case for password changing
				() -> assertEquals(0, dao.changePhonenumber(50, "7210689790"))

		);
	}

	// 4 performing testing over createRoom method
	@Test
	@Disabled
	public void testCreateRoom() {

		// creating dao object to create room entity
		adminDao dao = new adminDaoImpl();
		room r1 = new room();
		r1.setRoomId(2);
		r1.setRoomName("AC");
		r1.setRoomType("Non AC");

		room r2 = new room();
		r2.setRoomId(1);
		r2.setRoomName("single");
		r2.setRoomType("Multiple");
		assertAll(
				// test to create room
				() -> assertEquals(1, dao.createRoom(r1)),
				// testing to check if exception works in createRoom method
				() -> assertThrows(GlobalException.class, () -> dao.createRoom(r2))

		);
	}

	// 5 performing testing operation over login() method
	@Test
	@Disabled
	public void testLogin() throws GlobalException {
		hostelMSDao dao = new hostelMSDaoImpl();

		// To get user to compare
		Session ses = HibernateUtil.getSession();
		user u1 = ses.get(user.class, 3);

		user u2 = dao.login("Ankit", "Ankit123");

		assertAll(

				// test case to check if create method works properly or not
				() -> assertEquals(u1.getUserId(), u2.getUserId()),
				// testing the exception in login() method
				() -> assertThrows(GlobalException.class, () -> dao.login("vaibhav", "Ankit123")));

	}

	@Test
	@Disabled
	public void testAddDueAmount() {

		adminDao dao = new adminDaoImpl();

		assertAll(
				// test case to check addDueAmount() method
				() -> assertEquals(1, dao.addDueAmount(500, 4)),

				// testing to check exception in addDueAmount() method
				() -> assertThrows(Exception.class, () -> dao.addDueAmount(1000, 20))

		);

	}

	// 7 Testing operation over paidDueAmount() method
	@Test
	public void testPaidDueAmount() {

		adminDao dao = new adminDaoImpl();

		assertAll(
				// test case to check if paidDueAmount() method working properly or not
				() -> assertEquals(1, dao.paidDueAmount(100, 4)),
				// testing to check exception in paidDueAmount()method
				() -> assertThrows(Exception.class, () -> dao.paidDueAmount(1000, 20))

		);

	}
}
