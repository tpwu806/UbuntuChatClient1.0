package uc.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
	
	private String cno;//群分组编号
	private String cName;//群分组名称
	private ArrayList<GroupModel> groupList = new ArrayList<GroupModel>();//群列表
	
	public CrowdGroupModel(String cName){
		this.cName = cName;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public ArrayList<GroupModel> getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList<GroupModel> groupList) {
		this.groupList = groupList;
	}
	
}
