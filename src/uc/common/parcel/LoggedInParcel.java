package uc.common.parcel;

import java.io.Serializable;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class LoggedInParcel implements ParcelModel, Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3642094832934367141L;
	
	private final MessageEnum message = MessageEnum.LOGGED_IN;
	
	public MessageEnum getMessage() {
		return message;
	}
}
