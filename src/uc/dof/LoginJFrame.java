package uc.dof;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uc.common.MessageBean;
import uc.common.MessageType;
import uc.dal.ClinetServer;

/**
 * @Description: 登陆界面
 * @author wutp 2016年10月15日
 * @version 1.0
 */
public class LoginJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton ;
	private JButton resignButton;
	private JLabel errorLabel ;
	private ResignJFrame resignJFrame;
	private ChatroomJFrame frame ;
	private ClinetServer server;

	/**
	 * Create the frame.
	 */
	public LoginJFrame () {
		init();
		server = new ClinetServer();
	}
	
	private void init(){
		setTitle("Landing cat chat room\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 250, 450, 300);
		
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon(
						"images/登陆界面.jpg").getImage(), 0,
						0, getWidth(), getHeight(), null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(133, 183, 104, 21);
		textField.setOpaque(false);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setEchoChar('*');
		passwordField.setOpaque(false);
		passwordField.setBounds(133, 219, 104, 21);
		contentPane.add(passwordField);

		loginButton = new JButton();
		loginButton.setIcon(new ImageIcon("images/登陆.jpg"));
		loginButton.setBounds(246, 227, 50, 25);
		getRootPane().setDefaultButton(loginButton);
		contentPane.add(loginButton);

		resignButton = new JButton();
		resignButton.setIcon(new ImageIcon("images/注册.jpg"));
		resignButton.setBounds(317, 227, 50, 25);
		contentPane.add(resignButton);

		// 提示信息
		errorLabel = new JLabel();
		errorLabel.setBounds(60, 220, 151, 21);
		errorLabel.setForeground(Color.red);
		getContentPane().add(errorLabel);

		// 监听登陆按钮
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionLogin();
			}
		});
		// 注册按钮监听
		resignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				ActionGoResign();
			}
		});
	}

	
	/**
	 * @Description:处理登陆界面
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void ActionLogin(){
		String name = textField.getText();
		String pwd = new String(passwordField.getPassword());
						
		MessageBean m = new MessageBean();
		m.setType(MessageType.SIGN_IN);
		m.setName(name);
		m.setPwd(pwd);
			
		MessageBean ms = server.sendUserInfoToServer(m);
		System.out.println(ms.getType());		
		switch (ms.getType()) {
		case MessageType.SIGN_IN_SUCCESS: {
			System.out.println("登录成功");
			//loginButton.setEnabled(false);
			FriendListJFrame friendJFrame = new FriendListJFrame(name, server.getSocket());
			friendJFrame.setVisible(true);
			dispose();
			break;
		}
		case MessageType.SIGN_IN_FALSE: {
			if(ms.getErrorMessage() != null && !"".equals(ms.getErrorMessage()))
				errorLabel.setText(ms.getErrorMessage());
			else
				errorLabel.setText(" 登录失败！");
			textField.setText("");
			passwordField.setText("");
			textField.requestFocus();
	
			break;
		}
		default: {
			break;
		}
		}
	}

	/**
	 * @Description:处理注册事件
	 * @auther: wutp 2016年10月15日
	 * @return void
	 */
	private void ActionGoResign(){
		//resignButton.setEnabled(false);
		resignJFrame = new ResignJFrame(this,server);
		resignJFrame.setVisible(true);// 显示注册界面
		setVisible(false);// 隐藏掉登陆界面		
	}
	
	protected void errorTip(String str) {
		JOptionPane.showMessageDialog(contentPane, str, "Error Message",
				JOptionPane.ERROR_MESSAGE);
		textField.setText("");
		passwordField.setText("");
		textField.requestFocus();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 启动登陆界面
					LoginJFrame frame = new LoginJFrame();					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
}
