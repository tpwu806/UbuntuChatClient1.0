package uc.dof;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

import com.sun.awt.AWTUtilities;

import uc.common.GroupModel;
import uc.common.MessageBean;
import uc.common.MessageType;
import uc.common.StateEnum;
import uc.common.User;
import uc.common.UserInformation;
import uc.common.domain.UserInfo;
import uc.dal.ClinetServer;
import uc.pub.assembly.FillitFrame;
import uc.pub.assembly.HeadPanel;
import uc.pub.assembly.MainPanel;
import uc.pub.assembly.StateButton;
import uc.pub.assembly.StateItem;
import uc.pub.tool.ChangeImage;
import uc.pub.tool.Colors;
import uc.pub.tool.Fonts;
import uc.pub.tool.Images;

/**
 * @Description: 读取账号纪录功能没写 未将自定义按钮改成普通按钮的版本
 * 
 * @author wutp 2016年10月15日
 * @version 1.0
 * @author wutp 2016年10月30日
 * @version 2.0
 */
public class LoginJFrame extends FillitFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 窗体
	//private FillitFrame frame = null;
	// 包含背景
	private ImageIcon bg;
	private MainPanel mainPanel;
	// 包含界面底色背景、包含其他组件
	private ImageIcon texture;
	private JLabel centerPanel = null;

	// 编辑器
	private UserFiledEdit edit;
	// 用户组
	private Vector<User> elements;
	// 模型
	private DefaultComboBoxModel<User> comboBoxModel;
	// 下拉框
	private JComboBox<User> filluser = null;
	// 选择更改监听器
	private UserChangeAdapter userChangeAdapter = null;
	// 密码输入框
	private JPasswordField fillcipher = null;
	private boolean deleteAll = false;
	private boolean isModify = true;
	// 密码输入框焦点监听器
	private FillcipherFocusAdapter fillcipherFocusAdapter;

	// 头像
	private HeadPanel headPanel = null;
	// 状态选择
	private JPopupMenu stateButton_popup = null;
	private StateItem onLine = null;
	private StateItem qMe = null;
	private StateItem leave = null;
	private StateItem beBusy = null;
	private StateItem dontDisturb = null;
	private StateItem invisible = null;
	private StateButton stateButton = null;
	// 监听器
	private ItemActionAdapter itemActionAdapter = null;

	// 关闭与最小化按钮/设置
	private JButton closeButton = null;
	private JButton minimizationButton = null;
	private JButton settingsButton;
	// 复选框
	private JCheckBox rememberPassword = null;
	private JCheckBox automaticLogin = null;
	// 注册账号、找回密码按钮
	private JButton registration = null;
	private JButton find = null;
	// 登录按钮
	private JButton loginButton = null;
	
	// 提示栏
	JLabel tipLabel;
	JButton stretchButton;

	private ClinetServer server;
	
	public LoginJFrame() {
		super(380, 292);
		server = new ClinetServer();
		initUI();
	}

	// 生成界面
	private void initUI() {

		// frame = new FillitFrame(380, 292);
		// frame.setLocationRelativeTo(null);
		this.setLocationRelativeTo(null);

		bg = new ImageIcon("Image\\client\\login's\\noon.jpg");
		mainPanel = new MainPanel(bg);
		mainPanel.setLayout(new BorderLayout());
		// frame.add(mainPanel);
		this.add(mainPanel);
		// 中部面板
		texture = new ImageIcon("Image\\client\\login's\\texture.png");
		centerPanel = new JLabel(texture);
		centerPanel.setOpaque(false);
		centerPanel.setLayout(null);
		mainPanel.add(centerPanel);
		// 头像区域
		createHead();
		// 选项/按钮
		assembly();
		// 输入框
		createInputBox();

		// frame.setVisible(true);
		this.setVisible(true);
		// 生成提示栏
		createTip();
	}

	/*
	 * 构建头像区域 * 我认为界面的具体实现，不能让外部来操作 所以就设置为protected
	 */
	private void createHead() {
		// 头像
		headPanel = new HeadPanel();
		headPanel.setBounds(24, 146, 82, 82);
		centerPanel.add(headPanel);
	
		// 状态选择窗口
		stateButton_popup = new JPopupMenu() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9012594627907460339L;
	
			public void paintComponent(Graphics g) {
				g.drawImage(new ImageIcon("Image\\client\\login's\\statePopup.png").getImage(), 0, 0, 108, 145, null);
			}
		};
	
		// 实例化监听器
		itemActionAdapter = new ItemActionAdapter();
	
		onLine = new StateItem(StateEnum.ONLINE);
		onLine.addActionListener(itemActionAdapter);
		stateButton_popup.add(onLine);
	
		qMe = new StateItem(StateEnum.QME);
		qMe.addActionListener(itemActionAdapter);
		stateButton_popup.add(qMe);
	
		stateButton_popup.addSeparator();
	
		leave = new StateItem(StateEnum.LEAVE);
		leave.addActionListener(itemActionAdapter);
		stateButton_popup.add(leave);
	
		beBusy = new StateItem(StateEnum.BEBUSY);
		beBusy.addActionListener(itemActionAdapter);
		stateButton_popup.add(beBusy);
	
		dontDisturb = new StateItem(StateEnum.DONTDISTURB);
		dontDisturb.addActionListener(itemActionAdapter);
		stateButton_popup.add(dontDisturb);
	
		stateButton_popup.addSeparator();
	
		invisible = new StateItem(StateEnum.INVISIBLE);
		invisible.addActionListener(itemActionAdapter);
		stateButton_popup.add(invisible);
	
		// 状态选择按钮
		stateButton = new StateButton();
		stateButton.setIcon(onLine.getIcon());
		stateButton.setState(onLine.getState());
		stateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateButton_popup.show(centerPanel, 89, 232);
				stateButton_popup.updateUI();
			}
		});
		stateButton.setBounds(64, 64, 17, 17);
		headPanel.add(stateButton);
	}

	/*
	 * 生成界面上按键
	 */
	private void assembly() {
	
		// 关闭 //最小化 : 构建、设置经过图像、设置不获取焦点、设置透明、添加操作监听、设置位置大小、加入容器
		closeButton = new JButton(new ImageIcon("Image\\SystemButton\\clouse.png"));
		closeButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\clouse_hover.png"));
		closeButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\clouse_press.png"));
		closeButton.setToolTipText("关闭");
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setFocusable(false);
		closeButton.setBounds(340, 0, 40, 20);
		closeButton.addActionListener(actionAdapter);
		centerPanel.add(closeButton);
	
		minimizationButton = new JButton(new ImageIcon("Image\\SystemButton\\minimize.png"));
		minimizationButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\minimize_hover.png"));
		minimizationButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\minimize_press.png"));
		minimizationButton.setToolTipText("最小化");
		minimizationButton.setContentAreaFilled(false);
		minimizationButton.setFocusPainted(false);
		minimizationButton.setFocusable(false);
		minimizationButton.setBounds(311, 0, 30, 20);
		minimizationButton.addActionListener(actionAdapter);
		centerPanel.add(minimizationButton);
	
		settingsButton = new JButton(new ImageIcon("Image\\SystemButton\\btn_set_normal.png"));
		settingsButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\btn_set_hover.png"));
		settingsButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\btn_set_press.png"));
		settingsButton.setToolTipText("设置");
		settingsButton.setContentAreaFilled(false);
		settingsButton.setFocusPainted(false);
		settingsButton.setFocusable(false);
		settingsButton.setBounds(284, 0, 28, 20);
		settingsButton.addActionListener(actionAdapter);
		centerPanel.add(settingsButton);
	
		// 复选框:格式为：构建、设置按下，悬停，选中，选中后鼠标悬停 图标、
		// 设置字体、设置字体颜色、设置透明、设置不绘制焦点、设置大小位置、加入容器
		rememberPassword = new JCheckBox("记住密码", new ImageIcon("Image\\client\\login's\\checkbox_normal.png"));
		rememberPassword.setPressedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_hover.png"));
		rememberPassword.setRolloverIcon(new ImageIcon("Image\\client\\login's\\checkbox_press.png"));
		rememberPassword.setSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_normal.png"));
		rememberPassword.setRolloverSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_press.png"));
		rememberPassword.setFont(Fonts.MicrosoftAccor12);
		rememberPassword.setForeground(new Color(0, 27, 47));
		rememberPassword.setOpaque(false);
		rememberPassword.setFocusPainted(false);
		rememberPassword.setBounds(113, 216, 75, 15);
		centerPanel.add(rememberPassword);
	
		automaticLogin = new JCheckBox("自动登录", new ImageIcon("Image\\client\\login's\\checkbox_normal.png"));
		automaticLogin.setPressedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_hover.png"));
		automaticLogin.setRolloverIcon(new ImageIcon("Image\\client\\login's\\checkbox_press.png"));
		automaticLogin.setSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_normal.png"));
		automaticLogin.setRolloverSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_press.png"));
		automaticLogin.setFont(Fonts.MicrosoftAccor12);
		automaticLogin.setForeground(new Color(0, 27, 47));
		automaticLogin.setOpaque(false);
		automaticLogin.setFocusPainted(false);
		automaticLogin.setBounds(193, 216, 75, 15);
		centerPanel.add(automaticLogin);
	
		// 注册账号 //找回密码 : 构建、设置悬停图标、设置不获取焦点、设置透明、添加操作监听、设置大小位置、加入容器
		registration = new JButton(new ImageIcon("Image\\client\\login's\\registration.png"));
		registration.setPressedIcon(new ImageIcon("Image\\client\\login's\\registration_hover.png"));
		registration.setRolloverIcon(new ImageIcon("Image\\client\\login's\\registration_hover.png"));
		registration.setContentAreaFilled(false);
		registration.setFocusPainted(false);
		registration.setBounds(314, 154, 51, 16);
		registration.addActionListener(actionAdapter);
		centerPanel.add(registration);
	
		find = new JButton(new ImageIcon("Image\\client\\login's\\find.png"));
		find.setPressedIcon(new ImageIcon("Image\\client\\login's\\find_hover.png"));
		find.setRolloverIcon(new ImageIcon("Image\\client\\login's\\find_hover.png"));
		find.setContentAreaFilled(false);
		find.setFocusPainted(false);
		find.setBounds(314, 188, 51, 16);
		find.addActionListener(actionAdapter);
		centerPanel.add(find);
		// 登录按钮 ：构建、按下图标、悬停图标、不获取焦点、透明、添加操作监听、位置大小、加入容器
		loginButton = new JButton(new ImageIcon("Image\\client\\login's\\loginButton_1.png"));
		loginButton.setPressedIcon(new ImageIcon("Image\\client\\login's\\loginButton_3.png"));
		loginButton.setRolloverIcon(new ImageIcon("Image\\client\\login's\\loginButton_2.png"));
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.setBounds(115, 250, 160, 36);
		loginButton.addActionListener(actionAdapter);
		centerPanel.add(loginButton);
	}

	/*
	 * 构建输入框,拥有显示默认登录账号功能
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createInputBox() {
		// 密码输入框
		fillcipher = new JPasswordField(16) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6127751628415613865L;
	
			public void paintBorder(Graphics g) {
				super.paintBorder(g);
				g.drawImage(new ImageIcon("Image\\client\\login's\\inputbox.png").getImage(), -2, -2, 192, 30, null);
			}
		};
		fillcipher.setEchoChar('●');
		fillcipher.setFont(Fonts.MicrosoftAccor12);
		fillcipher.setBounds(115, 181, 188, 26);
		centerPanel.add(fillcipher);
		// 密码输入框的焦点监听器
		fillcipherFocusAdapter = new FillcipherFocusAdapter();
		fillcipher.addFocusListener(fillcipherFocusAdapter);
		// 监听密码输入框的ENTER键
		fillcipher.addKeyListener(new KeyAdapter() {
			// 只能使用这个监听，JTextField的ENTER键，其他的都是在弹出部分消失之后才获取事件
			// 不能够达成弹出部分显示的时候不登录的目的
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					query();
				}
			}
		});
		// 添加值修改监听
		fillcipher.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (deleteAll) {
					String content = new String(fillcipher.getPassword()).replace("123456789", "");
					modifyCipherState("");
					modifyCipherState(content);
				}
			}
	
			public void removeUpdate(DocumentEvent e) {
				if (deleteAll) {
					modifyCipherState("");
				}
			}
	
			public void changedUpdate(DocumentEvent e) {
			}
	
			public void modifyCipherState(final String s) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						fillcipher.setText(s);
						deleteAll = false;
						isModify = true;
					}
				});
			}
		});
	
		// 账号输入框的监听器
		userChangeAdapter = new UserChangeAdapter();
		// 账号输入框
		filluser = new JComboBox() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6127751628415613865L;
	
			public void paintBorder(Graphics g) {
				super.paintBorder(g);
				g.drawImage(new ImageIcon("Image\\client\\login's\\inputbox.png").getImage(), -2, -2, 192, 30, null);
			}
		};
		filluser.setUI(new BasicComboBoxUI() {
			protected JButton createArrowButton() {
				JButton arrowButton = new JButton(new ImageIcon("Image\\client\\login's\\arrowButton.png"));
				arrowButton.setFocusable(false);
				arrowButton.setFocusPainted(false);
				arrowButton.setRolloverIcon(new ImageIcon("Image\\client\\login's\\arrowButton_hover.png"));
				return arrowButton;
			}
		});
	
		filluser.setFont(Fonts.blackBody15);
		filluser.setEditable(true);
		filluser.setMaximumRowCount(2);
		filluser.setRenderer(new IconRenderer());
		filluser.setBounds(115, 147, 188, 26);
		centerPanel.add(filluser);
	
		// 数据模型
		elements = new Vector<User>();
		readUser();
		comboBoxModel = new DefaultComboBoxModel(elements);
	
		filluser.setModel(comboBoxModel);
	
		// JComboBox的编辑器
		edit = new UserFiledEdit();
		edit.addMouseWheelListener(userChangeAdapter);
		edit.addKeyListener(new KeyAdapter() {
			// 只能使用这个监听，JTextField的ENTER键，其他的都是在弹出部分消失之后才获取事件
			// 不能够达成弹出部分显示的时候不登录的目的
			public void keyPressed(KeyEvent e) {
				if (!filluser.isPopupVisible() && e.getKeyCode() == KeyEvent.VK_ENTER) {
					query();
				}
			}
		});
		edit.addDocumentListener(new DocumentListener() {
			// 该值用于避免重复的更新
			private boolean toUpDate = true;
			// 删除用的判断
			private boolean toUpDate_2 = true;
	
			// 删除字段时进行判断
			public void removeUpdate(DocumentEvent e) {
	
				if (!toUpDate_2) {
					return;
				}
				String input = edit.getText();
	
				if (!input.isEmpty()) {
	
					for (final User item : elements) {
						// 取得账号用于匹配
						String value = item.toString();
						// 判断是否完全匹配
						// 更新中的值多尽量在 子线程之前计算出来给其直接使用，减少其运行时间，因为觉得运行太长会出问题
						if (value.equals(input)) {
							// 更新值时还会触发 值更改事件，那是不希望发生的，于是状态设置为不更新
							toUpDate_2 = false;
							// 新开一条线程等待值更新操作完毕再进行修改值
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									filluser.setSelectedItem(item);
									updateUser();
									// 更新完毕之后将状体改为希望更新，好执行下次的操作
									toUpDate_2 = true;
								}
							});
							// 当匹配到一个账户时就不再匹配
							return;
						}
					}
				}
				// 当没有匹配项时，将视图置为默认
				reSet();
			}
	
			public void insertUpdate(DocumentEvent e) {
	
				if (!toUpDate || edit.isDefaultText() || edit.isAutomaticFilling()) {
					return;
				}
				filterUser();
			}
	
			public void changedUpdate(DocumentEvent e) {
				System.out.println("何何何何何何何何何何何何何何何何何何何何何何何何何何");
			}
	
			// 根据输入的内容来过滤是否有符合的账户，有的话则跳转为那个账户
			// 否则将置为默认的
			public void filterUser() {
	
				String input = edit.getText();
	
				if (!input.isEmpty()) {
	
					for (final User item : elements) {
						// 取得账号用于匹配
						String value = item.toString();
						// 判断是否匹配
						// 更新中的值多尽量在 子线程之前计算出来给其直接使用，减少其运行时间，因为觉得运行太长会出问题
						if (value.startsWith(input)) {
	
							String fill = value.replaceAll(input, "");
	
							if (fill.length() == 0) {
								// 更新值时还会触发 值更改事件，那是不希望发生的，于是状态设置为不更新
								toUpDate = false;
								// 新开一条线程等待值更新操作完毕再进行修改值
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										filluser.setSelectedItem(item);
										updateUser();
										// 更新完毕之后将状体改为希望更新，好执行下次的操作
										toUpDate = true;
									}
								});
							} else {
								toUpDate = false;
								final int start = input.length();
								final int end = value.length();
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										// 该语句得放在edit.setItem(item);之前
										// 因为该语句会导致刷新输入框内容使得输入框输入了选择效果
										filluser.setSelectedItem(item);
	
										edit.setItem(item);
										edit.select(start, end);
										updateUser();
										toUpDate = true;
									}
								});
							}
							// 当匹配到一个账户时就不再匹配
							return;
						}
					}
				}
				// 当没有匹配项时，将视图置为默认
				reSet();
			}
	
		});
		filluser.setEditor(edit);
		createTextFieldHoverBorder();
	}

	// 鼠标悬停输入框时的边框
	private void createTextFieldHoverBorder() {
		final JLabel boder = new JLabel();
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				boder.setIcon(Images.blueBorder);
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				boder.setIcon(null);
			}
		});
		boder.setBounds(113, 145, 192, 30);
		centerPanel.add(boder);
	
		final JLabel boder2 = new JLabel();
		boder2.setOpaque(false);
		fillcipher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				boder2.setIcon(Images.blueBorder);
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				boder2.setIcon(null);
			}
		});
		boder2.setBounds(113, 179, 192, 30);
		centerPanel.add(boder2);
	}

	// 读取账户
	private void readUser() {
		elements.add(new User("77777", "Image\\Head\\1_100.gif", StateEnum.QME, false, false));
		elements.add(new User("88888", "Image\\Head\\10.png", StateEnum.BEBUSY, true, false));
		elements.add(new User("99999", "Image\\Head\\30.png", StateEnum.INVISIBLE, false, true));
	}

	// 生成提示栏
	private void createTip() {
		tipLabel = new JLabel(new ImageIcon("Image\\client\\login's\\!.png"), SwingConstants.LEFT) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 286192857212324985L;
	
			public void paintComponent(Graphics g) {
				g.setColor(new Color(249, 245, 215));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				super.paintComponent(g);
			}
		};
		tipLabel.setVerticalAlignment(SwingConstants.CENTER);
		tipLabel.setPreferredSize(new Dimension(380, 26));
		tipLabel.setFont(Fonts.MicrosoftAccor12);
		tipLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
		stretchButton = new JButton(new ImageIcon("Image\\client\\login's\\stretchButton.png"));
		stretchButton.setRolloverIcon(new ImageIcon("Image\\client\\login's\\stretchButton_hover.png"));
		stretchButton.setPressedIcon(new ImageIcon("Image\\client\\login's\\stretchButton_hover.png"));
		stretchButton.setFocusPainted(false);
		stretchButton.setContentAreaFilled(false);
		stretchButton.setPreferredSize(new Dimension(14, 14));
		tipLabel.add(stretchButton);
	
		stretchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contraction();
			}
		});
	}

	// 收缩
	private void contraction() {
		// frame.setSize(380, 292);
		this.setSize(380, 292);
		mainPanel.remove(tipLabel);
	}

	// 保存账户纪录
	private void preservationUser() {

	}

	// 重置面板上的值
	private void reSet() {
		// 重置头像
		headPanel.setIcon(ChangeImage.roundedCornerIcon(Images.defaultHead, 82, 82, 10));
		// 重置密码框
		emptyFillcipher();
		// 重置复选框
		automaticLogin.setSelected(false);
		rememberPassword.setSelected(false);
		// 重置状态按钮
		stateButton.setIcon(onLine.getIcon());
		stateButton.setState(onLine.getState());
	}

	// 密码输入框显示默认值
	private synchronized void emptyFillcipher() {
		if (!fillcipherFocusAdapter.isDefaultText) {
			fillcipher.setForeground(Colors.greyColor);
			fillcipher.setEchoChar('\u0000');
			fillcipher.setText("密码");
			fillcipherFocusAdapter.isDefaultText = true;
		}
	}

	// 设置背景
	private void setBg(ImageIcon bg) {
		this.bg = bg;
	}

	// 弹出
	private void eJect(String tipText) {

		tipLabel.setText(tipText);
		if (mainPanel.getComponentCount() == 1) {
			// frame.setSize(380, 318);
			this.setSize(380, 318);
			/*
			 * AWTUtilities.setWindowShape(frame, new
			 * RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(),
			 * frame.getHeight(), 11.0D, 11.0D));
			 */
			AWTUtilities.setWindowShape(this,
					new RoundRectangle2D.Double(0.0D, 0.0D, this.getWidth(), this.getHeight(), 11.0D, 11.0D));

			tipLabel.setBounds(0, 292, 380, 26);
			mainPanel.add(tipLabel, "South");
		}
	}

	// 更新当前账户
	private void updateUser() {

		if (!(filluser.getSelectedItem() instanceof User)) {
			return;
		}
		User user = (User) filluser.getSelectedItem();
		// 更新当前用户头像
		headPanel.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(user.getHeadURL()), 82, 82, 10));
		// 更改状态按钮
		switch (user.getState()) {
		// 代表在线
		case ONLINE:
			stateButton.setIcon(onLine.getIcon());
			stateButton.setState(onLine.getState());
			break;
		// 代表Q我吧
		case QME:
			stateButton.setIcon(qMe.getIcon());
			stateButton.setState(qMe.getState());
			break;
		// 代表离开
		case LEAVE:
			stateButton.setIcon(leave.getIcon());
			stateButton.setState(leave.getState());
			break;
		// 代表忙碌
		case BEBUSY:
			stateButton.setIcon(beBusy.getIcon());
			stateButton.setState(beBusy.getState());
			break;
		// 代表请勿打扰
		case DONTDISTURB:
			stateButton.setIcon(dontDisturb.getIcon());
			stateButton.setState(dontDisturb.getState());
			break;
		// 代表隐身
		case INVISIBLE:
			stateButton.setIcon(invisible.getIcon());
			stateButton.setState(invisible.getState());
			break;
		}
		// 判断是否自动登录
		if (user.isAutomaticLogin()) {
			automaticLogin.setSelected(true);
		} else {
			automaticLogin.setSelected(false);
		}
		// 判断是否记住密码，更改 复选框状态，密码输入框状态
		if (user.isRememberPassWord()) {
			rememberPassword.setSelected(true);
			fillcipher.setForeground(Color.black);
			fillcipher.setEchoChar('●');
			fillcipher.setText("123456789");
			fillcipherFocusAdapter.isDefaultText = false;
			deleteAll = true;
			isModify = false;
		} else {
			rememberPassword.setSelected(false);
			emptyFillcipher();
		}
	}

	/*
	 * 登录验证
	 */
	private void query() {
	
		String userNumber = edit.getText();
		char[] password = fillcipher.getPassword();
		// 判断账户/密码是否为空，为空直接调转、避免浪费
		if (userNumber.isEmpty() || edit.isDefaultText()) {
			System.out.println("请输入账号");
			filluser.requestFocus();
			eJect("请您输入账号后再登录");
			return;
		}
		if (password.length == 0 || fillcipherFocusAdapter.isDefaultText) {
			System.out.println("请输入密码");
			fillcipher.requestFocus();
			eJect("请您输入密码后再登录");
			return;
		}
	
		StateEnum state = stateButton.getState();
	
		// 取得 账号输入框 当前对象进行判断
		Object ob = filluser.getSelectedItem();
		if (ob instanceof String) {
			// 说明是自主输入的账户那么就有必要重新生成一个账号纪录
			// 只需在服务器判断登录成功时才将纪录重新写入保存文件中去
			User user = new User(userNumber, null, state, false, false);
			user.setPassword(new String(password));
			reaction(user);
	
		} else if (ob instanceof User) {
			// 说明是在原纪录上点击的登录那么就没有必要重新创建一个账号纪录了
			// 只需在服务器判断登录成功时才将纪录重新写入保存文件中去
			User user = (User) ob;
			if (user.isRememberPassWord() && !isModify) {
				user.setState(state);
				reaction(user);
				return;
			}
			user.setPassword(new String(password));
			user.setState(state);
			reaction(user);
		}
	}
	
	/**
	 * @Description:处理登陆界面
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void reaction(User user){

		MessageBean m = new MessageBean();
		m.setType(MessageType.SIGN_IN);
		m.setClientUser(user);
		
		MessageBean ms = server.sendUserInfoToServer(m);
		System.out.println(ms.getType());		
		switch (ms.getType()) {
		case MessageType.SIGN_IN_SUCCESS: {
			System.out.println("登录成功");
			//loginButton.setEnabled(false);
			//u = ms.getUser();
			
			startFriendListJFrame(user,ms,server.getSocket());
			
			user.setAutomaticLogin(automaticLogin.isSelected());
			user.setRememberPassWord(rememberPassword.isSelected());
			dispose();
			break;
		}
		case MessageType.SIGN_IN_FALSE: {
			if(ms.getErrorMessage() != null && !"".equals(ms.getErrorMessage()))
				eJect(ms.getErrorMessage());
			else
				eJect(" 登录失败！");
			//textField.setText("");
			//passwordField.setText("");
			//textField.requestFocus();	
			break;
		}
		default: {
			break;
		}
		}
	}

	private void startFriendListJFrame(User user,MessageBean ms, Socket socket) {
		//FriendListJFrame friendJFrame = new FriendListJFrame(ms, socket);
		
		UserInformation userInformation = ms.getUserInformation();
		FriendListJFrame2 friendJFrame = new FriendListJFrame2(userInformation, socket);
				
		friendJFrame.setVisible(true);
		
	}

	/**
	 * @Description:处理注册事件
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void ActionGoResign(){
		//resignButton.setEnabled(false);
		ResignJFrame resignJFrame = new ResignJFrame(this,server);
		resignJFrame.setVisible(true);// 显示注册界面
		setVisible(false);// 隐藏掉登陆界面		
	}
	
	/*
	 * 按钮专用的监听器
	 */
	private ActionListener actionAdapter = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginButton) {

				System.out.println("Login");
				query();

			} else if (e.getSource() == find) {

				System.out.println("找回密码");

			} else if (e.getSource() == registration) {

				System.out.println("注册账号");
				ActionGoResign();
			} else if (e.getSource() == closeButton) {

				System.exit(0);

			} else if (e.getSource() == minimizationButton) {

				System.out.println("最小化");
				// frame.setExtendedState(JFrame.ICONIFIED);
				setExtendedState(JFrame.ICONIFIED);

			}
		}

		private void setExtendedState(int iconified) {
			this.setExtendedState(JFrame.ICONIFIED);
		}
	};

	/*
	 * 密码输入框的焦点监听器
	 */
	class FillcipherFocusAdapter implements FocusListener {

		boolean isDefaultText = false;

		// 获得焦点时
		public void focusGained(FocusEvent e) {
			// 当获得焦点时判断当前内容是否是默认值，是则取消默认值和前景色
			String s = new String(fillcipher.getPassword());
			if (s.equals("密码")) {
				fillcipher.setForeground(Color.black);
				fillcipher.setEchoChar('●');
				fillcipher.setText("");
				isDefaultText = false;
			}
		}

		// 失去焦点时
		public void focusLost(FocusEvent e) {
			// 当前显示内容是空白时，填充默认值并将状态更改
			if (fillcipher.getPassword().length == 0) {
				fillcipher.setForeground(Colors.greyColor);
				fillcipher.setEchoChar('\u0000');
				fillcipher.setText("密码");
				isDefaultText = true;
			}
		}
	}

	/*
	 * 输入框监听器 ： 监听 账号输入框的 齿轮滚动、回车、选择值事件 根据 账号输入框 过滤出的 账号 来更新 其他组件的状态
	 */
	class UserChangeAdapter implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {

			int position = filluser.getSelectedIndex();
			int count = e.getWheelRotation();
			position += count;
			// 判断是否滚动的超过了边界，超出了就将位置设置为边界
			if (position < 0) {
				position = 0;
				return;
			} else if (position > filluser.getItemCount() - 1) {

				position = filluser.getItemCount() - 1;
				return;
			}
			filluser.setSelectedIndex(position);
		}
	}

	/*
	 * 状态选择 Item 组件的 专用监听器，这样可以去除IF判断
	 */
	class ItemActionAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			StateItem selectedItem = (StateItem) e.getSource();
			stateButton.setIcon(selectedItem.getIcon());
			stateButton.setState(selectedItem.getState());
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		LoginJFrame LoginJFrame = new LoginJFrame();
		LoginJFrame.setVisible(true);
	}
}

