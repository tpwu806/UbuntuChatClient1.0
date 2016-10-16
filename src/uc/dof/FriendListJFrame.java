package uc.dof;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import uc.dal.model.ClientServerThread;
import uc.dof.model.CellRenderer;
import uc.dof.model.OnlineListModel;
import uc.pub.common.MessageBean;

/**
 * @Description: 主窗口 好友列表
 * @author wutp 2016年10月15日
 * @version 1.0
 */
public class FriendListJFrame extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 处理第一张卡片.
	private JPanel jphy1, jphy2, jphy3;
	private JButton jphy_jb1, jphy_jb2, jphy_jb3;
	private JScrollPane jsp1;
	private String owner;
	// 处理第二张卡片(陌生人).
	private JPanel jpmsr1, jpmsr2, jpmsr3;
	private JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	private JScrollPane jsp2;
	private JLabel[] jb1s;
	// 把整个JFrame设置成CardLayout
	private CardLayout cl;
	//好友列表组件
	private Socket socket;
	public  Vector<String> onlines;// 在线好友
	public  ListModel listmodel;
	public  JList<String> list;

	//public String friends[] ;
	
	public Map<String,ChatJFrame> chatWinMap = new HashMap<>();	
	public ClientServerThread server ;
	
	public FriendListJFrame(String ownerId, Socket socket) {
		this.owner=ownerId;
		this.socket=socket;
		init();
		server = new ClientServerThread(owner, socket, this);
		Thread t = new Thread(server);
		t.start();
	}
	private void init(){
		
		// 处理第一张卡片(显示好友列表)
		jphy_jb1 = new JButton("我的好友");
		jphy_jb2 = new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("黑名单");
		jphy1 = new JPanel(new BorderLayout());
		// 假定有10个好友
		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));
	
		// 给jphy2，初始化10好友.
		/*friends=new String[1];
		friends[0] = owner;
		jb1s = new JLabel[friends.length];
	
		for (int i = 0; i < jb1s.length; i++) {
			jb1s[i] = new JLabel(friends[i] + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
			jb1s[i].setEnabled(false);
			if (jb1s[i].getText().equals(owner)) {
				jb1s[i].setEnabled(true);
			}
			jb1s[i].addMouseListener(this);
			jphy2.add(jb1s[i]);
	
		}*/
		
		jphy3 = new JPanel(new GridLayout(2, 1));
		// 把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
	
		//jsp1 = new JScrollPane(jphy2);
		
		onlines = new Vector<String>();
		listmodel = new OnlineListModel(onlines);
		list = new JList(listmodel);
		list.setCellRenderer(new CellRenderer());
		list.setOpaque(false);
		list.addMouseListener(this);
		//Border etch = BorderFactory.createEtchedBorder();
		//list.setBorder(BorderFactory.createTitledBorder(etch, "<" + owner + ">" + "在线客户:", TitledBorder.LEADING,
		//		TitledBorder.TOP, new Font("sdf", Font.BOLD, 20), Color.green));
	
		JScrollPane onlineJScrollPane = new JScrollPane(list);
		onlineJScrollPane.setBounds(430, 10, 245, 375);
		onlineJScrollPane.setOpaque(false);
		onlineJScrollPane.getViewport().setOpaque(false);
		getContentPane().add(onlineJScrollPane);
	
		// 对jphy1,初始化
		jphy1.add(jphy_jb1, "North");
		//jphy1.add(jsp1, "Center");
		jphy1.add(onlineJScrollPane);
		jphy1.add(jphy3, "South");
	
		// 处理第二张卡片
		jpmsr_jb1 = new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("陌生人");
		jpmsr_jb3 = new JButton("黑名单");
		jpmsr1 = new JPanel(new BorderLayout());
		// 假定有20个陌生人
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));
	
		// 给jphy2，初始化20陌生人.
		JLabel[] jb1s2 = new JLabel[20];
	
		for (int i = 0; i < jb1s2.length; i++) {
			jb1s2[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jpmsr2.add(jb1s2[i]);
		}
	
		jpmsr3 = new JPanel(new GridLayout(2, 1));
		// 把两个按钮加入到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
	
		jsp2 = new JScrollPane(jpmsr2);
	
		// 对jphy1,初始化
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");
	
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		// 在窗口显示自己的编号.
		this.setTitle(owner);
		this.setSize(140, 400);
		this.setVisible(true);
	}
	@Deprecated
	public void upateFriends(MessageBean m) {
		/*String onLineFriend[] = m.getCon().split(" ");
	
		for (int i = 0; i < onLineFriend.length; i++) {
	
			jb1s[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}*/
	}

	// 更新在线的好友情况
	@Deprecated
	public void upateFriendType(MessageBean m) {
		/*String onLineFriend[] = m.getCon().split(" ");
	
		for (int i = 0; i < onLineFriend.length; i++) {
	
			jb1s[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}*/
	}
	
	/**
	 * @Description:更新在线的好友
	 * @auther: wutp 2016年10月16日
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// 响应用户双击的事件，并得到好友的编号.
		List<String> to = list.getSelectedValuesList();
		if (arg0.getClickCount() == 2) {
			if (to.toString().contains(owner + "(我)")) {
				JOptionPane.showMessageDialog(getContentPane(), "不能和自己聊天");
				return;
			}
			/*if(to.size() > 1){
				
				return;
			}*/
			// 得到该好友的编号
			
			String friendNo = list.getSelectedValue();
			System.out.println("你希望和 "+friendNo+" 聊天");
			
			ChatJFrame chatJframe =new ChatJFrame(owner,friendNo,this);
			chatJframe.setVisible(true);
			chatWinMap.put(friendNo, chatJframe);

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		/*JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);*/

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		/*JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);*/

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// 如果点击了陌生人按钮，就显示第二张卡片
		if (arg0.getSource() == jphy_jb2) {
			cl.show(this.getContentPane(), "2");
		} else if (arg0.getSource() == jpmsr_jb1) {
			cl.show(this.getContentPane(), "1");
		}

	}

	public static void main(String[] args) {
		//FriendListJFrame FriendListJFrame = new FriendListJFrame("Test");
	}

}
