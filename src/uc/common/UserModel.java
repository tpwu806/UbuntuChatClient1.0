package uc.common;

import java.io.Serializable;

/**
 * @Description: 代表一个账户
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class UserModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -84845435813766646L;
	
	private String ucId;//用户uc
	private String password;//密码
	private String headURL;//头像
	private StateEnum state;//状态
	private boolean rememberPassWord = false;//记住密码
	private boolean automaticLogin = false;//自动登录	
	private String nickName;//昵称	
	private String signature;//个性签名
	
	public UserModel(String ucId, 
			String headURL, 
			StateEnum state, 
			boolean rememberPassWord, 
			boolean automaticLogin) {
		
		this.ucId = ucId;
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

	public String getUcId() {
		return ucId;
	}

	public void setUcId(String ucId) {
		this.ucId = ucId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}	
	public String toString(){
		return ucId;
	}
}
