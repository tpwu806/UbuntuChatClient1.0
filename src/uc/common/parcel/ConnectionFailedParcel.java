package uc.common.parcel;

import java.io.Serializable;

/**
 * @Description: 连接失败对象 （这个应该是多余的）
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class ConnectionFailedParcel implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -998253461261267572L;
	
	private final MessageEnum message = MessageEnum.CONNECTION_FAILED;
	
	public MessageEnum getMessage() {
		return message;
	}
}
