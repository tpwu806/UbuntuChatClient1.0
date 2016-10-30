package uc.common.parcel;

import java.io.Serializable;

/**
 * @Description: 服务器与客户端传递消息的包
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class NewsParcel implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2658530149257278585L;
	
	//消息类型
	private final MessageEnum message = MessageEnum.NEWS;
	//发送方
	private final String sender;
	//发送方信息
	private final String senderInformation;
	//接收方
	private final String recipient;
	//内容
	private final Object content;
	
	public NewsParcel(String sender, String senderInformation, String recipient, Object content){
		this.sender = sender;
		this.senderInformation =senderInformation;
		this.recipient = recipient;
		this.content = content;
	}
	
	public MessageEnum getMessage(){
		return message;
	}

	public String getSender() {
		return sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public Object getContent() {
		return content;
	}

	public String getSenderInformation() {
		return senderInformation;
	}
}
