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
			// 不停的从服务器接收信息
			while (running) {
				ois = new ObjectInputStream(socket.getInputStream());
				final MessageBean bean = (MessageBean) ois.readObject();
				switch (bean.getType()) {
				case MessageType.SERVER_UPDATE_FRIENDS: {
					System.out.println(bean.getType());
					// 更新列表
					RoomWindow.onlines.clear();
					HashSet<String> clients = bean.getClients();
					Iterator<String> it = clients.iterator();
					while (it.hasNext()) {
						String ele = it.next();
						if (name.equals(ele)) {
							RoomWindow.onlines.add(ele + "(我)");
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
					String info = bean.getTimer() + "  " + bean.getName() + " 对 " + bean.getClients() + "说:\r\n";
					System.out.println(info);
					if (info.contains(name)) {
						info = info.replace(name, "我");
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
					// 由于等待目标客户确认是否接收文件是个阻塞状态，所以这里用线程处理
					new Thread() {
						public void run() {
							// 显示是否接收文件对话框
							int result = JOptionPane.showConfirmDialog(RoomWindow, bean.getInfo());
							switch (result) {
							case 0: { // 接收文件
								JFileChooser chooser = new JFileChooser();
								chooser.setDialogTitle("保存文件框"); // 标题哦...
								// 默认文件名称还有放在当前目录下
								chooser.setSelectedFile(new File(bean.getFileName()));
								chooser.showDialog(RoomWindow, "保存"); // 这是按钮的名字..
								// 保存路径
								String saveFilePath = chooser.getSelectedFile().toString();

								// 创建客户CatBean
								MessageBean clientBean = new MessageBean();
								clientBean.setType(MessageType.FILE_RECEIVE);
								clientBean.setName(name); // 接收文件的客户名字
								clientBean.setTimer(UtilTool.getTimer());
								clientBean.setFileName(saveFilePath);
								clientBean.setInfo("确定接收文件");

								// 判断要发送给谁
								HashSet<String> set = new HashSet<String>();
								set.add(bean.getName());
								clientBean.setClients(set); // 文件来源
								clientBean.setTo(bean.getClients());// 给这些客户发送文件

								// 创建新的tcp socket 接收数据, 这是额外增加的功能, 大家请留意...
								try {
									ServerSocket ss = new ServerSocket(0); // 0可以获取空闲的端口号

									clientBean.setIp(socket.getInetAddress().getHostAddress());
									clientBean.setPort(ss.getLocalPort());
									sendMessage(clientBean); // 先通过服务器告诉发送方,
																// 你可以直接发送文件到我这里了...

									RoomWindow.isReceiveFile = true;
									// 等待文件来源的客户，输送文件....目标客户从网络上读取文件，并写在本地上
									Socket sk = ss.accept();
									RoomWindow.chartextArea.append(UtilTool.getTimer() + "  " + bean.getFileName() + "文件保存中.\r\n");
									DataInputStream dis = new DataInputStream( // 从网络上读取文件
											new BufferedInputStream(sk.getInputStream()));
									DataOutputStream dos = new DataOutputStream( // 写在本地上
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
													"下载进度:" + count + "/" + bean.getSize() + "  整体" + index + "%");
										} else {
											RoomWindow.lblNewLabel.setText("下载进度:" + count + "/" + bean.getSize() + "  整体:"
													+ new Double(new Double(count).doubleValue()
															/ new Double(bean.getSize()).doubleValue() * 100)
																	.intValue()
													+ "%");
											if (count == bean.getSize()) {
												RoomWindow.progressBar.setValue(100);
											}
										}

									}

									// 给文件来源客户发条提示，文件保存完毕
									PrintWriter out = new PrintWriter(sk.getOutputStream(), true);
									out.println(UtilTool.getTimer() + " 发送给" + name + "的文件[" + bean.getFileName()
											+ "]" + "文件保存完毕.\r\n");
									out.flush();
									dos.flush();
									dos.close();
									out.close();
									dis.close();
									sk.close();
									ss.close();
									RoomWindow.chartextArea.append(UtilTool.getTimer() + "  " + bean.getFileName()
											+ "文件保存完毕.存放位置为:" + saveFilePath + "\r\n");
									RoomWindow.isReceiveFile = false;
								} catch (Exception e) {
									e.printStackTrace();
								}

								break;
							}
							default: {
								MessageBean clientBean = new MessageBean();
								clientBean.setType(MessageType.FILE_RECEIVE_OK);
								clientBean.setName(name); // 接收文件的客户名字
								clientBean.setTimer(UtilTool.getTimer());
								clientBean.setFileName(bean.getFileName());
								clientBean.setInfo(
										UtilTool.getTimer() + "  " + name + "取消接收文件[" + bean.getFileName() + "]");

								// 判断要发送给谁
								HashSet<String> set = new HashSet<String>();
								set.add(bean.getName());
								clientBean.setClients(set); // 文件来源
								clientBean.setTo(bean.getClients());// 给这些客户发送文件

								sendMessage(clientBean);

								break;

							}
							}
						};
					}.start();
					break;
				}
				case MessageType.FILE_RECEIVE: { // 目标客户愿意接收文件，源客户开始读取本地文件并发送到网络上
					RoomWindow.chartextArea.append(bean.getTimer() + "  " + bean.getName() + "确定接收文件" + ",文件传送中..\r\n");
					new Thread() {
						public void run() {

							try {
								RoomWindow.isSendFile = true;
								// 创建要接收文件的客户套接字
								Socket s = new Socket(bean.getIp(), bean.getPort());
								DataInputStream dis = new DataInputStream(new FileInputStream(RoomWindow.filePath)); // 本地读取该客户刚才选中的文件
								DataOutputStream dos = new DataOutputStream(
										new BufferedOutputStream(s.getOutputStream())); // 网络写出文件

								int size = dis.available();

								int count = 0; // 读取次数
								int num = size / 100;
								int index = 0;
								while (count < size) {

									int t = dis.read();
									dos.write(t);
									count++; // 每次只读取一个字节

									if (num > 0) {
										if (count % num == 0 && index < 100) {
											RoomWindow.progressBar.setValue(++index);

										}
										RoomWindow.lblNewLabel.setText("上传进度:" + count + "/" + size + "  整体" + index + "%");
									} else {
										RoomWindow.lblNewLabel
												.setText("上传进度:" + count + "/" + size + "  整体:"
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
								// 读取目标客户的提示保存完毕的信息...
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
	 * @Description:发送信息
	 * @auther: wutp 2016年10月15日
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
