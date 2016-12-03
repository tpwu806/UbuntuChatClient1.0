package uc.common;

import java.io.Serializable;

public class MessageModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sender;
	private String recerver;
	private Object info;
	private String time;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecerver() {
		return recerver;
	}
	public void setRecerver(String recerver) {
		this.recerver = recerver;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}	

}
