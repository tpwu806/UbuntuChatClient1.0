package uc.pub.assembly;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class SenioButton extends JLabel{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1518775487740923190L;
	
	//控制是否显示特有边框
	private boolean showBorder = true;
	//控制是否显示悬停状态
	private boolean hover;
	//控制是否显示点击状态
	private boolean press;
	//三个状态下的颜色：默认情况下 press没有颜色,你可以自行设置
	private LinearGradientPaint hoverPaint;
	private LinearGradientPaint pressPaint;
	private int roundSize = 4;
	//是否传递事件
	private boolean sendEvent = true;
	
	//默认状态的构造器
	public SenioButton(){
		super();
		addDefaultStateChanger();
	}	
	public SenioButton(Icon icon){
		super(icon);
		addDefaultStateChanger();
	}	
	public SenioButton(Icon icon, int horizontalAlignment, boolean showBorder){
		super(icon, horizontalAlignment);
		addDefaultStateChanger();
		this.showBorder = showBorder;
	}
	public SenioButton(String text, Icon icon, int horizontalAlignment){
		super(text, icon, horizontalAlignment);
		addDefaultStateChanger();
	}
	public SenioButton(String text, Icon icon, int horizontalAlignment, boolean showBorder){
		super(text, icon, horizontalAlignment);
		addDefaultStateChanger();
		this.showBorder = showBorder;
	}
	
	//拥有展开按钮的构造器
	public SenioButton(Icon icon, boolean hasOpenButton){
		super(icon);
		if(hasOpenButton){
			createOpenButton(new ImageIcon("Image\\ChatFrame\\aio_toolbar_arrow.png"));
		}
	}	
	public SenioButton(Icon icon, Icon icon2){
		super(icon);
		createOpenButton(icon2);
	}	
	
	protected void paintComponent(Graphics g) {
		  //因为不知道 画笔是否是会改变的、所以就只好每次都捕获一次了
		// 坐标的话、因为不知道什么时候才确定的 组件大小 所以就不提出去了， 不然的话 将会提升效率吧
		  if(press){
	    	  
	      }else if(hover){
	    	  Graphics2D g2d = (Graphics2D) g;
	          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                  RenderingHints.VALUE_ANTIALIAS_ON);
	    	  int width  = getWidth();
	    	  int height  = getHeight();
	    	  
	    	  if(hoverPaint==null){
	    			hoverPaint = new LinearGradientPaint(0.0f, 0.0f, 0.0f, this.getWidth(),
	    					new float[] { 0.0f, 0.35f, 0.70f, 1.0f }, new Color[] {
	    							new Color(255,255,255,150), new Color(255,255,255,100),
	    							new Color(255,255,255,70), new Color(255,255,255,30)});
	    	  }
			  g2d.setPaint(hoverPaint);
			  g2d.fillRoundRect(1, 1, width-1, height-1, roundSize, roundSize); 	  
	      }
		  super.paintComponent(g);
	  }
	
	public void paintBorder(Graphics g){
		super.paintBorder(g);
		if(showBorder){
			if(hover || press){
		    	  Graphics2D g2d = (Graphics2D) g;
		          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                  RenderingHints.VALUE_ANTIALIAS_ON);
		          g2d.setColor(new Color(0, 0, 0, 100));
		          g2d.drawRoundRect(0, 0, getSize().width-1, getSize().height-1, roundSize+1,
		        		  roundSize+1);
		          g2d.setColor(new Color(255, 255, 255, 150));
		          g2d.drawRoundRect(1, 1, getSize().width-3, getSize().height-3, roundSize,
		        		  roundSize);
			}	
		}			
	}
	
	//请求显示悬停状态、状态的显示是通过调用的，这意味着你可以手动的解除本组件原先的监听器，添加上自己的监听器
	//来显示不同的效果
	public void showHover(){
		hover = true;
		repaint();
	}
	public void dontShowHover(){
		hover = false;
		repaint();
	}
	//请求显示点击状态
	public void showPress(){
		press = true;
		repaint();
	}	
	public void dontShowPress(){
		press = false;
		repaint();
	}
	
	//设置是否显示本组件特有的边框、你可以而外添加边框
	public void setShowBorder(boolean show){
		showBorder = show;
	}
	
	public LinearGradientPaint getHoverPaint() {
		return hoverPaint;
	}
	public LinearGradientPaint getPressPaint() {
		return pressPaint;
	}
	public void setHoverPaint(LinearGradientPaint hoverPaint) {
		this.hoverPaint = hoverPaint;
	}
	public void setPressPaint(LinearGradientPaint pressPaint) {
		this.pressPaint = pressPaint;
	}

	//支持设置圆角度数
	public void setRoundSize(int roundSize) {
		this.roundSize = roundSize;
	}
	public int getRoundSize() {
		return roundSize;
	}
	//设置是否向上传递事件
	public void setSendEvent(boolean b){
		sendEvent = b;
	}
	
	//解除默认监听器
	public void removeDefaultStateChanger(){
		removeMouseListener(defaultStateChanger);
		defaultStateChanger = null;
	}
	
	//展开按钮
	protected SenioButton OpenButton;
	//提供给外部的添加
	public void addOpenButton(){
		createOpenButton(new ImageIcon("Image\\ChatFrame\\aio_toolbar_arrow.png"));
	}
	public void addOpenButton(Icon icon){
		createOpenButton(icon);
	}
	//添加展开按钮
	protected void createOpenButton(Icon icon){
		
		OpenButton = new SenioButton(icon);
		OpenButton.setPreferredSize(new Dimension(16, getPreferredSize().height));
		OpenButton.setShowBorder(false);
		
		setLayout(new BorderLayout());
		add(OpenButton, "East");			
	}
	//为展开按钮添加扩展监听
	public void openButtonAddMouseListener(MouseListener l){
		OpenButton.addMouseListener(l);
	}
	
	//默认状态下的状态更改器
	protected MouseAdapter defaultStateChanger;
	//添加状态更改器
	protected void addDefaultStateChanger(){
		defaultStateChanger = new MouseAdapter(){
			
			public void mouseEntered(MouseEvent e) {				
				showHover();
				if(sendEvent){
					sendEvent(e);
				}
			}
			public void mouseExited(MouseEvent e) {
				dontShowHover();
				if(sendEvent){
					sendEvent(e);
				}
			}
			public void mousePressed(MouseEvent e) {
				showPress();
			}
			public void mouseReleased(MouseEvent e) {
				dontShowPress();
			}
			public void sendEvent(MouseEvent e){
				Component source = (Component) e.getSource();
				Container father = source.getParent();
				MouseEvent e1 = SwingUtilities.convertMouseEvent(source, e, father);
				father.dispatchEvent(e1);
			}
		};
		addMouseListener(defaultStateChanger);
	}
}