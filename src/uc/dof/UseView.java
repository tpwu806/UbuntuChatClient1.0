package uc.dof;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import uc.common.dto.FriendItemModel;
import uc.common.dto.GroupModel;
import uc.common.dto.UserInformation;
import uc.common.parcel.NewsParcel;
import uc.common.parcel.ParcelModel;
import uc.pub.assembly.*;
import uc.pub.tool.ChangeImage;
import uc.pub.tool.Colors;
import uc.pub.tool.Fonts;
import uc.pub.tool.VerlicelColumn;

/**
 * @Description: 主窗口 好友列表
 * @author wutp 2016年10月15日
 * @version 1.0
 * @author wutp 2016年10月30日
 * @version 2.0
 */
public class UseView extends FillitFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static UserInformation user;
	// 头部
	private JPanel forTop;

	// 窗体,
	//private FillitFrame frame;
	// 包含界面背景、包含其他组件
	private ImageIcon bg;
	private MainPanel mainPanel;

	// 关闭与最小化按钮
	private JPanel forSystemButton;
	private JButton closeButton;
	private JButton minimizationButton;
	// 包含软件名
	private JLabel title;

	// 头像
	private HeadPanel headPanel;
	// 状态选择按钮
	private SenioButton stateButton;
	// 显示状态按钮上的图标
	private JLabel forStateIcon;
	// 昵称
	private JLabel nickName;
	// 等级
	private SenioButton lv;
	// 个性签名输入框
	private JTextField signatureField;
	private boolean defaultSignature = true;
	// 查看空间按钮
	private SenioButton seeZone;
	// 邮箱按钮
	private SenioButton mailBox;
	// 消息盒
	private SenioButton messageBox;

	// 搜索栏
	private JLabel searchBar;
	private JTextField searchField;
	private JButton auxiliaryButton;
	private boolean swiching = true;

	// 中部
	private JPanel forCenter;

	// 选项卡组
	private JPanel forTab;
	private Tab friendsTab;
	private Tab groupTab;
	private Tab conversationTab;

	// 弄好节点，之后做GroupLayout
	private final static ImageIcon arrow = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_collapseTexture.png");
	private final static ImageIcon arrowHover = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_collapseTextureHighlight.png");
	private final static ImageIcon arrowSelected = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_expandTexture.png");
	private final static ImageIcon arrowSelectedHover = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_expandTextureHighlight.png");

	// 管理聊天面板
	private static HashMap<String, ChatJFrame> chatJFrames = new HashMap<String, ChatJFrame>();

	// 具体面板
	private CardLayout cardLayout;
	private JPanel forCard;
	private JScrollPane scrollPane;
	// 好友面板
	private JPanel friendPanel;

	// 底部
	private JPanel forSouth;
	// soso
	private SenioButton soso;
	// 电脑管家
	private SenioButton housekeeper;
	// 应用管理
	private SenioButton appManagement;

	// 主菜单
	private SenioButton mainMenu;
	// 系统设置
	private SenioButton systemSettings;
	// 消息管理器
	private SenioButton messageManager;
	// 应用宝
	private SenioButton application;

	// 与服务器保持连接读取服务器发来的信息
	private Socket socket;
	private ObjectInputStream objectInputStream;
		
	public UseView(UserInformation user, Socket socket){
		super(280, 678, 7, 7);
		UseView.user = user;
		this.socket = socket;
		createFrame();
		//new Receive().start();
	}
	
	
	//构建
	private void createFrame(){
		//窗体
		//frame = new FillitFrame(280, 678, 7, 7);
		
		//中部面板
		bg = new ImageIcon("Image\\use\\未标题-2.jpg");
		mainPanel = new MainPanel(bg, 7, 7);
		mainPanel.setLayout(new BorderLayout());
		//frame.add(mainPanel);
		this.add(mainPanel);
		
		//构建头部
		createTop();
		//构建中部
		createCenter();
		//构建底部
		createSouth();
		
		//frame.setVisible(true);
		this.setVisible(true);
	}
	
	

	//构建头部
	private void createTop(){
		forTop = new JPanel(new BorderLayout());
		forTop.setOpaque(false);
		mainPanel.add(forTop, "North");
		
		//标题区:软件名、系统按钮
		createTitle();
		//头像区块
		createHeadDistrict();
		//搜索栏
		createSearchBar();
	}
	
	
	//构建
	private void createTitle(){		
		JPanel pane2 = new JPanel(new BorderLayout());
		pane2.setPreferredSize(new Dimension(pane2.getPreferredSize().width, 30));
		pane2.setOpaque(false);
		forTop.add(pane2, "North");
		//软件名
		JPanel pane3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 0));
		pane3.setOpaque(false);
		pane2.add(pane3);
		title = new JLabel("warau");
		title.setFont(new Font("微软雅黑", 0, 18));
		pane3.add(title);
		
		//系统按钮
		forSystemButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		forSystemButton.setOpaque(false);
		assembly();
		pane2.add(forSystemButton, "East");
	}
	/*
	 * 生成界面上按键
	 */
	private void assembly(){
		//顺序：构建、设置经过/点击图像、设置提示、添加操作监听、加入容器
		
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


	//构建
	private void createHeadDistrict(){
		//头像区域
		JPanel pane4 = new JPanel(new BorderLayout());
		pane4.setOpaque(false);
		pane4.setPreferredSize(new Dimension(pane4.getPreferredSize().width, 66));
		forTop.add(pane4);
		//头像
		JPanel pane11 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		pane11.setOpaque(false);
		pane4.add(pane11, "West");
		headPanel = new HeadPanel();
		headPanel.setHeadBorder(new ImageIcon("Image\\use\\Padding4Normal.png"));
		headPanel.setPreferredSize(new Dimension(66, 66));
		headPanel.setHorizontalAlignment(SwingConstants.CENTER);
		headPanel.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(user.getUser().getHeadURL()),
				62,
				62,
	    		7));
		pane11.add(headPanel, "West");
		
		//用于头像之后组件的布局
		JPanel pane5 = new JPanel(new BorderLayout());
		pane5.setOpaque(false);
		pane4.add(pane5, "Center");
		//状态按钮
		JPanel pane6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pane6.setOpaque(false);
		pane5.add(pane6, "North");		
	
		stateButton = new SenioButton();
		stateButton.setPreferredSize(new Dimension(36, 18));
		stateButton.setRoundSize(4);
		stateButton.setLayout(new GridLayout(1, 2));
		forStateIcon = new JLabel(new ImageIcon("Image\\Status\\FLAG\\Big\\invisible.png"));
		stateButton.add(forStateIcon);
		JLabel arrow = new JLabel(new ImageIcon("Image\\use\\arrow_down.png"));
		stateButton.add(arrow);
		pane6.add(stateButton);
		//昵称
		nickName = new JLabel(user.getNickName());
		nickName.setFont(new Font("微软雅黑", 0, 15));
		nickName.setBounds(20, 34, 100, 15);
		pane6.add(nickName);
		//等级
		lv = new SenioButton(new ImageIcon("Image\\use\\LV\\9.png"));
		pane6.add(lv);
		
		//个性签名输入框
		JPanel pane12 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 1));
		pane12.setOpaque(false);
		pane5.add(pane12, "Center");
		String content = user.getSignature();
		if(content == null){
			signatureField = new JTextField("编辑个性签名");
		}else{
			signatureField = new JTextField(content);
		}
		signatureField.setOpaque(false);
		signatureField.setBorder(null);
		signatureField.setPreferredSize(new Dimension(130, 22));
		pane12.add(signatureField, "Center");
		//经过边框的实现
		signatureField.addMouseListener(new MouseAdapter(){
			private final LineBorder border = new LineBorder(Colors.greyColor);
			public void mouseEntered(MouseEvent e){
				signatureField.setBorder(border);
			}
			public void mouseExited(MouseEvent e){
				signatureField.setBorder(null);
			}
		});
		//默认显示的实现
		signatureField.addFocusListener(new FocusListener(){			
			public void focusGained(FocusEvent e) {
				if(defaultSignature){
					signatureField.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if(signatureField.getText().isEmpty()){
					signatureField.setText("编辑个性签名");
				}
			}
		});
		//
		JPanel pane7 = new JPanel(new BorderLayout());
		pane7.setOpaque(false);
		pane7.setPreferredSize(new Dimension(pane7.getPreferredSize().width, 22));
		pane5.add(pane7, "South");
		JPanel pane9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
		pane9.setOpaque(false);
		pane7.add(pane9, "Center");
		//空间按钮
		seeZone = new SenioButton(new ImageIcon("Image\\AppPluginIcon\\qzoneicon.png"));
		seeZone.setPreferredSize(new Dimension(seeZone.getPreferredSize().width+4, 20));
		pane9.add(seeZone);
		//邮箱
		mailBox = new SenioButton("27", 
				new ImageIcon("Image\\AppPluginIcon\\ContactTipsVASFlagExt_Mail.png"),
				SwingConstants.CENTER);
		mailBox.setIconTextGap(0);
		mailBox.setFont(Fonts.MicrosoftAccor12);
		mailBox.setToolTipText("打开邮箱"+" 收件箱"+" 漂流瓶");
		mailBox.setPreferredSize(new Dimension(mailBox.getPreferredSize().width+4, 20));
		pane9.add(mailBox);
		
		//消息盒
		JPanel pane10 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 1));
		pane10.setOpaque(false);
		pane7.add(pane10, "East");
		messageBox = new SenioButton(new ImageIcon("Image\\AppPluginIcon\\tips_16.png"));
		messageBox.setPreferredSize(new Dimension(messageBox.getPreferredSize().width+4, 20));
		messageBox.setToolTipText("打开消息盒");
		pane10.add(messageBox);
	}


	//构建
	private void createSearchBar(){
		JPanel pane13 = new JPanel(new BorderLayout());
		pane13.setOpaque(false);
		pane13.setBorder(BorderFactory.createMatteBorder(0,
				1,
				0,
				1,
				Color.black));
		forTop.add(pane13, "South");
		
		JPanel pane14 = new JPanel();
		pane14.setOpaque(false);
		pane13.add(pane14);
		searchBar = new JLabel(" 搜索：联系人、讨论组、群、企业"){
			/**
			 * 
			 */
			private static final long serialVersionUID = -9012114410749053341L;
			public void paintComponent(Graphics g){
				g.setColor(new Color(255, 255, 255, 110));
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		searchBar.setBorder(BorderFactory.createMatteBorder(1,
				0,
				1,
				0,
				new Color(204, 217, 203)));
		searchBar.setOpaque(false);
		searchBar.setLayout(new BorderLayout());
		searchBar.setPreferredSize(new Dimension(searchBar.getPreferredSize().width, 30));
		searchBar.setBackground(Color.white);
		searchBar.setForeground(Colors.greyColor);
		pane13.add(searchBar, "South");
		//输入框
		searchField = new JTextField();
		searchField.setOpaque(false);
		searchField.setBorder(BorderFactory.createEmptyBorder(0,
				4,
				0,
				0));
		searchBar.add(searchField);
		searchField.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(swiching){
					swiching = false;
					searchBar.setText("");
					auxiliaryButton.setEnabled(true);
					searchBar.setOpaque(true);
				}
			}
		});
		searchField.getDocument().addDocumentListener(new DocumentListener(){
			public void insertUpdate(DocumentEvent e) {
				if(swiching){
					swiching = false;
					searchBar.setText("");
					auxiliaryButton.setEnabled(true);
					searchBar.setOpaque(true);
				}
			}
			public void removeUpdate(DocumentEvent e) {
			}
			public void changedUpdate(DocumentEvent e) {
			}
		});
		//关闭按钮
		auxiliaryButton = new JButton(new ImageIcon("Image\\use\\search.png"));
		auxiliaryButton.setDisabledIcon(new ImageIcon("Image\\use\\search_Disable.png"));
		auxiliaryButton.setRolloverIcon(new ImageIcon("Image\\use\\search_Hover.png"));
		auxiliaryButton.setEnabled(false);
		auxiliaryButton.setContentAreaFilled(false);
		auxiliaryButton.setFocusPainted(false);
		searchBar.add(auxiliaryButton, "East");
		//添加监听
		auxiliaryButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				auxiliaryButton.setEnabled(false);
				swiching = true;
				searchField.setText("");
				searchBar.setText(" 搜索：联系人、讨论组、群、企业");
				searchBar.setOpaque(false);
			}			
		});
	}


	//构建
	private void createCenter(){
		forCenter = new JPanel(new BorderLayout());
		forCenter.setOpaque(false);
		mainPanel.add(forCenter);
		//选项卡组
		crateTabs();
		//好友面板
		createFriendPanel();
	}


	//构建
	private void crateTabs(){
		forTab = new JPanel(new GridLayout(1, 3, 1, 0));
		forTab.setOpaque(false);
		forTab.setPreferredSize(new Dimension(forTab.getPreferredSize().width, 34));
		forCenter.add(forTab, "North");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		//联系人
		friendsTab = new Tab(new ImageIcon("Image\\use\\icon_contacts_normal.png"), true);
		friendsTab.setSelectedIcon(new ImageIcon("Image\\use\\icon_contacts_selected.png"));
		friendsTab.setToolTipText("联系人");
		forTab.add(friendsTab);
		buttonGroup.add(friendsTab);
		//群/讨论组
		groupTab = new Tab(new ImageIcon("Image\\use\\icon_group_normal.png"));
		groupTab.setSelectedIcon(new ImageIcon("Image\\use\\icon_group_selected.png"));
		groupTab.setToolTipText("群/讨论组");
		forTab.add(groupTab);
		buttonGroup.add(groupTab);
		//会话
		conversationTab = new Tab(new ImageIcon("Image\\use\\icon_last_normal.png"));
		conversationTab.setSelectedIcon(new ImageIcon("Image\\use\\icon_last_selected.png"));
		conversationTab.setToolTipText("会话");
		forTab.add(conversationTab);
		buttonGroup.add(conversationTab);
	}


	//构建
	private void createFriendPanel(){
		cardLayout = new CardLayout();
		forCard = new JPanel(cardLayout);
		forCard.setOpaque(false);
		scrollPane = new JScrollPane(forCard,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0,
				1,
				0,
				1,
				Color.black));
		forCenter.add(scrollPane, "Center");
		
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
		forCard.add(friendPanel);
		
		ArrayList<GroupModel> groups = user.getFriendList();
		for(GroupModel group : groups){
			GroupContainer gropContainer = createGroupNode(group.getGroupName());
			int length = group.size();
			for(int i=0; i<length; i++){
				FriendItemModel friendModel = group.get(i);	
				FriendItem friend = new FriendItem(friendModel);
				gropContainer.addMember(friend);
				friend.addMouseListener(friendItemMouseAdapter);
			}
			friendPanel.add(gropContainer);
		}
	}


	//添加节点
	private GroupContainer createGroupNode(String name){
		final JToggleButton node = new JToggleButton(name,
				arrow){
			/**
			 * 
			 */
			private static final long serialVersionUID = 392923895832778450L;
			private ButtonModel buttonModel = getModel();
			private final Color startColor = new Color(255,255,255,10);
			private final Color endColor = new Color(49,143,197,35);
			public void paintComponent(Graphics g){
				if(buttonModel.isRollover() && !isFocusOwner()){
					  int width = getWidth();
					  int height = getHeight();
					Graphics2D g2d = (Graphics2D)g;
				    g2d.setPaint(new GradientPaint(0, 0, startColor, 0, height, endColor));
					g2d.fillRect(0, 0, width, height);
				}
				super.paintComponent(g);
			}
		};
		node.setRolloverIcon(arrowHover);
		
		node.setContentAreaFilled(false);
		node.setOpaque(false);
		node.setFocusPainted(false);
		node.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));	
		node.setPreferredSize(new Dimension(0, 25));
		node.setHorizontalAlignment(SwingConstants.LEFT);
		node.addActionListener(nodeActionListener);
		node.addKeyListener(nodeKeyAdapter);
		node.addFocusListener(nodeFocusListener);
		
		return new GroupContainer(node);
	}


	//构建
	private void createSouth(){
		forSouth = new JPanel(new GridLayout(2, 1));
		forSouth.setBackground(new Color(255,255,255,240));
		forSouth.setPreferredSize(new Dimension(forSouth.getPreferredSize().width, 60));
		forSouth.setBorder(BorderFactory.createMatteBorder(1,
				0,
				0,
				0,
				new Color(0,0,0,50)));
		mainPanel.add(forSouth, "South");
		
		//第一层
		JPanel pane15 = new JPanel(new BorderLayout());
		pane15.setOpaque(false);
		forSouth.add(pane15);
		JPanel pane16 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane16.setOpaque(false);
		pane15.add(pane16);
		
		soso = new SenioButton(new ImageIcon("Image\\use\\SoSo.png"));
		soso.setPreferredSize(new Dimension(22, 22));
		pane16.add(soso);
		
		housekeeper = new SenioButton(new ImageIcon("Image\\use\\safe.png"));
		housekeeper.setPreferredSize(new Dimension(22, 22));
		pane16.add(housekeeper);
		//布局第一层最右边
		JPanel pane17 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane17.setOpaque(false);
		pane15.add(pane17, "East");
		
		appManagement = new SenioButton(new ImageIcon("Image\\use\\error.png"));
		appManagement.setPreferredSize(new Dimension(22, 22));
		pane17.add(appManagement);
		
		//第二层
		JPanel pane18 = new JPanel(new BorderLayout());
		pane18.setOpaque(false);
		forSouth.add(pane18);
		JPanel pane19 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane19.setOpaque(false);
		pane18.add(pane19);
		
		mainMenu = new SenioButton(new ImageIcon("Image\\use\\menu_btn_highlight.png"));
		mainMenu.setPreferredSize(new Dimension(22, 22));
		mainMenu.addMouseListener(new MouseAdapter(){
			private final ImageIcon hoverIcon = new ImageIcon("Image\\use\\menu_btn_normal.png");
			private final ImageIcon icon = (ImageIcon) mainMenu.getIcon();
			public void mouseEntered(MouseEvent e){
				mainMenu.setIcon(hoverIcon);
			}
			public void mouseExited(MouseEvent e){
				mainMenu.setIcon(icon);
			}
		});
		pane19.add(mainMenu);
		
		systemSettings = new SenioButton(new ImageIcon("Image\\use\\Tools.png"));
		systemSettings.setPreferredSize(new Dimension(22, 22));
		pane19.add(systemSettings);
		
		messageManager = new SenioButton(new ImageIcon("Image\\use\\message.png"));
		messageManager.setPreferredSize(new Dimension(22, 22));
		pane19.add(messageManager); 
		//布局第二层最右边
		JPanel pane20 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane20.setOpaque(false);
		pane18.add(pane20, "East");
		
		application = new SenioButton("应用宝", 
				new ImageIcon("Image\\use\\TencentNet.png"),
				SwingConstants.LEFT);
		application.setPreferredSize(new Dimension(58, 22));
		application.setIconTextGap(0);
		pane20.add(application);  
	}


	private final static ImageIcon[] arrows = new ImageIcon[]{
		    arrowHover,
			new ImageIcon("Image\\use\\15.png"),
			new ImageIcon("Image\\use\\30.png"),
			 new ImageIcon("Image\\use\\45.png"),
			 new ImageIcon("Image\\use\\60.png"),
			 new ImageIcon("Image\\use\\75.png"),
			 arrowSelectedHover
	};

	//提供删除窗口的静态方法
	public static void remove(String key){
		chatJFrames.remove(key);
	}


	//用于设置背景、
	public void setBg(){
		//**** 只需要更改 Bg的值就可以达成更改背景的效果
	}


	//关闭资源
	public void close(){
		try {
			if(objectInputStream!=null){
				objectInputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*
	 * 按钮专用的监听器
	 */
	private ActionListener actionAdapter = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == closeButton){				
				System.exit(0);				
			}else if(e.getSource() == minimizationButton){				
				System.out.println("最小化");
				//frame.setExtendedState(JFrame.ICONIFIED);	
				setExtendedState(JFrame.ICONIFIED);	
			}
		}

		private void setExtendedState(int iconified) {
			this.setExtendedState(JFrame.ICONIFIED);				
		}
	};
	
	
	//节点的点击监听器
	private ActionListener nodeActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JToggleButton pressButton = (JToggleButton) e.getSource();
			pressButton.setRolloverIcon(null);
			if(pressButton.getModel().isSelected()){
				openEffect(pressButton);
			}else{
				closeEffect(pressButton);
			}
		}
	};
	//节点ENTER键的监听器
	private KeyAdapter nodeKeyAdapter = new KeyAdapter(){
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				JToggleButton pressButton = (JToggleButton) e.getSource();
				pressButton.setRolloverIcon(null);
				if(pressButton.getModel().isSelected()){
					closeEffect(pressButton);
					pressButton.setSelected(false);
				}else{
					openEffect(pressButton);
					pressButton.setSelected(true);
				}
			}
		}
	};
	//节点的焦点监听器
	private FocusListener nodeFocusListener = new FocusListener(){
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e){
			final JToggleButton pressButton = (JToggleButton) e.getSource();
			if(pressButton.getModel().isSelected()){
				Thread t = new Thread(){
					public void run(){
						pressButton.setSelectedIcon(arrowSelected);
						pressButton.setRolloverSelectedIcon(arrowSelectedHover);
					}
				};
				t.start();
			}else{
				Thread t = new Thread(){
					public void run(){
						pressButton.setIcon(arrow);
						pressButton.setRolloverIcon(arrowHover);
					}
				};
				t.start();
			}				
		}
	};

	//双击创建聊天面板
	private MouseAdapter friendItemMouseAdapter = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				final FriendItem selectedItem = (FriendItem) e.getSource();				
				/*Thread t = new Thread(){
					public void run(){
						ChatView chetPanel = chatViews.get(selectedItem.getNo());
						//判断是否已经有该面板
						if(chetPanel==null){
							chetPanel = new ChatView(selectedItem.getModel(), socket);
							chatViews.put(selectedItem.getNo(), chetPanel);
						}else{
							chetPanel.aa();
						}
					}
				};
				t.start();*/
			}
		}		 
	};

	//播放展开效果
	private void openEffect(final JToggleButton pressButton){
		GroupContainer gropContainer = (GroupContainer) pressButton.getParent();
		gropContainer.expand();
		Thread t = new Thread(){
			public void run(){
				for(ImageIcon img: arrows){
					pressButton.setIcon(img);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();	
	}
	//关闭效果
	private void closeEffect(final JToggleButton pressButton){
		GroupContainer gropContainer = (GroupContainer) pressButton.getParent();
		gropContainer.collapse();
		Thread t = new Thread(){
			public void run(){
				ImageIcon img;
				int length = arrows.length;
				for(int i=length-1; i>=0;i--){
					img = arrows[i];
					pressButton.setIcon(img);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();	
	}
	/**
	 * @Description: 内部类线程
	 * @author wutp 2016年10月31日
	 * @version 1.0
	 */
	/*class Receive extends Thread{
		public void run(){
			ParcelModel parcel;
			while(true){
				try {
					objectInputStream = new ObjectInputStream(socket.getInputStream());
					try {
						parcel = (ParcelModel)objectInputStream.readObject();
						switch(parcel.getMessage()){
						case NEWS:
							NewsParcel newsParcel = (NewsParcel) parcel;
							String sender = newsParcel.getSender();
							ChatJFrame chatJFrame = chatJFrames.get(sender);
							//如果没有该面板则创建一个
							if(chatJFrame == null){
								ArrayList<GroupModel> groups = user.getFriendList();
								for(GroupModel group : groups){
									int length = group.size();
									for(int i=0; i<length; i++){
										FriendItemModel friendModel = group.get(i);	
										if(friendModel.getNO().equals(sender)){
											chatJFrame = new ChatJFrame(friendModel, socket);
											chatJFrames.put(friendModel.getNO(), chatJFrame);										
										}
									}
								}
							}
							//向其中插入内容
							chatJFrame.insertString(newsParcel.getSenderInformation(), null);
							chatJFrame.insertString((String)newsParcel.getContent(), null);
							break;
						default:
							break;
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}
