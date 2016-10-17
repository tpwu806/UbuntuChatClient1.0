package uc.dof;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import uc.dal.model.ClientServerThread;
import uc.dof.model.CellRenderer;
import uc.dof.model.OnlineListModel;
import uc.pub.common.MessageBean;

/**
 * @Description: ������ �����б�
 * @author wutp 2016��10��15��
 * @version 1.0
 */
public class FriendListJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane jtabPan ;
	private JPanel friendJPanel, groupJPanel;
	
	// �����һ�ſ�Ƭ.
	private JPanel jphy1, jphy2, jphy3;
	private JButton jphy_jb1, jphy_jb2, jphy_jb3;
	private JScrollPane onlineJScrollPane;
	
	// ����ڶ��ſ�Ƭ(İ����).
	private JPanel jpmsr1, jpmsr2, jpmsr3;
	private JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	private JScrollPane jsp2;
	private JLabel[] jb1s;
	// ������JFrame���ó�CardLayout
	private CardLayout cl;
	//�����б����
	private String owner;
	public  Vector<String> onlines;// ���ߺ���
	public  ListModel listmodel;
	public  JList<String> list;
	//Ⱥ���б����
	JScrollPane groupScrollPane ;
	public  Vector<String> groups;// ���ߺ���
	public  ListModel grouplistmodel;
	public  JList<String> grouplist;
	
	//public String friends[] ;
	
	public ClientServerThread server ;
	public Map<String,ChatJFrame> chatWinMap = new HashMap<>();	
	public Map<String,ChatroomJFrame> roomWinMap = new HashMap<>(); 
	
	public FriendListJFrame(String ownerId, Socket socket) {
		this.owner=ownerId;
		initialize();
		server = new ClientServerThread(owner, socket, this);
		Thread t = new Thread(server);
		t.start();
	}
	private void initialize(){
		jtabPan = new JTabbedPane();
		friendJPanel = new JPanel();
		//groupJPanel = new JPanel();
		
		initFrient();		
		//����Ϣ
		initGroup();
		
		jtabPan.add("��ϵ��", friendJPanel);
		jtabPan.add("Ⱥ��", groupScrollPane);
		// �ڴ�����ʾ�Լ��ı��.
		this.add(jtabPan);
		this.setTitle(owner);
		this.setSize(240, 400);
		this.setVisible(true);
		toCenter();
		
	}
	
	/**
	 * @Description:����
	 * @auther: wutp 2016��10��17��
	 * @return void
	 */
	private void initFrient() {
		// �����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb2 = new JButton("İ����");
		//jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("������");
		jphy1 = new JPanel(new BorderLayout());
		// �ٶ���10������
		jphy2 = new JPanel(new GridLayout(10, 1, 4, 4));

		jphy3 = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);

		onlines = new Vector<String>();
		listmodel = new OnlineListModel(onlines);
		list = new JList(listmodel);
		list.setCellRenderer(new CellRenderer());
		list.setOpaque(false);
		//list.addMouseListener(this);
		// Border etch = BorderFactory.createEtchedBorder();
		// list.setBorder(BorderFactory.createTitledBorder(etch, "<" + owner +
		// ">" + "���߿ͻ�:", TitledBorder.LEADING,
		// TitledBorder.TOP, new Font("sdf", Font.BOLD, 20), Color.green));

		onlineJScrollPane = new JScrollPane(list);
		onlineJScrollPane.setBounds(430, 10, 245, 375);
		onlineJScrollPane.setOpaque(false);
		onlineJScrollPane.getViewport().setOpaque(false);
		getContentPane().add(onlineJScrollPane);

		// ��jphy1,��ʼ��
		jphy1.add(jphy_jb1, "North");
		jphy1.add(onlineJScrollPane, "Center");
		jphy1.add(jphy3, "South");

		// ����ڶ��ſ�Ƭ
		jpmsr_jb1 = new JButton("�ҵĺ���");
		//jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("������");
		jpmsr1 = new JPanel(new BorderLayout());
		// �ٶ���20��İ����
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));

		// ��jphy2����ʼ��20İ����.
		JLabel[] jb1s2 = new JLabel[20];

		for (int i = 0; i < jb1s2.length; i++) {
			jb1s2[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jpmsr2.add(jb1s2[i]);
		}

		jpmsr3 = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		jsp2 = new JScrollPane(jpmsr2);

		// ��jphy1,��ʼ��
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");
		
		cl = new CardLayout();
		friendJPanel.setLayout(cl);
		friendJPanel.add(jphy1, "1");
		friendJPanel.add(jpmsr1, "2");

		// ���Ѱ�ť
		jphy_jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionPerformed(e);
			}
		});
		// ��������ť
		jpmsr_jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionPerformed(e);
			}
		});
		// �б����
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ActionFriendlist(e);
			}
		});
	}

	/**
	 * @Description:Ⱥ
	 * @auther: wutp 2016��10��17��
	 * @return void
	 */
	private void initGroup(){
		groups = new Vector<String>();
		groups.add("���ǵĻ���");
		groups.add("��Ӿ���ֲ�");
		grouplistmodel = new OnlineListModel(groups);
		grouplist = new JList(grouplistmodel);
		grouplist.setCellRenderer(new CellRenderer());
		grouplist.setOpaque(false);
		//grouplist.addMouseListener(this);
		// Border etch = BorderFactory.createEtchedBorder();
		// list.setBorder(BorderFactory.createTitledBorder(etch, "<" + owner +
		// ">" + "���߿ͻ�:", TitledBorder.LEADING,
		// TitledBorder.TOP, new Font("sdf", Font.BOLD, 20), Color.green));

		groupScrollPane = new JScrollPane(grouplist);
		//groupScrollPane.setBounds(430, 10, 245, 375);
		groupScrollPane.setOpaque(false);
		groupScrollPane.getViewport().setOpaque(false);
		//getContentPane().add(groupScrollPane);
		//groupJPanel.add(groupScrollPane);
		
		// �б����
		grouplist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ActionGrouplist(e);
			}
		});
	}

	/**
	 * @Description:������ʾ
	 * @auther: wutp 2016��10��17��
	 * @return void
	 */
	private void toCenter() {// ������ʾ
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width / 2;
		int screenHeight = screenSize.height / 2;
		int height = this.getHeight();
		int width = this.getWidth();
		this.setLocation(screenWidth - width / 2, screenHeight - height / 2);
	}
	private void ActionGrouplist(MouseEvent e) {
		List<String> to = grouplist.getSelectedValuesList();

		if (e.getClickCount() == 2) {			
			String groupNo = grouplist.getSelectedValue();
			System.out.println("��ϣ���� " + groupNo + " Ⱥ");

			ChatroomJFrame roomJframe = new ChatroomJFrame(owner, groupNo, this);
			roomJframe.setVisible(true);
			roomWinMap.put(groupNo, roomJframe);

		}
	}
	
	private void ActionFriendlist(MouseEvent arg0) {
		// ��Ӧ�û�˫�����¼������õ����ѵı��.
		List<String> to = list.getSelectedValuesList();
		
		if (arg0.getClickCount() == 2) {
			if (to.toString().contains(owner + "(��)")) {
				JOptionPane.showMessageDialog(getContentPane(), "���ܺ��Լ�����");
				return;
			}
		
			String friendNo = list.getSelectedValue();
			System.out.println("��ϣ���� "+friendNo+" ����");
			
			ChatJFrame chatJframe =new ChatJFrame(owner,friendNo,this);
			chatJframe.setVisible(true);
			chatWinMap.put(friendNo, chatJframe);
	
		}
	
	}
	private void ActionPerformed(ActionEvent arg0) {
		// ��������İ���˰�ť������ʾ�ڶ��ſ�Ƭ
		if (arg0.getSource() == jphy_jb2) {
			cl.show(this.getContentPane(), "2");
		} else if (arg0.getSource() == jpmsr_jb1) {
			cl.show(this.getContentPane(), "1");
		}
	
	}
	@Deprecated
	public void upateFriends(MessageBean m) {
		/*String onLineFriend[] = m.getCon().split(" ");
	
		for (int i = 0; i < onLineFriend.length; i++) {
	
			jb1s[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}*/
	}

	// �������ߵĺ������
	@Deprecated
	public void upateFriendType(MessageBean m) {
		/*String onLineFriend[] = m.getCon().split(" ");
	
		for (int i = 0; i < onLineFriend.length; i++) {
	
			jb1s[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}*/
	}
	
	/**
	 * @Description:�������ߵĺ���
	 * @auther: wutp 2016��10��16��
	 * @param m
	 * @return void
	 */
	@Deprecated
	public void upateFriend(MessageBean m) {
/*
		friends = m.getClients().toArray(friends);

		jb1s = new JLabel[friends.length];

		for (int i = 0; i < jb1s.length; i++) {
			jb1s[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
			jb1s[i].setEnabled(false);
			if (jb1s[i].getText().equals(owner)) {
				jb1s[i].setEnabled(true);
			}
			jb1s[i].addMouseListener(this);
			jphy2.add(jb1s[i]);

		}
		jphy2.revalidate();
		jphy2.repaint();*/

	}	

	public static void main(String[] args) {
		//FriendListJFrame FriendListJFrame = new FriendListJFrame("Test");
	}

}
