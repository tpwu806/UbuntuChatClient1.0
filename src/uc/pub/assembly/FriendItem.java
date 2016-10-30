package uc.pub.assembly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import uc.common.dto.FriendItemModel;
import uc.pub.tool.ChangeImage;
import uc.pub.tool.Colors;
import uc.pub.tool.Fonts;

/**
 * @Description: 好友项框架
 * 用JToggle来写或许能解决事件传递的问题
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class FriendItem extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5772102618380608802L;
	
	private static final Color friendContainerBackColor = new Color(252,240,193);
	
	private boolean hover = false;
	private boolean selected = false;
	private GradientPaint gradientPaint;
	private static final Color startColor = new Color(252,236,175);
	private static final Color endColor = new Color(252,233,158);
	{
    	setLayout(new FlowLayout(FlowLayout.LEFT, 7, 7));
    	setOpaque(false);
		setBackground(friendContainerBackColor);
    	setPreferredSize(new Dimension(0, 53));
    	addMouseListener(new MouseAdapter(){
    		public void mouseEntered(MouseEvent e){
    			hover = true;
    			repaint();   			
    		}
    		public void mouseExited(MouseEvent e){
    			hover = false;
    			repaint();
    		}
    	});
    	addMouseListener(mutualExclusion);
	}
	
	//为了达成互斥
	private static final MouseAdapter mutualExclusion = new MouseAdapter(){
		
		byte[] img;
		{
			  ImageIcon ima = new ImageIcon("Image\\Head\\1.png");
			  BufferedImage bu = new BufferedImage(ima.getIconWidth(), ima
			    .getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		        Graphics2D graphics = bu.createGraphics();
		        graphics.drawImage(ima.getImage(), 0, 0, null);
			  ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
			  try {
			   ImageIO.write(bu, "png", imageStream);
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
			  img = imageStream.toByteArray();
		}
		private FriendItem lastItem = new FriendItem(new FriendItemModel(img,
				null,
				null,
				false,
				null,
				null));
		public void mousePressed(MouseEvent e){
			FriendItem pressItem = (FriendItem) e.getSource();
			if(pressItem == lastItem){
				return;
			}
			pressItem.selected = true;
			pressItem.hover = false;
			pressItem.repaint();
			lastItem.selected = false;
			lastItem.repaint();
			lastItem = pressItem;
		} 		 
	};
	
	protected void paintComponent(Graphics g){
		if(selected){
			int width = getWidth();
			int height = getHeight();
			Graphics2D g2d = (Graphics2D)g;
			gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
			g2d.setPaint(gradientPaint);
			g2d.fillRect(0, 0, width, height);
		}else if(hover){
			g.setColor(friendContainerBackColor);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		super.paintComponent(g);
	}
	
	//模型
	private FriendItemModel model;
	//头像
	private JLabel headPanel;
	//分层
	private JPanel createCenterLayout;
	//第一层
	private JPanel firstLayer;
	//第二层
	private JPanel SecondLayer;	
	//备注
	private JLabel remarks;
	//昵称
	private JLabel nickName;
	//查看空间按钮
	private SenioButton seeZone;
	//个性签名
	private JLabel signature;
	//构建
	public FriendItem(FriendItemModel model){
		
		this.model = model;
		//头像
		JPanel pane11 = new JPanel(new BorderLayout());
		pane11.setOpaque(false);
		add(pane11);
		headPanel = new JLabel();
		headPanel.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(model.getHead()),
				40,
				40,
	    		5));
		pane11.add(headPanel, "West");
		
		//用于头像之后组件的布局
		createCenterLayout = new JPanel(new GridLayout(2, 1));
		createCenterLayout.setOpaque(false);
		createCenterLayout.addMouseListener(util);
		add(createCenterLayout, "Center");
		//第一层
		firstLayer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		firstLayer.setOpaque(false);
		firstLayer.addMouseListener(util);
		createCenterLayout.add(firstLayer);	
		//备注
		if(model.getRemarks()!=null){
			remarks = new JLabel(model.getRemarks());
			remarks.setFont(Fonts.MicrosoftAccor15);
			firstLayer.add(remarks);
			//昵称	
			nickName = new JLabel(model.getNickName());
			nickName.setFont(Fonts.MicrosoftAccor11);
			nickName.setForeground(Colors.greyColor);
			firstLayer.add(nickName);
		}else{
			nickName = new JLabel();
			nickName.setFont(Fonts.MicrosoftAccor15);
			firstLayer.add(nickName);
		}
		//第二层
		SecondLayer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		SecondLayer.setOpaque(false);
		SecondLayer.addMouseListener(util);
		createCenterLayout.add(SecondLayer);
		//空间有更新
		if(model.isNewMessage()){
			seeZone = new SenioButton(new ImageIcon("Image\\AppPluginIcon\\qzoneicon.png"));
			seeZone.setSendEvent(true);			
			SecondLayer.add(seeZone);
		}
		signature = new JLabel(model.getSignature());
		signature.setFont(Fonts.MicrosoftAccor11);
		signature.setForeground(Colors.greyColor);
		SecondLayer.add(signature);
	}
	//用于事件传递
	private MouseAdapter util = new MouseAdapter(){
		public void mouseEntered(MouseEvent e){
			sendEvent(e);
		}
		public void mouseExited(MouseEvent e){
			sendEvent(e);
		}
		public void mouseClicked(MouseEvent e){
			sendEvent(e);
		}
		public void mousePressed(MouseEvent e){
			sendEvent(e);
		}
		private void sendEvent(MouseEvent e){
			Component source = (Component) e.getSource();
			Container father = source.getParent();
			MouseEvent e1 = SwingUtilities.convertMouseEvent(source, e, father);
			father.dispatchEvent(e1);
		}
	};
	public void addToFirstLayer(Component com){
		firstLayer.add(com);
	}	
	public void addToSecondLayer(Component com){
		SecondLayer.add(com);
	}	
	//提供头像设置方法
	public void setHead(Icon head){
		headPanel.setIcon(head);
	}
	public Icon getHead(){
		return headPanel.getIcon();
	}
	//返回该好友号码
	public String getNo(){
		return model.getNO();
	}
	//好友姓名
	public String getName(){
		return nickName.getText();
	}
	//model
	public FriendItemModel getModel() {
		return model;
	}
	public void setModel(FriendItemModel model) {
		this.model = model;
	}
}
