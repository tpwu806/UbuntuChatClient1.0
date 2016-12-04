package uc.pub.assembly;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JToggleButton;

/**
 * @Description: 好友、群、聊天记录
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class Tab extends JToggleButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7968357328552808841L;

	private static final Color startColor = new Color(255,255,255,0);
	private static final Color pressSendColor = new Color(255,255,255,235);
	private static final Color hoverSendColor = new Color(255,255,255,120);
	
	private ButtonModel myModel;
	{
		setContentAreaFilled(false);
		setFocusPainted(false);
		myModel = getModel();
	}
	
	public Tab(Icon imageIcon) {
		super(imageIcon);
	}

	public Tab(Icon imageIcon, boolean b) {
		super(imageIcon, b);
	}

	public void paintComponent(Graphics g){
		if(myModel.isSelected()){
			int width = getWidth();
			int height = getHeight();
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(new GradientPaint(0, 0, startColor, 0, height, pressSendColor));
			g2d.fillRect(0, 0, width, height);
		}else if(myModel.isRollover()){
			int width = getWidth();
			int height = getHeight();
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(new GradientPaint(0, 0, startColor, 0, height, hoverSendColor));
			g2d.fillRect(0, 0, width, height);
		}
		super.paintComponent(g);
	}
}
