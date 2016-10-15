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

import uc.dal.model.ClientInputThread;
import uc.pub.UtilTool;
import uc.pub.common.MessageBean;
import uc.pub.common.MessageType;

/**
 * @Description: Ⱥ�������
 * @author wutp 2016��10��15��
 * @version 1.0
 */
public class ChatroomJFrame extends JFrame {

	private static final long serialVersionUID = 6129126482250125466L;

	private  JPanel contentPane;
	private  Socket clientSocket;
	private  String name;
	public  JTextArea chartextArea;//������Ϣ��ʾ����
	public  JList<String> list;
	public  String filePath;// �ļ�·��
	public  JLabel lblNewLabel;// �ļ�������ʾ
	public  JProgressBar progressBar;
	public  ListModel listmodel;

	public  Vector<String> onlines;// �����û�
	public  boolean isSendFile = false;
	public  boolean isReceiveFile = false;

	// ����
	private  File file, file2;
	private  URL cb, cb2;
	public  AudioClip aau;

	public  AudioClip aau2;

	public JTextArea sendJTextArea;// ������
	private JButton sendButton;// ���Ͱ�ť
	private JButton closeButton;// �رհ�ť

	private ClientInputThread clientInputThread;

	/**
	 * Create the frame.
	 */

	public ChatroomJFrame(String u_name, Socket client) {
		// ��ֵ
		name = u_name;
		clientSocket = client;
		init();
	}

