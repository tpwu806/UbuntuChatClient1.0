package uc.common.parcel;

import java.io.Serializable;

import uc.common.dto.User;

/**
 * @Description: 账号验证
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class UserVerificationParcel  implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1956551231810567245L;

	//消息类型
	private final MessageEnum message = MessageEnum.USER_VERIFICATION;
	//账户
	private final User user;
	
	public UserVerificationParcel(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public MessageEnum getMessage(){
		return message;
	}
}
