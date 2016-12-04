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
	//好友分组
	private ArrayList<FriendGroupModel> friendList;
	//群分组
	private ArrayList<CrowdGroupModel> groupsList;
	
	public UserInfoModel(UserModel userModel,
			ArrayList<FriendGroupModel> friendList,
			ArrayList<CrowdGroupModel> groupsList){
		this.userModel = userModel;
		this.friendList = friendList;
		this.groupsList = groupsList;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public ArrayList<FriendGroupModel> getFriendList() {
		return friendList;
	}

	public void setFriendList(ArrayList<FriendGroupModel> friendList) {
		this.friendList = friendList;
	}

	public ArrayList<CrowdGroupModel> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(ArrayList<CrowdGroupModel> groupsList) {
		this.groupsList = groupsList;
	}
	
}
