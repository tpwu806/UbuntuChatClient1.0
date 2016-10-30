package uc.common.parcel;

import java.io.Serializable;

import uc.common.dto.UserInformation;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class UserThroughParcel implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1956551231810567245L;

	//消息类型
	private final MessageEnum message = MessageEnum.USER_THROUGH;
	
	private final UserInformation user;
	
	public UserThroughParcel(UserInformation user){
		this.user = user;
	} 
	
	public MessageEnum getMessage(){
		return message;
	}
	
	public UserInformation getUserInformation(){
		return user;
	}
}
