package uc.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class UserInformation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1142876585817962444L;
	
	//基本账户
	private final User user;
	//昵称
	private String nickName;
	//个性签名
	private String signature;
	//好友列表
	private ArrayList<GroupModel> friendList;
	
	public UserInformation(User user,
			String nickName,
			ArrayList<GroupModel> friendList){
		this.user = user;
		this.nickName = nickName;
		this.friendList = friendList;
	}

	public User getUser() {
		return user;
	}

	public String getNickName() {
		return nickName;
	}

	public String getSignature() {
		return signature;
	}

	public ArrayList<GroupModel> getFriendList() {
		return friendList;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setFriendList(ArrayList<GroupModel> friendList) {
		this.friendList = friendList;
	}	
}
