package uc.dof;

import java.awt.BorderLayout;  
import java.awt.CardLayout;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;  
import java.awt.Font;  
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.SwingConstants;  
  
public class FriendView extends JFrame implements ActionListener,MouseListener{  
    //����ͼ��  
    ImageIcon background;  
    JPanel buttom;  
    JLabel imgLabel;  
    JButton jb;  
      
    //�ϲ㱱��  
    JLabel head,name,sign;  
      
    //�ϲ��ϱ�  
    JPanel jp;//��Ƭ����  
    CardLayout cl;  
      
    //��һ�ſ�Ƭ  
    JPanel jp1;  
    JButton jp1_jb1,jp1_jb2,jp1_jb3;  
      
    //�ڶ��ſ�Ƭ  
    JPanel jp2;  
    JScrollPane jsp;  
    JPanel jp_jsp;//������jsp  
    JButton jp2_jb1,jp2_jb2,jp2_jb3;  
      
    //�����ſ�Ƭ  
    JPanel jp3;  
    JScrollPane jsp2;  
    JPanel jp_jsp2;//������jsp2  
    JButton jp3_jb1,jp3_jb2,jp3_jb3;  
  
    //�����ſ�Ƭ  
    JPanel jp4;  
    JScrollPane jsp3;  
    JPanel jp_jsp3;  
    JButton jp4_jb1,jp4_jb2,jp4_jb3;  
  
      
    //���캯��  
    public FriendView() {  
        //������  
        backGround();  
          
        //�����ߵĶ�����ͷ���ǳƣ�ǩ����  
        head = new JLabel(new ImageIcon("Images/qqhead.jpg"));  
        head.setBounds(10, 40, 50, 50);  
        name = new JLabel("СQ������");  
        name.setBounds(70, 42, 80, 20);  
        name.setFont(new Font("����",Font.BOLD, 16));  
        name.setForeground(Color.white);  
        sign = new JLabel("����ǩ��");  
        sign.setBounds(70, 70, 80, 20);  
        sign.setForeground(Color.white);  
          
        //���ú����б�Ϊ��Ƭ����  
        cl = new CardLayout();  
        jp = new JPanel();  
        jp.setOpaque(false);  
        jp.setBounds(0, 205, background.getIconWidth(), background.getIconHeight());  
          
        //�����һ�ſ�Ƭ  
        firstCard();      
        //����ڶ��ſ�Ƭ  
        secondCard();     
        //��������ſ�Ƭ  
        thirdCard();  
        //��������ſ�Ƭ  
        fourthCard();  
          
        this.add(head);  
        this.add(name);  
        this.add(sign);  
        jp.setLayout(cl);  
        jp.add(jp1,"1");  
        jp.add(jp2,"2");  
        jp.add(jp3,"3");  
        jp.add(jp4,"4");  
        this.add(jp);  
        this.getLayeredPane().setLayout(null);  
        this.setLayout(null);  
        this.setSize(283, 720);  
        this.setLocation(800, 30);  
        this.setVisible(true);  
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
    }  
      
    //����������  
    private void backGround()  {  
        background = new ImageIcon("Images/bg.jpg");  
        imgLabel = new JLabel(background);  
        imgLabel.setBounds(0, 0,  background.getIconWidth(), background.getIconHeight());  
        buttom=(JPanel)this.getContentPane();  
        //��contentPane����Ϊ͸����  
        buttom.setOpaque(false);  
        this.getLayeredPane().add(imgLabel , new Integer(Integer.MIN_VALUE));  
    }  
      
    //�����һ�ſ�Ƭ����  
    private void firstCard()  {  
        jp1 = new JPanel();  
          
        jp1_jb1 = new JButton("> �ҵĺ���");  
        jp1_jb1.addActionListener(this);  
        jp1_jb1.setLayout(null);  
        jp1_jb1.setSize(277, 35);  
        jp1_jb1.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp1_jb2 = new JButton("> İ����");  
        jp1_jb2.addActionListener(this);  
        jp1_jb2.setLayout(null);  
        jp1_jb2.setBounds(0, 35, 277, 35);  
        jp1_jb2.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp1_jb3 = new JButton("> ������");  
        jp1_jb3.addActionListener(this);  
        jp1_jb3.setLayout(null);  
        jp1_jb3.setBounds(0, 70, 277, 35);  
        jp1_jb3.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp1.add(jp1_jb1);  
        jp1.add(jp1_jb2);  
        jp1.add(jp1_jb3);  
        jp1.setLayout(null);  
        jp1.setOpaque(false);  
    }  
      
