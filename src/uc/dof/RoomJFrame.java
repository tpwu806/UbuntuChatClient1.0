package uc.dof;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import uc.common.FriendGroupModel;
import uc.common.FriendItemModel;
import uc.common.GroupModel;
import uc.common.MessageBean;
import uc.common.MessageModel;
import uc.common.MessageType;
import uc.pub.assembly.FillitFrame;
import uc.pub.assembly.FriendItem;
import uc.pub.assembly.GradientPanel;
import uc.pub.assembly.GroupContainer;
import uc.pub.assembly.GroupUserItem;
import uc.pub.assembly.MainPanel;
import uc.pub.assembly.SenioButton;
import uc.pub.assembly.VerlicelColumn;
import uc.pub.tool.ChangeImage;
import uc.pub.tool.Fonts;
import uc.pub.tool.ImagesFunction;

/**
 * @Description:群 
 * @author wutp 2016年12月8日
 * @version 1.0
 */
public class RoomJFrame extends FillitFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 好友的状态模型
	private GroupModel model;
	private FriendListJFrame MainJFrame;
	// 与服务器的连接
	//private Socket socket;

	// 窗体,
	//private FillitFrame frame;
	// 包含界面背景、包含其他组件
	private ImageIcon bg;
	private MainPanel mainPanel;

	// 构建头部
	private JPanel forSystemButton;
	private JLabel forHead;
	private JLabel seeInformation;
	private JButton seeZone;
	private JLabel survey;

	// 关闭与最小化按钮
	private JButton closeButton;
	private JButton minimizationButton;

	// 用于组合中部
	private JPanel chatPane;

	// 聊天面板
	// 显示消息
	private JTextPane displayMessage;
	private Document document;
	// 工具栏//工具栏的按钮其实是JTo的，需要改一下
	private GradientPanel toolBar;
	// 字体按钮
	private SenioButton fonts;
	// 表情按钮
	private SenioButton face;
	// 魔法表情
	private SenioButton richface;
	// 输入框
	private JTextPane textField;
	private KeyListener textFieldKeyListener;
	private DateFormat dateFormat;
	// 底部面板
	private GradientPanel bottomPanel;
	// 关闭按钮
	private JButton closeButton2;
	// 发送按钮
	private JButton sendButton;

	// 功能栏
	private JPanel functionBar;
	private SenioButton videoCall;
	private SenioButton voiceConversation;
	private SenioButton fileTransfer;
	private SenioButton screenSharing;

	// 伸缩按钮
	private JLabel telescopicButton;
	// 右边栏
	private GradientPanel eastPane;

	private CardLayout cardLayout ;
	private JPanel forCard ;
	private JScrollPane scrollPane1 ;
	private JPanel friendPanel ;
	// 构建
	public RoomJFrame(GroupModel model, FriendListJFrame fj) {
		super(691, 582, 9, 9);
		this.model = model;
		this.MainJFrame=fj;
		//this.socket = socket;
		createFrame();
	}

	// 构建
	public void createFrame() {
		// 窗体
		//frame = new FillitFrame(591, 482, 9, 9);
		// 主面板
		bg = new ImageIcon("Image\\ChatFrame\\bg.jpg");
		mainPanel = new MainPanel(bg, 9, 9);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(75, 153, 202));
		this.add(mainPanel);
		//frame.add(mainPanel);
		// 构建头部
		createTop();
		// 中部
		createCenterPanel();

		//frame.setVisible(true);
		this.setVisible(true);
	}

	public void createTop() {

		// 龙套
		JPanel pane1 = new JPanel(new BorderLayout());
		pane1.setPreferredSize(new Dimension(mainPanel.getWidth(), 50));
		pane1.setOpaque(false);
		mainPanel.add(pane1, "North");

		// 龙套
		JPanel pane2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
		pane2.setOpaque(false);
		pane1.add(pane2, "West");

		// 头像
		forHead = new JLabel();
		forHead.setPreferredSize(new Dimension(42, 42));
		if (model.getPicture() != null) {
			forHead.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(model.getPicture()), 42, 42, 5));
		} else {
			forHead.setIcon(ChangeImage.roundedCornerIcon(ImagesFunction.defaultHead, 42, 42, 5));
		}
		pane2.add(forHead);

		// 龙套
		JPanel pane3 = new JPanel(null);
		pane3.setOpaque(false);
		pane1.add(pane3, "Center");

		// 查看对方资料按钮
		seeInformation = new JLabel(model.getGroupName());
		seeInformation.setCursor(new Cursor(Cursor.HAND_CURSOR));
		seeInformation.setFont(new Font("微软雅黑", 0, 20));
		seeInformation.setBounds(0, 8, 200, 23);
		pane3.add(seeInformation);
		// 查看空间按钮
		seeZone = new JButton(new ImageIcon("Image\\AppPluginIcon\\qzoneicon.png"));
		seeZone.setContentAreaFilled(false);
		seeZone.setFocusPainted(false);
		seeZone.setCursor(new Cursor(Cursor.HAND_CURSOR));
		seeZone.setBounds(0, 34, 16, 16);
		pane3.add(seeZone);

		survey = new JLabel("朋友、同学");
		survey.setFont(Fonts.MicrosoftAccor12);
		survey.setBounds(20, 34, 100, 15);
		pane3.add(survey);

		forSystemButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		forSystemButton.setPreferredSize(new Dimension(70, 30));
		forSystemButton.setOpaque(false);
		assembly();
		pane1.add(forSystemButton, "East");
	}

	// 构建 顺序：构建、设置经过/点击图像、设置提示、添加操作监听、加入容器
	private void assembly() {
		minimizationButton = new JButton(new ImageIcon("Image\\SystemButton\\minimize.png"));
		minimizationButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\minimize_hover.png"));
		minimizationButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\minimize_press.png"));
		minimizationButton.setToolTipText("最小化");
		minimizationButton.setContentAreaFilled(false);
		minimizationButton.setFocusPainted(false);
		minimizationButton.setFocusable(false);
		minimizationButton.setPreferredSize(new Dimension(30, 20));
		minimizationButton.addActionListener(actionAdapter);
		forSystemButton.add(minimizationButton);

		closeButton = new JButton(new ImageIcon("Image\\SystemButton\\clouse.png"));
		closeButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\clouse_hover.png"));
		closeButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\clouse_press.png"));
		closeButton.setToolTipText("关闭");
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setFocusable(false);
		closeButton.setPreferredSize(new Dimension(40, 20));
		closeButton.addActionListener(actionAdapter);
		forSystemButton.add(closeButton);
	}

	public void createCenterPanel() {
		// 聊天面板
		chatPane = new JPanel(new BorderLayout());
		chatPane.setOpaque(false);
		mainPanel.add(chatPane, "Center");
		// 功能栏
		createFunctionBar();
		// 聊天面板
		createChatPanel();
		// 右边栏
		createExtensionPanel();
	}

	private void createFunctionBar() {

		functionBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		functionBar.setOpaque(false);
		chatPane.add(functionBar, "North");

		videoCall = new SenioButton();
		videoCall.setToolTipText("视频通话");
		videoCall.setSendEvent(false);
		videoCall.addOpenButton();
		JLabel forVideoCallIcon = new JLabel(new ImageIcon("Image\\ChatFrame\\Group\\Icons\\TopToolbar\\b19m0_0.png"));
		videoCall.add(forVideoCallIcon);
		videoCall.setPreferredSize(new Dimension(42, 30));
		functionBar.add(videoCall);
		// 一个测试
		videoCall.openButtonAddMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("弹出按钮:" + "这是检查点击事件是否有冲突");
			}
		});
		videoCall.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("主按钮:" + "这是检查点击事件是否有冲突");
			}
		});

		voiceConversation = new SenioButton(new ImageIcon("Image\\ChatFrame\\Group\\Icons\\TopToolbar\\b9m0_0.png"));
		voiceConversation.setToolTipText("语音通话");
		voiceConversation.setPreferredSize(new Dimension(30, 30));
		functionBar.add(voiceConversation);

		fileTransfer = new SenioButton(new ImageIcon("Image\\ChatFrame\\Group\\Icons\\TopToolbar\\b1m0_0.png"));
		fileTransfer.setToolTipText("传送文件");
		fileTransfer.setPreferredSize(new Dimension(30, 30));
		functionBar.add(fileTransfer);

		screenSharing = new SenioButton(new ImageIcon("Image\\ChatFrame\\Group\\Icons\\TopToolbar\\b17m0_0.png"));
		screenSharing.setToolTipText("屏幕分享");
		screenSharing.setPreferredSize(new Dimension(30, 30));
		functionBar.add(screenSharing);
	}

	// 构建
	private void createChatPanel() {

		// 包含消息显示区和输入框区
		JPanel pane3 = new JPanel(new BorderLayout());
		pane3.setOpaque(false);
		chatPane.add(pane3, "Center");

		// 消息显示框
		displayMessage = new JTextPane() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				int width = getWidth();
				int height = getHeight();
				LinearGradientPaint p;
				p = new LinearGradientPaint(0.0f, 0.0f, this.getWidth(), this.getHeight(),
						new float[] { 0.0f, 0.5f, 0.501f, 1.0f },
						new Color[] { new Color(255, 255, 255, 250), new Color(255, 255, 255, 222),
								new Color(255, 255, 255, 210), new Color(255, 255, 255, 180) });
				g2d.setPaint(p);
				g2d.fillRect(0, 0, width, height);
				super.paintComponent(g);
			}
		};
		displayMessage.setOpaque(false);
		displayMessage.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		displayMessage.setFont(new Font("微软雅黑", 0, 13));
		JScrollPane scroll1 = new JScrollPane(displayMessage, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll1.setBorder(null);
		scroll1.setOpaque(false);
		scroll1.getViewport().setOpaque(false);
		pane3.add(scroll1, "Center");

		document = displayMessage.getDocument();

		// 组合输入区块
		JPanel pane4 = new JPanel(new BorderLayout());
		pane4.setOpaque(false);
		pane4.setPreferredSize(new Dimension(pane4.getPreferredSize().width, 158));
		pane3.add(pane4, "South");
		// 工具栏
		toolBar = new GradientPanel(new FlowLayout(FlowLayout.LEFT, 5, 3), new Color(255, 255, 255, 0),
				new Color(255, 255, 255, 180));
		toolBar.setPreferredSize(new Dimension(pane4.getPreferredSize().width, 28));
		toolBar.setOpaque(false);
		pane4.add(toolBar, "North");
		// 字体按钮
		fonts = new SenioButton(new ImageIcon("Image\\ChatFrame\\toolBar\\aio_quickbar_font.png"));
		fonts.setPreferredSize(new Dimension(24, 22));
		fonts.setToolTipText("选择字体");
		toolBar.add(fonts);
		// 表情按钮
		face = new SenioButton(new ImageIcon("Image\\ChatFrame\\toolBar\\aio_quickbar_face.png"));
		face.setPreferredSize(new Dimension(24, 22));
		face.setToolTipText("选择表情");
		toolBar.add(face);
		// 魔法表情
		richface = new SenioButton(new ImageIcon("Image\\ChatFrame\\toolBar\\aio_quickbar_richface.png"));
		richface.setPreferredSize(new Dimension(24, 22));
		richface.setToolTipText("魔法表情");
		toolBar.add(richface);

		// 输入框
		textField = new JTextPane() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				int width = getWidth();
				int height = getHeight();
				LinearGradientPaint p;
				p = new LinearGradientPaint(0.0f, 0.0f, this.getWidth(), this.getHeight(),
						new float[] { 0.0f, 0.5f, 0.501f, 1.0f },
						new Color[] { new Color(255, 255, 255, 250), new Color(255, 255, 255, 222),
								new Color(255, 255, 255, 210), new Color(255, 255, 255, 180) });
				g2d.setPaint(p);
				g2d.fillRect(0, 0, width, height);
				super.paintComponent(g);
			}

			public void setOpaque(boolean b) {
				super.setOpaque(false);
			}
		};
		textField.setOpaque(false);
		textField.setFont(new Font("微软雅黑", 0, 13));
		textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		JScrollPane scroll2 = new JScrollPane(textField, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setBorder(null);
		scroll2.setOpaque(false);
		scroll2.getViewport().setOpaque(false);
		pane4.add(scroll2, "Center");
		// 用于格式化时间
		dateFormat = DateFormat.getDateTimeInstance(2, 2);
		// 添加监听：目前就只监听是否按钮ENTER键、按下ENTER键就把消息发送出去
		textFieldKeyListener = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("send");
					actionSend();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		textField.addKeyListener(textFieldKeyListener);

		// 底部面板
		bottomPanel = new GradientPanel(new BorderLayout(), new Color(255, 255, 255, 0), new Color(255, 255, 255, 150));
		bottomPanel.setOpaque(false);
		pane4.add(bottomPanel, "South");

		JLabel fo = new JLabel("  warau");
		bottomPanel.add(fo);

		// 布局关闭和发送按钮
		JPanel pane7 = new JPanel();
		pane7.setOpaque(false);
		bottomPanel.add(pane7, "East");

		closeButton2 = new JButton("关闭(C)");
		closeButton2.setFocusPainted(false);
		closeButton2.addActionListener(actionAdapter);
		pane7.add(closeButton2);
		sendButton = new JButton("发送(S)");
		sendButton.setFocusPainted(false);
		sendButton.addActionListener(actionAdapter);
		pane7.add(sendButton);
	}

	private void createExtensionPanel() {
		//
		JPanel pane = new JPanel(new BorderLayout());
		pane.setOpaque(false);
		chatPane.add(pane, "East");

		eastPane = new GradientPanel(new BorderLayout());
		eastPane.setOpaque(false);
		eastPane.setGradientColor(new Color(255, 255, 255, 200));
		eastPane.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 6));
		pane.add(eastPane);

		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		p1.setPreferredSize(new Dimension(142, 128));
		eastPane.add(p1, "North");
		JLabel i2 = new JLabel(new ImageIcon("Image\\MainPanel\\NoviceGuide\\qshow.jpg"));
		p1.add(i2);

		//JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//p2.setPreferredSize(new Dimension(142, 242));
		
		/*JLabel i1 = new JLabel(new ImageIcon("Image\\MainPanel\\NoviceGuide\\qshow.jpg"));
		p2.add(i1);*/
		
		/*****************************************************/
		
		
				
		friendPanel = new JPanel(new VerlicelColumn(5)){
			/**
			 * 
			 */
			private static final long serialVersionUID = 2549744010310864813L;
			public void paintComponent(Graphics g){
				g.setColor(new Color(255,255,255,235));
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		friendPanel.setOpaque(false);
		
		scrollPane1 = new JScrollPane(friendPanel);
		scrollPane1.setOpaque(false);
		scrollPane1.getViewport().setOpaque(false);
		scrollPane1.setBorder(BorderFactory.createMatteBorder(0,
				1,
				0,
				1,
				Color.black));
		ArrayList<FriendItemModel> friends = model.getFriends();
		for(FriendItemModel user : friends){			
			GroupUserItem gUserItem = new GroupUserItem(user);	
			friendPanel.add(gUserItem);
			//gUserItem.addMouseListener(mouseAdapter);
		}
		
		//p2.add(scrollPane1);
		scrollPane1.setVisible(true);
		eastPane.add(scrollPane1, "South");
		/*****************************************************/
		
		
		// 折叠按钮
		GradientPanel forTelescopicButton = new GradientPanel(new BorderLayout());
		forTelescopicButton.setGradientColor(new Color(255, 255, 255, 205), new Color(255, 255, 255, 150));
		forTelescopicButton.setLongitudinal(false);
		forTelescopicButton.setOpaque(false);
		pane.add(forTelescopicButton, "West");

		telescopicButton = new JLabel();
		telescopicButton.setPreferredSize(new Dimension(8, telescopicButton.getPreferredSize().height));
		telescopicButton.setOpaque(false);
		telescopicButton.addMouseListener(new MouseAdapter() {
			private boolean invisible = true;
			private final ImageIcon hoverImg = new ImageIcon("Image\\ChatFrame\\aio_flexleft_normal.png");
			private final ImageIcon selectedHoverImg = new ImageIcon("Image\\ChatFrame\\aio_flexright_normal.png");

			public void mouseEntered(MouseEvent e) {
				if (invisible) {
					telescopicButton.setIcon(hoverImg);
				} else {
					telescopicButton.setIcon(selectedHoverImg);
				}
			}

			public void mouseExited(MouseEvent e) {
				telescopicButton.setIcon(null);
			}

			public void mousePressed(MouseEvent e) {
				if (invisible) {
					invisible = false;
					eastPane.setVisible(false);
				} else {
					invisible = true;
					eastPane.setVisible(true);
				}
			}
		});
		forTelescopicButton.add(telescopicButton);
	}

	// 请求获取焦点
	public void textFieldRequestFocus() {
		//frame.setExtendedState(JFrame.NORMAL);
		this.setExtendedState(JFrame.NORMAL);
		textField.requestFocus();
	}

	// 提供向消息显示框插入内容的方法
	@Deprecated
	public void insertString(String str, AttributeSet a) {
		try {
			document.insertString(document.getLength(), str, a);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	// 传递数据
	@Deprecated
	public void sendOut() {
		String sender = FriendListJFrame.user.getUserModel().toString();
		String senderInformation = FriendListJFrame.user.getUserModel().getNickName() + " "
				+ dateFormat.format(Calendar.getInstance().getTime()) + "\n";
		displayMessage.replaceSelection(senderInformation);
		String resipient = model.getGid();
		String content = textField.getText() + "\n";
		textField.setText("");
		displayMessage.replaceSelection(content);
		// 打包
		///NewsParcel parcel = new NewsParcel(sender, senderInformation, resipient, content);
		/*try {
			System.out.println("发送");
			ObjectOutputStream objectoutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectoutputStream.writeObject(parcel);
		} catch (IOException e) {
			System.out.println("发送失败");
			e.printStackTrace();
		}*/
	}
	
	/**
	 * @Description:写一个方法，让它显示消息
	 * @auther: wutp 2016年12月8日
	 * @param m
	 * @return void
	 */
	public void showMessage(MessageModel message) {
		String info = message.getSender() + ":" + message.getInfo() + "\r\n";
		//this.jta.append(info);
		try {
			document.insertString(document.getLength(), info, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:处理发送信息事件
	 * @auther: wutp 2016年12月4日
	 * @return void
	 */
	public void actionSend() {		
		MessageBean m = new MessageBean();
		m.setType(MessageType.SINGLETON_CHAT);
		MessageModel message = new MessageModel();
		message.setSender(FriendListJFrame.user.getUserModel().toString());
		message.setRecerver(model.getGid());
		message.setInfo(textField.getText().trim());
		message.setTime(new java.util.Date().toString());
		m.setObject(message);
		sendMessage(m);
		displayMessage.replaceSelection(new java.util.Date().toString() + " 我:\r\n" + textField.getText().trim() + "\r\n");
		textField.setText(null);
		textField.requestFocus();
	}
		
	/**
	 * @Description:统一调用发送消息clientInputThread
	 * @auther: wutp 2016年10月15日
	 * @param clientBean
	 * @return void
	 */
	private void sendMessage(MessageBean clientBean) {
		this.MainJFrame.server.sendMessage(clientBean);
	}

	/*
	 * 按钮专用的监听器
	 */
	private ActionListener actionAdapter = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == closeButton || e.getSource() == closeButton2) {
				dispose();
				FriendListJFrame.removeChatJFrame(model.getGid());
			} else if (e.getSource() == minimizationButton) {
				setExtendedState(JFrame.ICONIFIED);
			}else if(e.getSource() == sendButton){
				actionSend();
			}
		}
	};

}
