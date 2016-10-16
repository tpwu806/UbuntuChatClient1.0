package uc.dal.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

import uc.dof.ChatJFrame;
import uc.dof.FriendListJFrame;
import uc.dof.model.OnlineListModel;
import uc.pub.common.MessageBean;
import uc.pub.common.MessageType;

public class ClientServerThread implements Runnable {
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private FriendListJFrame RoomWindow;
	private String name;
	
	public ClientServerThread(String name,Socket socket,FriendListJFrame RoomWindow) {
		this.name=name;
		this.socket = socket;
		this.RoomWindow=RoomWindow;	
	}
	
	volatile boolean running = true;
	@Override
	public void run() {
		try {
			// ��ͣ�Ĵӷ�����������Ϣ
			while (running) {
				ois = new ObjectInputStream(socket.getInputStream());
				final MessageBean bean = (MessageBean) ois.readObject();
				switch (bean.getType()) {
				case MessageType.SERVER_UPDATE_FRIENDS: {
					System.out.println(bean.getType());
					// �����б�
					RoomWindow.onlines.clear();
					HashSet<String> clients = bean.getClients();
					Iterator<String> it = clients.iterator();
					while (it.hasNext()) {
						String ele = it.next();
						if (name.equals(ele)) {
							RoomWindow.onlines.add(ele + "(��)");
						} else {
							RoomWindow.onlines.add(ele);
						}
					}

					RoomWindow.listmodel = new OnlineListModel(RoomWindow.onlines);
					RoomWindow.list.setModel(RoomWindow.listmodel);
					//RoomWindow.aau2.play();
					//RoomWindow.chartextArea.append(bean.getInfo() + "\r\n");
					//RoomWindow.chartextArea.selectAll();
					break;
				}
				case -1: {

					return;
				}
				case MessageType.CLIENT_CHAR: {
					String info = bean.getTimer() + "  " + bean.getName() + " �� " + bean.getClients() + "˵:\r\n";
					System.out.println(info);
					if (info.contains(name)) {
						info = info.replace(name, "��");
					}
					//RoomWindow.aau.play();
					//RoomWindow.chartextArea.append(info + bean.getInfo() + "\r\n");
					//RoomWindow.chartextArea.selectAll();
					break;
				}
				case MessageType.SERVER_BROADCAST: {
					//RoomWindow.aau.play();
					//RoomWindow.chartextArea.append( bean.getInfo() + "\r\n");
					//RoomWindow.chartextArea.selectAll();
					break;
				}
				case MessageType.SINGLETON_CHAR: {
					singletonChat(bean);
					break;
				}
				default: {
					break;
				}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.exit(0);
		}

	}
		
	/**
	 * @Description:ͳһ�������������Ϣ
	 * @auther: wutp 2016��10��15��
	 * @param clientBean
	 * @return void
	 */
	public void sendMessage(MessageBean clientBean) {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(clientBean);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description:��һ��һ����������ʾ����Ӧ���촰����
	 * @auther: wutp 2016��10��16��
	 * @param bean
	 * @return void
	 */
	private void singletonChat(MessageBean bean){
		ChatJFrame chatJFrame = RoomWindow.chatWinMap.get(bean.getName());
		
		if(chatJFrame != null){
			chatJFrame.showMessage(bean);
		}else{//��ʵ����Ϣ����
			System.out.println("��������Ϣ�������" + bean.getInfo());
		}
	}

}