    //����ڶ��ſ�Ƭ����  
    private void secondCard()  {  
        jp2 = new JPanel();  
          
        jp2_jb1 = new JButton("�� �ҵĺ���");  
        jp2_jb1.addActionListener(this);  
        jp2_jb1.setLayout(null);  
        jp2_jb1.setSize(277, 35);  
        jp2_jb1.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp2_jb2 = new JButton("> İ����");  
        jp2_jb2.addActionListener(this);  
        jp2_jb2.setLayout(null);  
        jp2_jb2.setBounds(0, 354, 277, 35);  
        jp2_jb2.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp2_jb3 = new JButton("> ������");  
        jp2_jb3.addActionListener(this);  
        jp2_jb3.setLayout(null);  
        jp2_jb3.setBounds(0, 389, 277, 35);  
        jp2_jb3.setHorizontalAlignment(SwingConstants.LEFT );  
          
        //�ٶ�30������  
        jp_jsp = new JPanel(new GridLayout(30,1));  
        jsp = new JScrollPane(jp_jsp);  
          
        //��ʼ��30������  
        JLabel[] jbls = new JLabel[30];  
        for(int i=0; i<jbls.length; i++)  
        {  
            jbls[i] = new JLabel(i+1+"�Ż�����", new ImageIcon("Images/qqhead.jpg"), JLabel.LEFT);  
            jbls[i].addMouseListener(this);  
            jp_jsp.add(jbls[i]);  
        }  
  
        jsp.setBounds(1, 35, 275, 319);  
          
        //jsp.setLayout(null);���󣡣�jsp������û�в���  
        jp2.add(jsp);  
        jp2.add(jp2_jb1);  
        jp2.add(jp2_jb2);  
        jp2.add(jp2_jb3);  
        jp2.setLayout(null);  
        jp2.setOpaque(false);  
    }  
      
    //��������ſ�Ƭ����  
    private void thirdCard()  {  
        jp3 = new JPanel();  
          
        jp3_jb1 = new JButton("> �ҵĺ���");  
        jp3_jb1.addActionListener(this);  
        jp3_jb1.setLayout(null);  
        jp3_jb1.setSize(277, 35);  
        jp3_jb1.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp3_jb2 = new JButton("�� İ����");  
        jp3_jb2.addActionListener(this);  
        jp3_jb2.setLayout(null);  
        jp3_jb2.setBounds(0, 35, 277, 35);  
        jp3_jb2.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp3_jb3 = new JButton("> ������");  
        jp3_jb3.addActionListener(this);  
        jp3_jb3.setLayout(null);  
        jp3_jb3.setBounds(0, 389, 277, 35);  
        jp3_jb3.setHorizontalAlignment(SwingConstants.LEFT );  
          
        //�ٶ�30������  
        jp_jsp2 = new JPanel(new GridLayout(10,1));  
        jsp2 = new JScrollPane(jp_jsp2);  
          
        //��ʼ��30������  
        JLabel[] jbls = new JLabel[10];  
        for(int i=0; i<jbls.length; i++)  
        {  
            jbls[i] = new JLabel(i+1+"��İ����", new ImageIcon("Images/qqhead.jpg"), JLabel.LEFT);  
            jbls[i].addMouseListener(this);  
            jp_jsp2.add(jbls[i]);  
        }  
  
        jsp2.setBounds(1, 70, 275, 319);  
          
        jp3.add(jsp2);  
        jp3.add(jp3_jb1);  
        jp3.add(jp3_jb2);  
        jp3.add(jp3_jb3);  
        jp3.setLayout(null);  
        jp3.setOpaque(false);  
    }  
      
