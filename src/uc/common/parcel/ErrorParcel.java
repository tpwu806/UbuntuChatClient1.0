package uc.common.parcel;

import java.io.Serializable;

/**
 * @Description: 以后是要加一个出错信息对象吧
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class ErrorParcel implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2160311154617580289L;
	
	private final MessageEnum message = MessageEnum.ERROR;
	
	public MessageEnum getMessage() {
		return message;
	}
}
