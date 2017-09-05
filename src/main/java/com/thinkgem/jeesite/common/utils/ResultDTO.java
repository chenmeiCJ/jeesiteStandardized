package com.thinkgem.jeesite.common.utils;

import java.io.Serializable;

public class ResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1443394184664485821L;
	
	private boolean success;
	
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
