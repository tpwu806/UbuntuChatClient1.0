package uc.pub.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class MessageBean implements Serializable {

	private String name;// 登录名
	private String pwd;// 密码
	private int type; // 1私聊 0上下线更新 -1下线请求 2请求发送文件 3.确定接收文件

	private HashSet<String> clients; // 存放选中的客户
	private HashSet<String> to;
	private String friendName;

	private String info;// 内容
	private String timer;// 时间

	private String ip;// ip地址
	private int port;// 端口号
	private String fileName;// 文件名称
	private int size;// 大小

	private ArrayList<GroupTable> groups;//群信息
	private String groupName;//群名称
	private ArrayList<UserInfo> users;
	
	public ArrayList<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserInfo> users) {
		this.users = users;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ArrayList<GroupTable> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<GroupTable> groups) {
		this.groups = groups;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public HashSet<String> getClients() {
		return clients;
	}

	public void setClients(HashSet<String> clients) {
		this.clients = clients;
	}

	public HashSet<String> getTo() {
		return to;
	}

	public void setTo(HashSet<String> to) {
		this.to = to;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}