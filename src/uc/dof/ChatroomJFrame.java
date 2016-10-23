package uc.dof;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import uc.dof.model.CellRenderer;
import uc.dof.model.OnlineListModel;
import uc.pub.UtilTool;
import uc.pub.common.MessageBean;
import uc.pub.common.MessageType;

/**
 * @Description: 群聊天界面
 * @author wutp 2016年10月15日
 * @version 1.0
 */
public class ChatroomJFrame extends JFrame {

	private static final long serialVersionUID = 6129126482250125466L;

	private  JPanel contentPane;
	public  JTextArea chartextArea;//聊天信息显示区域
	public  JList<String> list;
	public  String filePath;// 文件路径
	public  JLabel lblNewLabel;// 文件传输提示
	public  JProgressBar progressBar;
	public  ListModel listmodel;

	public  Vector<String> gFriends;// 在线用户
	public  boolean isSendFile = false;
	public  boolean isReceiveFile = false;

	// 声音
	private  File file, file2;
	private  URL cb, cb2;
	public  AudioClip aau;
	public  AudioClip aau2;

	public JTextArea sendJTextArea;// 发送区
	private JButton sendButton;// 发送按钮
	private JButton closeButton;// 关闭按钮

	private FriendListJFrame WIN;
	private  String name;
	private String groupName;
	/**
	 * Create the frame.
	 */	
	public ChatroomJFrame(String u_name, String groupName,FriendListJFrame win) {
		// 赋值
		this.name = u_name;
		this.groupName=groupName;
		this.WIN=win;
		
		init();
		
		MessageBean clientBean = new MessageBean();
		clientBean.setType(MessageType.UPDATE_GROUP_FRIENDS);
		clientBean.setGroupName(groupName);
		sendMessage(clientBean);
	}

