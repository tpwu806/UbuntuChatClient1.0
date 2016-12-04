package uc.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
	private Timestamp gdate;//创建时间
	private String power;//群权限
	private ArrayList<FriendItemModel> friends = new ArrayList<FriendItemModel>();//成员列表
	
	public GroupModel(String groupName){
		this.groupName = groupName;
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

	public Timestamp getGdate() {
		return gdate;
	}

	public void setGdate(Timestamp gdate) {
		this.gdate = gdate;
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
