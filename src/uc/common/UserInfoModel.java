package uc.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class UserInfoModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1142876585817962444L;
	
	//基本账户
	private final UserModel userModel;
	//昵称
	private String nickName;
	//个性签名
	private String signature;
	//好友列表
	private ArrayList<GroupModel> friendList;
	
	public UserInfoModel(UserModel userModel,
			String nickName,
			ArrayList<GroupModel> friendList){
		this.userModel = userModel;
		this.nickName = nickName;
		this.friendList = friendList;
	}

	public UserModel getUser() {
		return userModel;
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