	private void init() {

		gFriends = new Vector<String>();
		//gFriends.add(name);
		/*
		 * SwingUtilities.updateComponentTreeUI(this);
		 * 
		 * try { UIManager.setLookAndFeel(
		 * "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); } catch
		 * (ClassNotFoundException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (InstantiationException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } catch
		 * (IllegalAccessException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (UnsupportedLookAndFeelException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); }
		 */

		setTitle(groupName);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 100, 688, 510);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon("images/聊天室1.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
			}

		};
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//聊天信息显示区域
		JScrollPane charScrollPane = new JScrollPane();
		charScrollPane.setBounds(10, 10, 410, 300);
		getContentPane().add(charScrollPane);

		chartextArea = new JTextArea();
		chartextArea.setEditable(false);
		chartextArea.setLineWrap(true);// 激活自动换行功能
		chartextArea.setWrapStyleWord(true);// 激活断行不断字功能
		chartextArea.setFont(new Font("sdf", Font.BOLD, 13));
		charScrollPane.setViewportView(chartextArea);

		// 打字区域
		JScrollPane writeScrollPane = new JScrollPane();
		writeScrollPane.setBounds(10, 347, 411, 97);
		getContentPane().add(writeScrollPane);

		sendJTextArea = new JTextArea();
		sendJTextArea.setLineWrap(true);// 激活自动换行功能
		sendJTextArea.setWrapStyleWord(true);// 激活断行不断字功能
		writeScrollPane.setViewportView(sendJTextArea);

		// 关闭按钮
		closeButton = new JButton("\u5173\u95ED");
		closeButton.setBounds(214, 448, 60, 30);
		getContentPane().add(closeButton);

		// 发送按钮
		sendButton = new JButton("\u53D1\u9001");
		sendButton.setBounds(313, 448, 60, 30);
		getRootPane().setDefaultButton(sendButton);
		getContentPane().add(sendButton);

		// 在线客户列表
		listmodel = new OnlineListModel(gFriends);
		list = new JList<String>(listmodel);
		list.setCellRenderer(new CellRenderer());
		list.setOpaque(false);
		Border etch = BorderFactory.createEtchedBorder();
		list.setBorder(BorderFactory.createTitledBorder(etch, "<" + groupName + ">" + "在线客户:", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("sdf", Font.BOLD, 20), Color.green));

		JScrollPane onlineJScrollPane = new JScrollPane(list);
		onlineJScrollPane.setBounds(430, 10, 245, 375);
		onlineJScrollPane.setOpaque(false);
		onlineJScrollPane.getViewport().setOpaque(false);
		getContentPane().add(onlineJScrollPane);

		// 文件传输栏
		progressBar = new JProgressBar();
		progressBar.setBounds(430, 390, 245, 15);
		progressBar.setMinimum(1);
		progressBar.setMaximum(100);
		getContentPane().add(progressBar);

		// 文件传输提示
		lblNewLabel = new JLabel("\u6587\u4EF6\u4F20\u9001\u4FE1\u606F\u680F:");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(430, 410, 245, 15);
		getContentPane().add(lblNewLabel);

		try {
			// 消息提示声音
			file = new File("sounds/eo.wav");
			//cb = file.toURL();
			cb = file.toURI().toURL();
			aau = Applet.newAudioClip(cb);
			// 上线提示声音
			file2 = new File("sounds/ding.wav");
			//cb2 = file2.toURL();
			cb2 = file2.toURI().toURL();
			aau2 = Applet.newAudioClip(cb2);
			aau2.play();
			// 启动客户接收线程
			// new ClientInputThread().start();
			/*clientInputThread = new ClientInputThread(name, clientSocket, this);
			Thread t = new Thread(clientInputThread);
			t.start();*/

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送按钮
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionSend();
			}
		});

		// 关闭按钮
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionClose();
			}
		});

		// 离开
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ActionWindowClosing();
			}
		});

		// 列表监听
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Actionlist(e);
			}
		});
	}

	/**
	 * 处理发送按钮
	 * 
	 * @Description:
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void ActionSend() {
		String info = sendJTextArea.getText();
		List<String> to = list.getSelectedValuesList();

		if (to.size() < 1) {
			JOptionPane.showMessageDialog(getContentPane(), "请选择聊天对象");
			return;
		}
		if (to.toString().contains(groupName + "(我)")) {
			JOptionPane.showMessageDialog(getContentPane(), "不能向自己发送信息");
			return;
		}
		if (info.equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "不能发送空信息");
			return;
		}

		MessageBean clientBean = new MessageBean();
		clientBean.setType(MessageType.CLIENT_CHAR);
		clientBean.setName(name);
		String time = UtilTool.getTimer();
		clientBean.setTimer(time);
		clientBean.setInfo(info);
		HashSet<String> set = new HashSet<String>();
		set.addAll(to);
		clientBean.setClients(set);

		// 自己发的内容也要现实在自己的屏幕上面
		chartextArea.append(time + " 我对" + to + "说:\r\n" + info + "\r\n");

		sendMessage(clientBean);
		sendJTextArea.setText(null);
		sendJTextArea.requestFocus();
	}

	/**
	 * @Description:处理关闭事件
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void ActionClose() {
		if (isSendFile || isReceiveFile) {
			JOptionPane.showMessageDialog(contentPane, "正在传输文件中，您不能离开...", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else {
			closeButton.setEnabled(false);
			MessageBean clientBean = new MessageBean();
			clientBean.setType(-1);
			clientBean.setName(name);
			clientBean.setTimer(UtilTool.getTimer());
			sendMessage(clientBean);
		}
	}

	/**
	 * 处理关闭窗口事件
	 */
	private void ActionWindowClosing() {
		if (isSendFile || isReceiveFile) {
			JOptionPane.showMessageDialog(contentPane, "正在传输文件中，您不能离开...", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(getContentPane(), "您确定要离开聊天室");
			if (result == 0) {
				MessageBean clientBean = new MessageBean();
				clientBean.setType(-1);
				clientBean.setName(name);
				clientBean.setTimer(UtilTool.getTimer());
				sendMessage(clientBean);
			}
		}
	}

	/**
	 * @param e
	 * @Description:处理列表监听事件
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void Actionlist(MouseEvent e) {
		List<String> to = list.getSelectedValuesList();
		if (e.getClickCount() == 2) {

			if (to.toString().contains(name + "(我)")) {
				JOptionPane.showMessageDialog(getContentPane(), "不能向自己发送文件");
				return;
			}

			// 双击打开文件文件选择框
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("选择文件框"); // 标题哦...
			chooser.showDialog(getContentPane(), "选择"); // 这是按钮的名字..

			// 判定是否选择了文件
			if (chooser.getSelectedFile() != null) {
				// 获取路径
				filePath = chooser.getSelectedFile().getPath();
				File file = new File(filePath);
				// 文件为空
				if (file.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), filePath + "文件为空,不允许发送.");
					return;
				}

				MessageBean clientBean = new MessageBean();
				clientBean.setType(2);// 请求发送文件
				clientBean.setSize(new Long(file.length()).intValue());
				clientBean.setName(name);
				clientBean.setTimer(UtilTool.getTimer());
				clientBean.setFileName(file.getName()); // 记录文件的名称
				clientBean.setInfo("请求发送文件");

				// 判断要发送给谁
				HashSet<String> set = new HashSet<String>();
				set.addAll(list.getSelectedValuesList());
				clientBean.setClients(set);
				sendMessage(clientBean);
			}
		}
	}

	/**
	 * @Description:统一调用发送消息clientInputThread
	 * @auther: wutp 2016年10月15日
	 * @param clientBean
	 * @return void
	 */
	private void sendMessage(MessageBean clientBean) {
		WIN.server.sendMessage(clientBean);
	}

}
