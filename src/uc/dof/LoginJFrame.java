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

import uc.dal.model.ClinetServer;
import uc.pub.common.MessageBean;
import uc.pub.common.MessageType;

/**
 * @Description: ��½����
 * @author wutp 2016��10��15��
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
						"images/��½����.jpg").getImage(), 0,
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
		loginButton.setIcon(new ImageIcon("images/��½.jpg"));
		loginButton.setBounds(246, 227, 50, 25);
		getRootPane().setDefaultButton(loginButton);
		contentPane.add(loginButton);

		resignButton = new JButton();
		resignButton.setIcon(new ImageIcon("images/ע��.jpg"));
		resignButton.setBounds(317, 227, 50, 25);
		contentPane.add(resignButton);

		// ��ʾ��Ϣ
		errorLabel = new JLabel();
		errorLabel.setBounds(60, 220, 151, 21);
		errorLabel.setForeground(Color.red);
		getContentPane().add(errorLabel);

		// ������½��ť
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionLogin();
			}
		});
		// ע�ᰴť����
		resignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				ActionGoResign();
			}
		});
	}

	
	/**
	 * @Description:�����½����
	 * @auther: wutp 2016��10��15��
	 * @return void
	 */
	private void ActionLogin(){
		String name = textField.getText();
		String pwd = new String(passwordField.getPassword());
						
		MessageBean m = new MessageBean();
		m.setType(MessageType.CLIENT_SIGN_IN);
		m.setName(name);
		m.setPwd(pwd);
			
		MessageBean ms = server.sendUserInfoToServer(m);
					
		switch (ms.getType()) {
		case MessageType.SERVER_SIGN_IN_SUCCESS: {
			System.out.println("��¼�ɹ�");
			loginButton.setEnabled(false);
			frame = new ChatroomJFrame(name, server.getSocket());
			frame.setVisible(true);// ��ʾ�������
			dispose();
			//setVisible(false);// ���ص���½����
			break;
		}
		case MessageType.SERVER_SIGN_IN_FALSE: {
			errorLabel.setText(" �������");
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
	 * @Description:����ע���¼�
	 * @auther: wutp 2016��10��15��
	 * @return void
	 */
	private void ActionGoResign(){
		//resignButton.setEnabled(false);
		resignJFrame = new ResignJFrame(this,server);
		resignJFrame.setVisible(true);// ��ʾע�����
		setVisible(false);// ���ص���½����		
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
					// ������½����
					LoginJFrame frame = new LoginJFrame();					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
}
