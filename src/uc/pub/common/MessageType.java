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
	public static final int SIGN_IN = 100;
	/**
	 * 登陆成功
	 */
	public static final int SIGN_IN_SUCCESS = 101;
	/**
	 * 登陆失败 密码错误
	 */
	public static final int SIGN_IN_FALSE = 102;
	// *******************************************************************
	/**
	 * 下线
	 */
	public static final int SIGN_OUT = 200;
	/**
	 * 有人下线
	 */
	public static final int SIGN_OUT_NOTICE = 201;

	// *******************************************************************
	/**
	 * 注册
	 */
	public static final int SIGN_UP = 301;
	/**
	 * 注册成功
	 */
	public static final int SIGN_UP_SUCCESS = 302;
	/**
	 * 注册失败
	 */
	public static final int SIGN_UP_FALSE = 303;
	// *******************************************************************
	/**
	 * 请求接受文件
	 */
	public static final int FILE_REQUESTION = 402;

	/**
	 * 确定接收文件
	 */
	public static final int FILE_RECEIVE = 403;

	/**
	 * 
	 */
	public static final int FILE_RECEIVE_OK = 404;
	// *******************************************************************
	/**
	 * 聊天
	 */
	public static final int CLIENT_CHAR = 500;
	/**
	 * 一对一聊天
	 */
	public static final int SINGLETON_CHAR = 510;
	/**
	 * 服务器广播
	 */
	public static final int SERVER_BROADCAST = 520;
	/**
	 * 群聊天
	 */
	public static final int GROUP_CHAR = 530;
	// *******************************************************************
	/**
	 * 更新在线好友
	 */
	public static final int SERVER_UPDATE_FRIENDS = 600;
	/**
	 * 更新群
	 */
	public static final int UPDATE_GROUP = 610;
	/**
	 * 更新在线群好友
	 */
	public static final int UPDATE_GROUP_FRIENDS = 620;
	

}
