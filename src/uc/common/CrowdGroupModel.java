package uc.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description: 群分组
 * @author wutp 2016年12月03日
 * @version 1.0
 */
public class CrowdGroupModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1129594901962565953L;
	
	private String cid;//群分组编号
	private String cname;//群分组名称
	private ArrayList<GroupModel> groupList = new ArrayList<GroupModel>();//群列表
	
	public CrowdGroupModel(){
	
	}
	public CrowdGroupModel(String cname){
		this.cname = cname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public ArrayList<GroupModel> getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList<GroupModel> groupList) {
		this.groupList = groupList;
	}
	
}