    //��������ſ�Ƭ����  
    private void fourthCard()  {  
        jp4 = new JPanel();  
          
        jp4_jb1 = new JButton("> �ҵĺ���");  
        jp4_jb1.addActionListener(this);  
        jp4_jb1.setLayout(null);  
        jp4_jb1.setSize(277, 35);  
        jp4_jb1.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp4_jb2 = new JButton("> İ����");  
        jp4_jb2.addActionListener(this);  
        jp4_jb2.setLayout(null);  
        jp4_jb2.setBounds(0, 35, 277, 35);  
        jp4_jb2.setHorizontalAlignment(SwingConstants.LEFT );  
          
        jp4_jb3 = new JButton("�� ������");  
        jp4_jb3.addActionListener(this);  
        jp4_jb3.setLayout(null);  
        jp4_jb3.setBounds(0, 70, 277, 35);  
        jp4_jb3.setHorizontalAlignment(SwingConstants.LEFT );  
          
        //�ٶ�30������  
        jp_jsp3 = new JPanel(new GridLayout(10,1));  
        jsp3 = new JScrollPane(jp_jsp3);  
          
        //��ʼ��30������  
        JLabel[] jbls = new JLabel[5];  
        for(int i=0; i<jbls.length; i++)  
        {  
            jbls[i] = new JLabel(i+1+"�ź�����", new ImageIcon("Images/qqhead.jpg"), JLabel.LEFT);  
            jbls[i].addMouseListener(this);  
            jp_jsp3.add(jbls[i]);  
        }  
  
        jsp3.setBounds(1, 105, 275, 319);  
          
        jp4.add(jsp3);  
        jp4.add(jp4_jb1);  
        jp4.add(jp4_jb2);  
        jp4.add(jp4_jb3);  
        jp4.setLayout(null);  
        jp4.setOpaque(false);  
    }
    
    private void toCenter() {// ������ʾ
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width / 2;
		int screenHeight = screenSize.height / 2;
		int height = this.getHeight();
		int width = this.getWidth();
		this.setLocation(screenWidth - width / 2, screenHeight - height / 2);
	}
  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        //��һ�ſ�Ƭ�İ�ť  
        if(e.getSource()==jp1_jb1)  
        {  
            cl.show(jp, "2");;  
        }  
        if(e.getSource()==jp1_jb2)  
        {  
            cl.show(jp, "3");;  
        }  
        if(e.getSource()==jp1_jb3)  
        {  
            cl.show(jp, "4");;  
        }  
          
        //�ڶ��ſ�Ƭ�İ�ť  
        if(e.getSource()==jp2_jb1)  
        {  
            cl.show(jp, "1");;  
        }  
        if(e.getSource()==jp2_jb2)  
        {  
            cl.show(jp, "3");;  
        }  
        if(e.getSource()==jp2_jb3)  
        {  
            cl.show(jp, "4");;  
        }  
      
        //�����ſ�Ƭ�İ�ť  
        if(e.getSource()==jp3_jb1)  
        {  
            cl.show(jp, "2");;  
        }  
        if(e.getSource()==jp3_jb2)  
        {  
            cl.show(jp, "1");;  
        }  
        if(e.getSource()==jp3_jb3)  
        {  
            cl.show(jp, "4");;  
        }  
      
        //�����ſ�Ƭ�İ�ť  
        if(e.getSource()==jp4_jb1)  
        {  
            cl.show(jp, "2");;  
        }  
        if(e.getSource()==jp4_jb2)  
        {  
            cl.show(jp, "3");;  
        }  
        if(e.getSource()==jp4_jb3)  
        {  
            cl.show(jp, "1");;  
        }  
          
    }  
  
    @Override  
    public void mouseClicked(MouseEvent e) {  
        // TODO Auto-generated method stub  
        if(e.getClickCount()==2)  
        {  
            String str = ((JLabel)e.getSource()).getText();  
            System.out.println("��ϣ����"+str+"���졣");  
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
    public void mouseEntered(MouseEvent e) {  
        // TODO Auto-generated method stub  
        JLabel jl =(JLabel)e.getSource();  
        jl.setForeground(Color.red);  
              
      
    }  
  
    @Override  
    public void mouseExited(MouseEvent e) {  
        // TODO Auto-generated method stub  
        JLabel jl =(JLabel)e.getSource();  
        jl.setForeground(Color.black);  
    } 
    
    public static void main(String[] args){
    	FriendView fv = new FriendView();
    	fv.setVisible(true);
    }
      
}  
