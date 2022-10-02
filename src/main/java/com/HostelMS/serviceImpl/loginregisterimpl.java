package com.HostelMS.serviceImpl;


import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.HostelMS.App;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.daoImpl.hostelMSDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;
import com.HostelMS.service.admindashboard;
import com.HostelMS.service.loginregister;
import com.HostelMS.service.userdashboard;

public class loginregisterimpl implements loginregister {
	static Logger lg=Logger.getLogger(App.class);
	static Scanner miku=new Scanner(System.in);
	static hostelMSDao dao=new hostelMSDaoImpl();
	
	//registration method
	public void register() throws GlobalException{
		lg.info("welcome to registeration");
		lg.info("Enter Username");
		String uname=miku.next();
		lg.info("Create Password");
		String upwd=miku.next();
		lg.info("Enter Phone number");
		String uphone=miku.next();
		lg.info("Enter Address");
		String uaddress=miku.next();
		
		user user1=new user();
		user1.setUserName(uname);
		user1.setUserPassword(upwd);
		user1.setUserPhone(uphone);
		user1.setUserAddress(uaddress);
		user1.setUserRole("student");
		user1.setUserRoom(null);
		user1.setUserFee(0);
		//regular expressions to check data correctness
		if(Pattern.matches("[a-zA-Z]{4,}", uname)&&Pattern.matches("[a-zA-Z0-9@#]{6,}",upwd)&&Pattern.matches("[0-9]{10}", uphone))
		{
			//saving the user details
			int status=dao.registration(user1);
			//log.info(status);
			if(status==1) {
				lg.info("Registration success");
			}
			else {
				throw new GlobalException("Something went wrong");
			}
		}
		else {
			throw new GlobalException("Invalid data");
		}
}

	public void login()throws GlobalException {
		lg.info("  welcome ;) to Login  ");
		
		lg.info("Enter username");
		String username=miku.next();
		lg.info("Enter password");
		String password=miku.next();
		user user1=dao.login(username, password);        //checking login
		lg.info("Hello "+user1.getUserName()+" Login Success");     //success message
		userdashboard udash=new userdashboardImpl();
		admindashboard admndash=new admindashboardImpl();
			if(user1.getUserRole().equals("student")) {
			udash.dashboard();
				}
		else if(user1.getUserRole().equals("admin")) {
			admndash.dashboard();
		}
	}

}