/*
 * 单元格绘制器
 */
class IconRenderer extends JLabel implements ListCellRenderer<Object> {
	/**
	* 
	*/
	private static final long serialVersionUID = 7575789313364976435L;

	private Color background = new Color(51, 137, 201);

	public IconRenderer() {
		setFont(new Font("微软雅黑", 0, 11));
		setPreferredSize(new Dimension(186, 46));
	}

	/*
	 * @version JComboBox 会将单元格设置为 透明，于是将方法重写成只能为 不透明
	 */
	public void setOpaque(boolean b) {
		super.setOpaque(true);
	}

	public Component getListCellRendererComponent(JList<?> list, Object obj, int row, boolean sel, boolean hasFocus) {

		User user = (User) obj;
		setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(user.getHeadURL()), 42, 42, 40, 40, 1, 1, 5));// 设置图片
		setText(user.toString());// 设置文本

		setBorder(new LineBorder(Color.white));// 绘制边框

		if (sel) {// 如果选中

			setForeground(Color.white);// 设前景色为白色
			setBackground(background);
		} else { // 没选中

			setForeground(Color.black);// 设前景色为黑色
			setBackground(Color.white);
		}
		return this;
	}
}

// 账号输入框编辑器
class UserFiledEdit implements ComboBoxEditor, FocusListener {

