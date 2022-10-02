package com.HostelMS.serviceImpl;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.HostelMS.dao.userDao;
import com.HostelMS.model.user;
import com.HostelMS.service.userdashboard;
import com.HostelMS.daoImpl.userDaoImpl;
import com.HostelMS.exception.GlobalException;

public class userdashboardImpl implements userdashboard {

	static Logger lg = Logger.getLogger(userdashboardImpl.class);
	static Scanner miku = new Scanner(System.in);
	static userdashboardImpl udl = new userdashboardImpl();
	static userDao dao = new userDaoImpl();
	static int userId;

	@Override
	public void viewRoom() { // room details of the user

		user user1 = dao.viewRoom(userId); // calling dao
		lg.info("Hello " + user1.getUserName() + " your room number is" + user1.getUserRoom().getRoomId()
				+ " room name is " + user1.getUserRoom().getRoomName() + " and it is "
				+ user1.getUserRoom().getRoomType() + " room");
	}

	@Override
	public void viewDueAmmount() {

		int amount = dao.viewDueAmmount(userId); // calling dao
		lg.info("your fee due is :" + amount);
	}

	// viewProfile with toString
	@Override
	public void viewProfile() {

		user user1 = dao.viewProfile(userId);
		lg.info(user1);

	}

	@Override
	public void changePhonenumber() { // to change phone number
		lg.info("Enter New Phone number");
		String phone = miku.next();

		int st = dao.changePhonenumber(userId, phone);

		if (st == 1) {
			lg.info("Phone number updated");
		}
	}

	@Override
	public void changePassword() throws GlobalException { // change paswrd
		lg.info("Enter OLD Password");
		String oldpwd = miku.next();
		lg.info("Enter New Password");
		String newpwd = miku.next();
		int st = dao.changePassword(userId, oldpwd, newpwd);
		if (st == 1) {
			lg.info("password changed");
		}
	}

	@Override
	public void dashboard() throws GlobalException {
		lg.info("\t\t\t userdashboard   ");
		int x = 0;
		while (x < 6) { // user can select operation
			lg.info("\nPress 1 for viewRoom\nPress 2 for view dueAmount \nPress 3 for view profile\nPress 4 for Update Phone number \nPress 5 for Change password");
			x = miku.nextInt();
			switch (x) {
			case 1 -> udl.viewRoom();
			case 2 -> udl.viewDueAmmount();
			case 3 -> udl.viewProfile();
			case 4 -> udl.changePhonenumber();
			case 5 -> udl.changePassword();
			}
		}
	}

}