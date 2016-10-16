package uc.dof;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uc.dal.model.ClinetServer;
import uc.pub.common.MessageBean;
import uc.pub.common.MessageType;

/**
 * @Description: 注册界面
 * @author wutp 2016年10月14日
 * @version 1.0
 */
public class ResignJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblNewLabel;
	private JButton backButton;
	private JButton resignButton;
	private LoginJFrame loginJFrame;
	private ClinetServer server;
	
	public ResignJFrame(LoginJFrame loginJFrame,ClinetServer server) {
		this.loginJFrame=loginJFrame;
		this.server=server;
		init();
	}

	private void init(){
		setTitle("Registered cat chat room\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 250, 450, 300);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon("images/注册界面.jpg").getImage(), 0,0, getWidth(), getHeight(), null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(155, 52, 104, 21);
		textField.setOpaque(false);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setOpaque(false);
		passwordField.setBounds(195, 118, 104, 21);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(197, 182, 104, 21);
		passwordField_1.setOpaque(false);
		contentPane.add(passwordField_1);

		//注册按钮
		resignButton = new JButton();
		resignButton.setIcon(new ImageIcon("images/注册1.jpg"));
		resignButton.setBounds(320, 218, 80, 40);
		getRootPane().setDefaultButton(resignButton);
		contentPane.add(resignButton);

		//返回按钮
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon("images/返回.jpg"));
		backButton.setBounds(230, 218, 80, 40);
		contentPane.add(backButton);

		//提示信息
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(55, 228, 185, 20);
		lblNewLabel.setForeground(Color.red);
		contentPane.add(lblNewLabel);
		
		//返回按钮监听
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionGoLogin();
			}
		});

		// 注册按钮监听
		resignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ActionResign();
			}

		});
	}
	
	/**
	 * @Description:处理返回事件
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void ActionGoLogin(){
		backButton.setEnabled(false);
		//返回登陆界面
		loginJFrame.setVisible(true);
		//setVisible(false);
		this.dispose();
	}
	
	/**
	 * @Description:处理注册事件
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void ActionResign(){

		String u_name = textField.getText();
		String u_pwd = new String(passwordField.getPassword());
		String u_pwd_ag = new String(passwordField_1.getPassword());

		// 判断用户名是否在普通用户中已存在
		if (u_name.length() == 0) {
			lblNewLabel.setText("用户名不能为空！");
			return;
		}
		if (u_pwd.length() < 1 ) {
			lblNewLabel.setText("密码为空！");
			return;
		}
		if (!u_pwd_ag.equals(u_pwd)) {
			lblNewLabel.setText("密码不一致！");
			return;
		}		
					
		MessageBean m = new MessageBean();
		m.setType(MessageType.SIGN_UP);
		m.setName(u_name);
		m.setPwd(u_pwd);
		
		MessageBean ms = server.sendUserInfoToServer(m);
					
		switch (ms.getType()) {
		case MessageType.SIGN_UP_SUCCESS: {
			lblNewLabel.setText("注册成功！");
			break;
		}
		case MessageType.SIGN_UP_FALSE: {
			lblNewLabel.setText("此帐号已注册！");
			break;
		}
		default: {
			break;
		}
		}
		
	}

}