	protected JTextField editor = new JTextField();
	private Object item = "";
	private boolean first = true;
	private boolean isDefaultText = false;
	private boolean isAutomaticFilling = false;

	public UserFiledEdit() {
		editor.addFocusListener(this);
		editor.setBorder(new LineBorder(Color.white, 2));
	}

	// 获得焦点时
	public void focusGained(FocusEvent e) {
		setAutomaticFilling(false);
		// 当获得焦点时判断当前内容是否是默认值，是则取消默认值和前景色
		if (isDefaultText) {
			isDefaultText = false;
			editor.setForeground(Color.black);
			editor.setText("");
			return;
		}
		// 当当前内容不是默认值时、全选内容
		if (first) {
			first = false;
			selectAll();
		}
	}

	// 失去焦点时
	public void focusLost(FocusEvent e) {
		setAutomaticFilling(true);
		// 当前显示内容是空白时，填充默认值并将状态更改
		if (editor.getText().isEmpty()) {
			isDefaultText = true;
			editor.setForeground(Colors.greyColor);
			editor.setText("账号");
			return;
		}
		first = true;
	}

	public void selectAll() {
		editor.selectAll();
	}

	public Object getItem() {
		String text = editor.getText();
		if (!text.equals(item.toString())) {
			item = text;
		}
		return item;
	}

