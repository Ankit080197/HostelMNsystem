// Hostel Management System 
// using with JPA, HIBERNATE, MySQL
//@Sandeep kumar

package com.HostelMS;
import com.HostelMS.service.loginregister;
import com.HostelMS.exception.GlobalException;
import java.util.Scanner;
import com.HostelMS.serviceImpl.loginregisterimpl;
import org.apache.log4j.Logger;


public class App { 															// main method

	static Logger lg = Logger.getLogger(App.class); 						// loggers for output
	
	public static void main(String[] args) throws GlobalException {
		Scanner miku = new Scanner(System.in);
		loginregister lgrg = new loginregisterimpl();						 // object of log-in-register-impl()

		lg.info("\t\t\t\t\t Hostel_Management System  ");
		lg.info("\n Press 1 for Login \nPress 2 for Registeration ");
		int Ak = miku.nextInt();

		switch (Ak) {
		case 1 -> lgrg.login();
		case 2 -> lgrg.register();
		}
		miku.close();
	}
}