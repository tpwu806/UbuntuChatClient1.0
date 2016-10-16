package uc.dal.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import uc.dof.ChatroomJFrame;
import uc.dof.model.OnlineListModel;
import uc.pub.UtilTool;
import uc.pub.common.MessageBean;
import uc.pub.common.MessageType;

public class ClientInputThread implements Runnable {
	
	public Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ChatroomJFrame RoomWindow;
	private String name;
	
	public ClientInputThread(String name,Socket socket,ChatroomJFrame RoomWindow) {
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
					RoomWindow.aau2.play();
					//RoomWindow.chartextArea.append(bean.getInfo() + "\r\n");
					RoomWindow.chartextArea.selectAll();
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
					RoomWindow.aau.play();
					RoomWindow.chartextArea.append(info + bean.getInfo() + "\r\n");
					RoomWindow.chartextArea.selectAll();
					break;
				}
				case MessageType.SERVER_BROADCAST: {
					RoomWindow.aau.play();
					RoomWindow.chartextArea.append( bean.getInfo() + "\r\n");
					RoomWindow.chartextArea.selectAll();
					break;
				}
				case MessageType.FILE_REQUESTION: {
					// ���ڵȴ�Ŀ��ͻ�ȷ���Ƿ�����ļ��Ǹ�����״̬�������������̴߳���
					new Thread() {
						public void run() {
							// ��ʾ�Ƿ�����ļ��Ի���
							int result = JOptionPane.showConfirmDialog(RoomWindow, bean.getInfo());
							switch (result) {
							case 0: { // �����ļ�
								JFileChooser chooser = new JFileChooser();
								chooser.setDialogTitle("�����ļ���"); // ����Ŷ...
								// Ĭ���ļ����ƻ��з��ڵ�ǰĿ¼��
								chooser.setSelectedFile(new File(bean.getFileName()));
								chooser.showDialog(RoomWindow, "����"); // ���ǰ�ť������..
								// ����·��
								String saveFilePath = chooser.getSelectedFile().toString();

								// �����ͻ�CatBean
								MessageBean clientBean = new MessageBean();
								clientBean.setType(MessageType.FILE_RECEIVE);
								clientBean.setName(name); // �����ļ��Ŀͻ�����
								clientBean.setTimer(UtilTool.getTimer());
								clientBean.setFileName(saveFilePath);
								clientBean.setInfo("ȷ�������ļ�");

								// �ж�Ҫ���͸�˭
								HashSet<String> set = new HashSet<String>();
								set.add(bean.getName());
								clientBean.setClients(set); // �ļ���Դ
								clientBean.setTo(bean.getClients());// ����Щ�ͻ������ļ�

								// �����µ�tcp socket ��������, ���Ƕ������ӵĹ���, ���������...
								try {
									ServerSocket ss = new ServerSocket(0); // 0���Ի�ȡ���еĶ˿ں�

									clientBean.setIp(socket.getInetAddress().getHostAddress());
									clientBean.setPort(ss.getLocalPort());
									sendMessage(clientBean); // ��ͨ�����������߷��ͷ�,
																// �����ֱ�ӷ����ļ�����������...

									RoomWindow.isReceiveFile = true;
									// �ȴ��ļ���Դ�Ŀͻ��������ļ�....Ŀ��ͻ��������϶�ȡ�ļ�����д�ڱ�����
									Socket sk = ss.accept();
									RoomWindow.chartextArea.append(UtilTool.getTimer() + "  " + bean.getFileName() + "�ļ�������.\r\n");
									DataInputStream dis = new DataInputStream( // �������϶�ȡ�ļ�
											new BufferedInputStream(sk.getInputStream()));
									DataOutputStream dos = new DataOutputStream( // д�ڱ�����
											new BufferedOutputStream(new FileOutputStream(saveFilePath)));

									int count = 0;
									int num = bean.getSize() / 100;
									int index = 0;
									while (count < bean.getSize()) {
										int t = dis.read();
										dos.write(t);
										count++;

										if (num > 0) {
											if (count % num == 0 && index < 100) {
												RoomWindow.progressBar.setValue(++index);
											}
											RoomWindow.lblNewLabel.setText(
													"���ؽ���:" + count + "/" + bean.getSize() + "  ����" + index + "%");
										} else {
											RoomWindow.lblNewLabel.setText("���ؽ���:" + count + "/" + bean.getSize() + "  ����:"
													+ new Double(new Double(count).doubleValue()
															/ new Double(bean.getSize()).doubleValue() * 100)
																	.intValue()
													+ "%");
											if (count == bean.getSize()) {
												RoomWindow.progressBar.setValue(100);
											}
										}

									}

									// ���ļ���Դ�ͻ�������ʾ���ļ��������
									PrintWriter out = new PrintWriter(sk.getOutputStream(), true);
									out.println(UtilTool.getTimer() + " ���͸�" + name + "���ļ�[" + bean.getFileName()
											+ "]" + "�ļ��������.\r\n");
									out.flush();
									dos.flush();
									dos.close();
									out.close();
									dis.close();
									sk.close();
									ss.close();
									RoomWindow.chartextArea.append(UtilTool.getTimer() + "  " + bean.getFileName()
											+ "�ļ��������.���λ��Ϊ:" + saveFilePath + "\r\n");
									RoomWindow.isReceiveFile = false;
								} catch (Exception e) {
									e.printStackTrace();
								}

								break;
							}
							default: {
								MessageBean clientBean = new MessageBean();
								clientBean.setType(MessageType.FILE_RECEIVE_OK);
								clientBean.setName(name); // �����ļ��Ŀͻ�����
								clientBean.setTimer(UtilTool.getTimer());
								clientBean.setFileName(bean.getFileName());
								clientBean.setInfo(
										UtilTool.getTimer() + "  " + name + "ȡ�������ļ�[" + bean.getFileName() + "]");

								// �ж�Ҫ���͸�˭
								HashSet<String> set = new HashSet<String>();
								set.add(bean.getName());
								clientBean.setClients(set); // �ļ���Դ
								clientBean.setTo(bean.getClients());// ����Щ�ͻ������ļ�

								sendMessage(clientBean);

								break;

							}
							}
						};
					}.start();
					break;
				}
				case MessageType.FILE_RECEIVE: { // Ŀ��ͻ�Ը������ļ���Դ�ͻ���ʼ��ȡ�����ļ������͵�������
					RoomWindow.chartextArea.append(bean.getTimer() + "  " + bean.getName() + "ȷ�������ļ�" + ",�ļ�������..\r\n");
					new Thread() {
						public void run() {

							try {
								RoomWindow.isSendFile = true;
								// ����Ҫ�����ļ��Ŀͻ��׽���
								Socket s = new Socket(bean.getIp(), bean.getPort());
								DataInputStream dis = new DataInputStream(new FileInputStream(RoomWindow.filePath)); // ���ض�ȡ�ÿͻ��ղ�ѡ�е��ļ�
								DataOutputStream dos = new DataOutputStream(
										new BufferedOutputStream(s.getOutputStream())); // ����д���ļ�

								int size = dis.available();

								int count = 0; // ��ȡ����
								int num = size / 100;
								int index = 0;
								while (count < size) {

									int t = dis.read();
									dos.write(t);
									count++; // ÿ��ֻ��ȡһ���ֽ�

									if (num > 0) {
										if (count % num == 0 && index < 100) {
											RoomWindow.progressBar.setValue(++index);

										}
										RoomWindow.lblNewLabel.setText("�ϴ�����:" + count + "/" + size + "  ����" + index + "%");
									} else {
										RoomWindow.lblNewLabel
												.setText("�ϴ�����:" + count + "/" + size + "  ����:"
														+ new Double(new Double(count).doubleValue()
																/ new Double(size).doubleValue() * 100).intValue()
														+ "%");
										if (count == size) {
											RoomWindow.progressBar.setValue(100);
										}
									}
								}
								dos.flush();
								dis.close();
								// ��ȡĿ��ͻ�����ʾ������ϵ���Ϣ...
								BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
								RoomWindow.chartextArea.append(br.readLine() + "\r\n");
								RoomWindow.isSendFile = false;
								br.close();
								s.close();
							} catch (Exception ex) {
								ex.printStackTrace();
							}

						};
					}.start();
					break;
				}
				case MessageType.FILE_RECEIVE_OK: {
					RoomWindow.chartextArea.append(bean.getInfo() + "\r\n");
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
	 * @Description:������Ϣ
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

}