	public String getText() {
		return editor.getText();
	}

	public void setText(String s) {
		editor.setText(s);
	}

	public void select(int selectionStart, int selectionEnd) {
		editor.select(selectionStart, selectionEnd);
	}

	public void setItem(final Object item) {
		this.item = item;
		if (item == null) {
			this.item = "账号";
			editor.setForeground(Colors.greyColor);
			isDefaultText = true;
		}
		editor.setText(this.item.toString());
	}

	public Component getEditorComponent() {
		return editor;
	}

	public boolean isDefaultText() {
		return isDefaultText;
	}

	public void setState(boolean isDefaultText) {
		this.isDefaultText = isDefaultText;
	}

	public boolean isAutomaticFilling() {
		return isAutomaticFilling;
	}

	public void setAutomaticFilling(boolean isAutomaticFilling) {
		this.isAutomaticFilling = isAutomaticFilling;
	}

	public void addFocusListener(FocusListener l) {
		editor.addFocusListener(l);
	}

	public void removeFocusListener(FocusListener l) {
		editor.removeFocusListener(l);
	}

	public void addKeyListener(KeyListener l) {
		editor.addKeyListener(l);
	}

	public void removeKeyListener(KeyListener l) {
		editor.removeKeyListener(l);
	}

	public void addMouseListener(MouseListener mouseAdapter) {
		editor.addMouseListener(mouseAdapter);
	}

	public void removeMouseListener(MouseListener mouseAdapter) {
		editor.removeMouseListener(mouseAdapter);
	}

	public void addActionListener(final ActionListener l) {
		editor.addActionListener(l);
	}

	public void removeActionListener(final ActionListener l) {
		editor.removeActionListener(l);
	}

	public void addMouseWheelListener(MouseWheelListener l) {
		editor.addMouseWheelListener(l);
	}

	public void removeMouseWheelListener(MouseWheelListener l) {
		editor.removeMouseWheelListener(l);
	}

	public void addDocumentListener(DocumentListener l) {
		editor.getDocument().addDocumentListener(l);
	}

	public void removeDocumentListener(DocumentListener l) {
		editor.getDocument().removeDocumentListener(l);
	}
}
