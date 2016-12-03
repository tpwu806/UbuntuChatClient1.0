package uc.common;

import java.io.Serializable;
import java.util.HashMap;

public class MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 1私聊 0上下线更新 -1下线请求 2请求发送文件 3.确定接收文件
	 */
	private String type; 
	/**
	 * 错误信息
	 */
	private String ErrorMessage;
	/**
	 * 参数
	 */
	private HashMap<?, ?> PO;
	/**
	 * 返回结果
	 */
	private Object object;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public HashMap<?, ?> getPO() {
		return PO;
	}
	public void setPO(HashMap<?, ?> pO) {
		PO = pO;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

}