	private void init() {

		onlines = new Vector<String>();
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

		setTitle(name);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 100, 688, 510);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon("images/������1.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
			}

		};
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//������Ϣ��ʾ����
		JScrollPane charScrollPane = new JScrollPane();
		charScrollPane.setBounds(10, 10, 410, 300);
		getContentPane().add(charScrollPane);

		chartextArea = new JTextArea();
		chartextArea.setEditable(false);
		chartextArea.setLineWrap(true);// �����Զ����й���
		chartextArea.setWrapStyleWord(true);// ������в����ֹ���
		chartextArea.setFont(new Font("sdf", Font.BOLD, 13));
		charScrollPane.setViewportView(chartextArea);

		// ��������
		JScrollPane writeScrollPane = new JScrollPane();
		writeScrollPane.setBounds(10, 347, 411, 97);
		getContentPane().add(writeScrollPane);

		sendJTextArea = new JTextArea();
		sendJTextArea.setLineWrap(true);// �����Զ����й���
		sendJTextArea.setWrapStyleWord(true);// ������в����ֹ���
		writeScrollPane.setViewportView(sendJTextArea);

		// �رհ�ť
		closeButton = new JButton("\u5173\u95ED");
		closeButton.setBounds(214, 448, 60, 30);
		getContentPane().add(closeButton);

		// ���Ͱ�ť
		sendButton = new JButton("\u53D1\u9001");
		sendButton.setBounds(313, 448, 60, 30);
		getRootPane().setDefaultButton(sendButton);
		getContentPane().add(sendButton);

		// ���߿ͻ��б�
		listmodel = new OnlineListModel(onlines);
		list = new JList<String>(listmodel);
		list.setCellRenderer(new CellRenderer());
		list.setOpaque(false);
		Border etch = BorderFactory.createEtchedBorder();
		list.setBorder(BorderFactory.createTitledBorder(etch, "<" + name + ">" + "���߿ͻ�:", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("sdf", Font.BOLD, 20), Color.green));

		JScrollPane onlineJScrollPane = new JScrollPane(list);
		onlineJScrollPane.setBounds(430, 10, 245, 375);
		onlineJScrollPane.setOpaque(false);
		onlineJScrollPane.getViewport().setOpaque(false);
		getContentPane().add(onlineJScrollPane);

		// �ļ�������
		progressBar = new JProgressBar();
		progressBar.setBounds(430, 390, 245, 15);
		progressBar.setMinimum(1);
		progressBar.setMaximum(100);
		getContentPane().add(progressBar);

		// �ļ�������ʾ
		lblNewLabel = new JLabel("\u6587\u4EF6\u4F20\u9001\u4FE1\u606F\u680F:");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(430, 410, 245, 15);
		getContentPane().add(lblNewLabel);

		try {
			// ��Ϣ��ʾ����
			file = new File("sounds/eo.wav");
			//cb = file.toURL();
			cb = file.toURI().toURL();
			aau = Applet.newAudioClip(cb);
			// ������ʾ����
			file2 = new File("sounds/ding.wav");
			//cb2 = file2.toURL();
			cb2 = file2.toURI().toURL();
			aau2 = Applet.newAudioClip(cb2);
			aau2.play();
			// �����ͻ������߳�
			// new ClientInputThread().start();
			clientInputThread = new ClientInputThread(name, clientSocket, this);
			Thread t = new Thread(clientInputThread);
			t.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ���Ͱ�ť
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionSend();
			}
		});

		// �رհ�ť
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionClose();
			}
		});

		// �뿪
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ActionWindowClosing();
			}
		});

		// �б����
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Actionlist(e);

			}
		});
	}

	/**
	 * �����Ͱ�ť
	 * 
	 * @Description:
	 * @auther: wutp 2016��10��15��
	 * @return void
	 */
	private void ActionSend() {
		String info = sendJTextArea.getText();
		List<String> to = list.getSelectedValuesList();

		if (to.size() < 1) {
			JOptionPane.showMessageDialog(getContentPane(), "��ѡ���������");
			return;
		}
		if (to.toString().contains(name + "(��)")) {
			JOptionPane.showMessageDialog(getContentPane(), "�������Լ�������Ϣ");
			return;
		}
		if (info.equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "���ܷ��Ϳ���Ϣ");
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

		// �Լ���������ҲҪ��ʵ���Լ�����Ļ����
		chartextArea.append(time + " �Ҷ�" + to + "˵:\r\n" + info + "\r\n");

		sendMessage(clientBean);
		sendJTextArea.setText(null);
		sendJTextArea.requestFocus();
	}

	/**
	 * @Description:����ر��¼�
	 * @auther: wutp 2016��10��15��
	 * @return void
	 */
	private void ActionClose() {
		if (isSendFile || isReceiveFile) {
			JOptionPane.showMessageDialog(contentPane, "���ڴ����ļ��У��������뿪...", "Error Message", JOptionPane.ERROR_MESSAGE);
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
	 * ����رմ����¼�
	 */
	private void ActionWindowClosing() {
		if (isSendFile || isReceiveFile) {
			JOptionPane.showMessageDialog(contentPane, "���ڴ����ļ��У��������뿪...", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(getContentPane(), "��ȷ��Ҫ�뿪������");
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
	 * @Description:�����б�����¼�
	 * @auther: wutp 2016��10��15��
	 * @return void
	 */
	private void Actionlist(MouseEvent e) {
		List<String> to = list.getSelectedValuesList();
		if (e.getClickCount() == 2) {

			if (to.toString().contains(name + "(��)")) {
				JOptionPane.showMessageDialog(getContentPane(), "�������Լ������ļ�");
				return;
			}

			// ˫�����ļ��ļ�ѡ���
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("ѡ���ļ���"); // ����Ŷ...
			chooser.showDialog(getContentPane(), "ѡ��"); // ���ǰ�ť������..

			// �ж��Ƿ�ѡ�����ļ�
			if (chooser.getSelectedFile() != null) {
				// ��ȡ·��
				filePath = chooser.getSelectedFile().getPath();
				File file = new File(filePath);
				// �ļ�Ϊ��
				if (file.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), filePath + "�ļ�Ϊ��,��������.");
					return;
				}

				MessageBean clientBean = new MessageBean();
				clientBean.setType(2);// �������ļ�
				clientBean.setSize(new Long(file.length()).intValue());
				clientBean.setName(name);
				clientBean.setTimer(UtilTool.getTimer());
				clientBean.setFileName(file.getName()); // ��¼�ļ�������
				clientBean.setInfo("�������ļ�");

				// �ж�Ҫ���͸�˭
				HashSet<String> set = new HashSet<String>();
				set.addAll(list.getSelectedValuesList());
				clientBean.setClients(set);
				sendMessage(clientBean);
			}
		}
	}

	/**
	 * @Description:ͳһ���÷�����ϢclientInputThread
	 * @auther: wutp 2016��10��15��
	 * @param clientBean
	 * @return void
	 */
	private void sendMessage(MessageBean clientBean) {
		clientInputThread.sendMessage(clientBean);
	}

}
