package com.HostelMS.serviceImpl;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Scanner;
import com.HostelMS.dao.adminDao;
import com.HostelMS.model.room;
import com.HostelMS.model.user;
import com.HostelMS.daoImpl.adminDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.service.admindashboard;

public class admindashboardImpl implements admindashboard {
	static admindashboard adDash = new admindashboardImpl();
	static adminDao dao = new adminDaoImpl();
	static Logger lg = Logger.getLogger(admindashboardImpl.class);
	static Scanner miku = new Scanner(System.in);

	@Override
	public void dashboard() throws GlobalException {
		lg.info("\t\t\t\twelcome to Admin dashboard   ");
		int x = 0;
		while (x < 10) {
			lg.info("\n\tPress 1 : To create room \t\tPress 2 :To allot room to users \n\tPress 3 :To view rooms  \t\tPress 4 :To view users \n\tPress 5 :To view users in room \t\tPress 6 :To view user profile \n\tPress 7 :To add dues \t\t\tPress 8 :To show dues to paid \n\tPress 9 :To delete a user");
			x = miku.nextInt();

			switch (x) {

			case 1 -> adDash.createRoom();
			case 2 -> adDash.allotRoom();
			case 3 -> adDash.viewRooms();
			case 4 -> adDash.viewUsers();
			case 5 -> adDash.userInARoom();
			case 6 -> adDash.viewUserProfile();
			case 7 -> adDash.addDueAmount();
			case 8 -> adDash.paidDueAmount();
			case 9 -> adDash.deleteUser();

			}
		}

	}

	@Override
	public void viewRooms() {

		List<room> st = dao.viewRooms();
		for (room r : st) {

			lg.info("\tRoom Id Is :" + r.getRoomId() + "\tRoom Name Is :" + r.getRoomName() + "\tRoom Type Is :"
					+ r.getRoomType());
		}

	}

	@Override
	public void viewUsers() {

		List<user> st = dao.viewUsers();
		for (user user : st) {
			lg.info("\tUser id is :" + user.getUserId() + "\tUser Name is :" + user.getUserName() + "\tUser password :"
					+ user.getUserPassword() + "\tUser addres is :" + user.getUserAddress() + "\tUser fee is :"
					+ user.getUserFee() + "\tUser phone is :" + user.getUserPhone() + "\tUser Role is :" + user.getUserRole());
		}
	}

	@Override
	public void userInARoom() {

		lg.info("enter room id :");
		int rId = miku.nextInt();
		List<user> st = dao.userInARoom(rId);
		for (user user : st) {

			lg.info("\tUser id is:" + user.getUserId() + "\tUser Name is:" + user.getUserName() + "\tUser addres is : "
					+ user.getUserAddress() + "\tUser fee is :" + user.getUserFee() + "\tUser phone is :" + user.getUserPhone()
					+ "\tUser Role is :" + user.getUserRole());
		}

	}

	@Override
	public void createRoom() {

		try {
			lg.info(" enter room details ");
			lg.info(" enter room name :");
			String rName = miku.next();
			lg.info(" enter room id :");
			int rId = miku.nextInt();

			lg.info("enter room type :");
			String rType = miku.next();
			room r1 = new room();

			r1.setRoomId(rId);
			r1.setRoomName(rName);
			r1.setRoomType(rType);

			int st = dao.createRoom(r1);
			if (st == 1) {

				lg.info("room created successfully ;)");
			}
		}

		catch (Exception e) {

			lg.info(e.getMessage());
		}
	}

	@Override
	public void allotRoom() throws GlobalException {

		lg.info(" Alloting room to users ");
		lg.info("enter user id :");
		int uId = miku.nextInt();
		lg.info("enter room id :");
		int rId = miku.nextInt();
		int st = dao.allotRoom(rId, uId);

		if (st == 1) {

			lg.info("room has been alloted ;) ");
		} else {

			throw new GlobalException("user/room id is invalid");
		}

	}

	@Override
	public void deleteUser() throws GlobalException {

		lg.info("enter user id :");
		int uId = miku.nextInt();
		int st = dao.deleteUser(uId);
		if (st == 1) {

			lg.info("User Deleted Successfully ;(");
		} else {

			throw new GlobalException("user does'nt exist");
		}

	}

	@Override
	public void addDueAmount() throws GlobalException {
		// TODO Auto-generated method stub
		lg.info("enter amount which u want to add");
		int amount = miku.nextInt();

		lg.info("enter user id :");
		int uId = miku.nextInt();
		int st = dao.addDueAmount(amount, uId);

		if (st == 1) {

			lg.info("Due added successfully");

		} else {
			throw new GlobalException("user not found");
		}

	}

	@Override
	public void viewUserProfile() {

		lg.info("enter user id ");
		int uId = miku.nextInt();
		user user = dao.viewUserProfile(uId);

		lg.info(user);

	}

	@Override
	public void paidDueAmount() throws GlobalException {

		lg.info("enter amount which is already paid by user");
		int amount = miku.nextInt();

		lg.info("enter user id :");
		int uId = miku.nextInt();
		int st = dao.paidDueAmount(amount, uId);
		if (st == 1) {

			lg.info("final due is analizd successfull");

		} else {

			throw new GlobalException("User not found ");
		}
	}

}