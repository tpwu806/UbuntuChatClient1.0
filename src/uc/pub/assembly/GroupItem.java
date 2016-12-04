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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import uc.common.GroupModel;
import uc.pub.tool.ChangeImage;
import uc.pub.tool.Fonts;

/**
 * @Description:群项框架 
 * @author wutp 2016年12月4日
 * @version 1.0
 */
public class GroupItem extends JPanel{
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
	private GroupModel model;
	//头像
	private JLabel headPanel;
	//昵称
	private JLabel gname;


	
	//构建
	public GroupItem(GroupModel model){
		
		this.model = model;
		
		JPanel pane11 = new JPanel(new BorderLayout());
		pane11.setOpaque(false);
		add(pane11);
		//群头像
		headPanel = new JLabel();
		byte[] head = model.getPicture();
		headPanel.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(head),
				30,
				30,
	    		5));
		pane11.add(headPanel, "West");		
		//群名称
		gname = new JLabel(this.model.getGroupName());
		gname.setFont(Fonts.MicrosoftAccor15);
		add(gname, "Center");
		
	}

	//提供头像设置方法
	public void setHead(Icon head){
		headPanel.setIcon(head);
	}
	public Icon getHead(){
		return headPanel.getIcon();
	}
	//返回该好友号码
	public String getGid(){
		return model.getGid();
	}
	//好友姓名
	public String getGname(){
		return gname.getText();
	}
	//model
	public GroupModel getModel() {
		return model;
	}
	public void setModel(GroupModel model) {
		this.model = model;
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
		private GroupItem lastItem = new GroupItem(new GroupModel(null, 
				null, 
				null, 
				img, 
				null, 
				null));
		public void mousePressed(MouseEvent e){
			GroupItem pressItem = (GroupItem) e.getSource();
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
}
