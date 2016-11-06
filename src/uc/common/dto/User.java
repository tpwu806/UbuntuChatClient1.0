package uc.common.dto;

import java.io.Serializable;

/**
 * @Description: 代表一个账户
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -84845435813766646L;
	
	private String user;//用户uc
	private String password;//密码
	private String headURL;//头像
	private StateEnum state;//状态
	private boolean rememberPassWord = false;//记住密码
	private boolean automaticLogin = false;//自动登录
	
	public User(String user, 
			String headURL, 
			StateEnum state, 
			boolean rememberPassWord, 
			boolean automaticLogin) {
		
		this.user = user;
		this.headURL = headURL;
		this.state = state;
		this.rememberPassWord = rememberPassWord;
		this.automaticLogin = automaticLogin;
	}

	public String getHeadURL() {
		return headURL;
	}

	public void setHeadURL(String headURL) {
		this.headURL = headURL;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

	public boolean isRememberPassWord() {
		return rememberPassWord;
	}

	public void setRememberPassWord(boolean rememberPassWord) {
		this.rememberPassWord = rememberPassWord;
	}

	public boolean isAutomaticLogin() {
		return automaticLogin;
	}

	public void setAutomaticLogin(boolean automaticLogin) {
		this.automaticLogin = automaticLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString(){
		return user;
	}
}
