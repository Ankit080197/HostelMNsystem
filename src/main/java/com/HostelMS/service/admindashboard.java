package com.HostelMS.service;

import com.HostelMS.exception.GlobalException;

public interface admindashboard {
	public void viewRooms();
	public void viewUserProfile();
	public void userInARoom();
	public void createRoom();
	public void viewUsers();
	public void dashboard() throws GlobalException;
	public void allotRoom() throws GlobalException;
	public void deleteUser() throws GlobalException;
	public void addDueAmount() throws GlobalException;
	public void paidDueAmount() throws GlobalException;

}
