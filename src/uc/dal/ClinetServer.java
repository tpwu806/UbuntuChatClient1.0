package uc.dal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import uc.common.MessageBean;

/**
 * @Description: 客户端后台
 * @author wutp 2016年10月13日
 * @version 1.0
 */
public class ClinetServer {
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public ClinetServer() {
		try {
			socket = new Socket("localhost", 8520);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	/**
	 * @Description:登陆注册
	 * @auther: wutp 2016年10月15日
	 * @param m
	 * @return
	 * @return MessageBean
	 */
	public MessageBean sendUserInfoToServer(MessageBean m){
		MessageBean ms = null;
		try {			
			oos= new ObjectOutputStream(socket.getOutputStream());			
			oos.writeObject(m);
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			ms = (MessageBean) ois.readObject();			
		}catch(Exception e){
			e.printStackTrace();
			closeSockt();
		}
		return ms;		
	}
	
	/**
	 * @Description:关闭Socket
	 * @auther: wutp 2016年10月14日
	 * @return void
	 */
	public void closeSockt() {
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
