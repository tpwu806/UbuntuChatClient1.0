package uc.dal.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import uc.dof.ChatroomJFrame;
import uc.pub.common.MessageBean;

/**
 * @Description: �ͻ��˺�̨
 * @author wutp 2016��10��13��
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
	 * @Description:��½ע��
	 * @auther: wutp 2016��10��15��
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
	 * @Description:�ر�Socket
	 * @auther: wutp 2016��10��14��
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
