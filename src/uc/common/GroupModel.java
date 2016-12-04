package uc.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description: 群
 * @author wutp 2016年12月03日
 * @version 1.0
 */
public class GroupModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1129594901962565953L;
	
	private String gid;
	private String groupName;
	private Object gdate;//创建时间
	private byte[] picture;//群头像
	private String power;//群权限
	private ArrayList<FriendItemModel> friends = new ArrayList<FriendItemModel>();//成员列表
	
	public GroupModel(){
		
	}
	public GroupModel(String groupName){
		this.groupName = groupName;
	}

	public GroupModel(String gid, String groupName, Object gdate, byte[] picture, String power,
			ArrayList<FriendItemModel> friends) {
		this.gid = gid;
		this.groupName = groupName;
		this.gdate = gdate;
		this.picture = picture;
		this.power = power;
		this.friends = friends;
	}
	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Object getGdate() {
		return gdate;
	}

	public void setGdate(Object gdate) {
		this.gdate = gdate;
	}

	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public ArrayList<FriendItemModel> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<FriendItemModel> friends) {
		this.friends = friends;
	}	
	
}
