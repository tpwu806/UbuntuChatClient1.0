package uc.common;

import java.io.Serializable;

public class MessageObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	public Object ResponseObject;
	public int ErrorCode;
	public String ErrorString;

	public MessageObject() {
		this.type = null;
		this.ResponseObject = null;
		this.ErrorCode = 0;
		this.ErrorString = null;
	}

	public MessageObject(Object RO) {
		this.ResponseObject = RO;
		this.ErrorCode = 0;
		this.ErrorString = null;
		this.type = null;
	}

	public MessageObject(Object RO, int EC) {
		this.ResponseObject = RO;
		this.ErrorCode = EC;
		this.ErrorString = null;
		this.type = null;
	}

	public MessageObject(String type , Object RO, int EC, String ES) {
		this.type = type;
		this.ResponseObject = RO;
		this.ErrorCode = EC;
		this.ErrorString = ES;
	}

	public int GetErrorCode() {
		return this.ErrorCode;
	}

	public String getType() {
		return type;
	}
}