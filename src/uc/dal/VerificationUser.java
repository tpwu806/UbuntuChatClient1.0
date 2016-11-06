package uc.dal;

import java.net.*;

import uc.common.dto.User;
import uc.common.parcel.*;

import java.io.*;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class VerificationUser {

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	//连接服务器并查询服务器是否拥有该账号
	public ParcelModel askServer(User user){		
		
		//发送消息的包
		UserVerificationParcel send = new UserVerificationParcel(user);	
		//接收消息的包
		ParcelModel message = null;		
		try {
			socket = new Socket("127.0.0.1", 18010);		
			System.out.println("连接服务器");
			//取得连接管道并传送账号信息给服务器
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());			
			objectOutputStream.writeObject(send);
			//获取服务器返回的信息
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			try {
				message = (ParcelModel) objectInputStream.readObject();				
			} catch (ClassNotFoundException e) {
				message = new ErrorParcel();
				e.printStackTrace();
			}			
		} catch (IOException e) {
			message = new ConnectionFailedParcel();
			e.printStackTrace();
		}
		//返回服务器传递过来的信息
		return message;
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	//关闭资源
	public void close(){
		try {
			if(objectInputStream != null){
				
				if(objectOutputStream != null){
					objectOutputStream.close();
				}
				objectInputStream.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}