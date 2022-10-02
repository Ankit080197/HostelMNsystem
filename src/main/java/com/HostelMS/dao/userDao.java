package com.HostelMS.dao;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public interface userDao{
	public int viewDueAmmount(int uId);
	public user viewProfile(int uId);
	public user viewRoom(int uId);
	public int changePhonenumber(int uId , String phone);
	public int changePassword(int uId , String oldPwd ,String newPwd) throws GlobalException;	
	
}