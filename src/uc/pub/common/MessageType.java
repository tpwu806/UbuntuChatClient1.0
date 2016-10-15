package uc.pub.common;

/**
 * @Description: MessageType
 * @author wutp 2016年10月14日
 * @version 1.0
 */
public class MessageType {
	
	/**************************************************************
	 *命名规则
	 *客户端请求服务信息 1
	 *登陆00       
	 *下线01
	 *聊天02
	 *请求接收文件03
	 *确认请求接收文件04
	 *注册05
	 *
	 *服务器返回结果2 
	 *登陆成功100
	 *登陆失败101
	 */
	
	/**
	 *  登录
	 */
	public static final int CLIENT_SIGN_IN = 100;
	
	/**
	 *  下线
	 */
	public static final int CLIENT_SIGN_OUT = 110;
	
	/**
	 * 聊天
	 */
	public static final int CLIENT_CHAR = 120;
	
	/**
	 * 请求接受文件
	 */
	public static final int CLIENT_REQUESTION_RECEIVE = 130;
	
	/**
	 *  确定接收文件
	 */
	public static final int CLIENT_AGREE_RECEIVE = 140;
	
	/**
	 * 
	 */
	public static final int CLIENT_AGREE_RECEIVE_OK = 4;
	
	/**
	 *  注册
	 */
	public static final int CLIENT_SIGN_UP = 150;
	
	/**
	 * 登陆成功
	 */
	public static final int SERVER_SIGN_IN_SUCCESS = 200;
	/**
	 * 登陆失败 密码错误
	 */
	public static final int SERVER_SIGN_IN_FALSE = 201;
	
	/**
	 * 登陆失败 已经登陆
	 */
	public static final int SERVER_SIGN_IN_FALSE2 = 202;	
	/**
	 * 有人登陆通知
	 */
	public static final int SERVER_SIGN_IN_NOTICE = 203;
	/**
	 * 服务器通知
	 */
	public static final int SERVER_NOTICE = 204;
	/**
	 *  你被强制下线
	 */
	public static final int SERVER_SIGN_OUT = 210;
	/**
	 *  有人下线
	 */
	public static final int SERVER_SIGN_OUT_NOTICE = 211;
	/**
	 * 注册成功
	 */
	public static final int SERVER_SIGN_UP_SUCCESS = 250;
	/**
	 * 注册失败 已经注册
	 */
	public static final int SERVER_SIGN_UP_FALSE = 251;
	/**
	 * 注册失败 已经注册
	 */
	public static final int SERVER_UPDATE_FRIENDS = 251;

}
