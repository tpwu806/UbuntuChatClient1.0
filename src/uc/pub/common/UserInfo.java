package uc.pub.common;

import java.io.Serializable;

/**
 * @Description: 用户信息
 * @author wutp 2016年10月23日
 * @version 1.0
 */
public class UserInfo implements Serializable {
	private Integer uc;
	private String nickName;
	private String status;
	public Integer getUc() {
		return uc;
	}
	public void setUc(Integer uc) {
		this.uc = uc;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
