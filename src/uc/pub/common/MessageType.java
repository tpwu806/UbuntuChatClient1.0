package uc.pub.common;

/**
 * @Description: MessageType
 * @author wutp 2016年10月14日
 * @version 1.0
 */
public class MessageType {

	/**
	 * 登录
	 */
	public static final String SIGN_IN = "SIGN_IN";
	/**
	 * 登陆成功
	 */
	public static final String SIGN_IN_SUCCESS = "SIGN_IN_SUCCESS";
	/**
	 * 登陆失败 密码错误
	 */
	public static final String SIGN_IN_FALSE = "SIGN_IN_FALSE";
	// *******************************************************************
	/**
	 * 下线
	 */
	public static final String SIGN_OUT = "SIGN_OUT";
	/**
	 * 有人下线
	 */
	public static final String SIGN_OUT_NOTICE = "SIGN_OUT_NOTICE";

	// *******************************************************************
	/**
	 * 注册
	 */
	public static final String SIGN_UP = "SIGN_UP";
	/**
	 * 注册成功
	 */
	public static final String SIGN_UP_SUCCESS = "SIGN_UP_SUCCESS";
	/**
	 * 注册失败
	 */
	public static final String SIGN_UP_FALSE = "SIGN_UP_FALSE";
	// *******************************************************************
	/**
	 * 请求接受文件
	 */
	public static final String FILE_REQUESTION = "FILE_REQUESTION";

	/**
	 * 确定接收文件
	 */
	public static final String FILE_RECEIVE = "FILE_RECEIVE";

	/**
	 * 
	 */
	public static final String FILE_RECEIVE_OK = "FILE_RECEIVE_OK";
	// *******************************************************************
	/**
	 * 聊天
	 */
	public static final String GROUP_CHAT = "GROUP_CHAT";
	/**
	 * 一对一聊天
	 */
	public static final String SINGLETON_CHAT = "SINGLETON_CHAT";
	/**
	 * 服务器广播
	 */
	public static final String SERVER_BROADCAST = "SERVER_BROADCAST";

	// *******************************************************************
	/**
	 * 更新在线好友
	 */
	public static final String SERVER_UPDATE_FRIENDS = "SERVER_UPDATE_FRIENDS";
	/**
	 * 更新群
	 */
	public static final String GET_GROUP_LIST = "GET_GROUP_LIST";
	/**
	 * 更新在线群好友
	 */
	public static final String GET_GROUP_FRIEND_LIST = "GET_GROUP_FRIEND_LIST";
	

}
