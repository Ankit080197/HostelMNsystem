package com.HostelMS.service;

import com.HostelMS.exception.GlobalException;

public interface loginregister {
	public void register() throws GlobalException;
	public void login() throws GlobalException;
}