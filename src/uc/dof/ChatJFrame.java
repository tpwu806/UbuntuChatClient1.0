package uc.dof;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uc.pub.common.MessageBean;
import uc.pub.common.MessageType;

/**
 * @Description: һ��һ�������
 * @author wutp 2016��10��15��
 * @version 1.0
 */
public class ChatJFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea jta;
	private JTextField jtf;
	private JButton jb;
	private JPanel jp;
	private String name,friendName;
	private FriendListJFrame MainJFrame;
	
	public ChatJFrame(String name,String friendName,FriendListJFrame FriendListJFrame){
		this.name=name;
		this.friendName=friendName;
		this.MainJFrame=FriendListJFrame;
		init();			
	}
	
	private void init(){
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("����");
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(friendName);
		this.setIconImage((new ImageIcon("images/qq.gif").getImage()));
		this.setSize(300, 200);
		this.setVisible(true);
	}
	
	//дһ��������������ʾ��Ϣ
	public void showMessage(MessageBean m)
	{
		String info=m.getName() + ":" + m.getInfo() +"\r\n";
		this.jta.append(info);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==jb){
			MessageBean m = new MessageBean();
			m.setType(MessageType.SINGLETON_CHAR);
			m.setName(name);
			m.setFriendName(friendName);
			m.setInfo(jtf.getText().trim());
			m.setTimer(new java.util.Date().toString());
			sendMessage(m);
		}
			
	}
	/**
	 * @Description:ͳһ���÷�����ϢclientInputThread
	 * @auther: wutp 2016��10��15��
	 * @param clientBean
	 * @return void
	 */
	private void sendMessage(MessageBean clientBean) {
		this.MainJFrame.server.sendMessage(clientBean);
	}

	public static void main(String[] args) {
		//QqChat qqChat=new QqChat("1","2");
	}
}
