package uc.pub.assembly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import uc.common.dto.StateEnum;

/**
 * @Description: 状态选择按钮
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class StateButton extends JButton{	
  /**
	 * 
	 */
	private static final long serialVersionUID = -1518775487740923190L;
	
	private ImageIcon icon;
	private StateEnum state;
	
	public StateButton(){
		setContentAreaFilled(false);
		setFocusPainted(false);
	}
	
	protected void paintComponent(Graphics g) {
		
		  ButtonModel model = getModel();
		  //由于不知道点击的时候的渐变色是多少 所以就和 悬停一样了
	      if (model.isArmed()) {
	    	  
	    	  Graphics2D g2d = (Graphics2D) g;

	    	  int width  = getWidth();
	    	  int height  = getHeight();
			  LinearGradientPaint p;

			  p = new LinearGradientPaint(0.0f, 0.0f, 0.0f, 20.0f,
						new float[] { 0.0f, 0.5f, 0.501f, 1.0f }, new Color[] {
								new Color(255,255,255,200), new Color(255,255,255,0),
								new Color(255,255,255,0), new Color(255,255,255,200) });
			  g2d.setPaint(p);
			  g2d.fillRoundRect(1, 1, width, height, 1, 1);			  
	    	  g.drawImage(icon.getImage(), 4, 4, 11, 11, null);
	    	  
	      } else if (model.isRollover()) {

	    	  Graphics2D g2d = (Graphics2D) g;
	    	  int width  = getWidth();
	    	  int height  = getHeight();
	    	  
			  Paint oldPaint = g2d.getPaint();			  
			  LinearGradientPaint p;
			  p = new LinearGradientPaint(0.0f, 0.0f, 0.0f, 20.0f,
						new float[] { 0.0f, 0.5f, 0.501f, 1.0f }, new Color[] {
								new Color(255,255,255,200), new Color(255,255,255,0),
								new Color(255,255,255,0), new Color(255,255,255,200) });
			  g2d.setPaint(p);
			  g2d.fillRoundRect(1, 1, width, height, 1, 1);
			  g.setColor(new Color(255, 255, 255, 200));
			  //画出高光部分
			  g.drawRoundRect(1, 1, width, height, 1,
						1);
			  g2d.setPaint(oldPaint);
	    	  g.drawImage(icon.getImage(), 3, 3, 11, 11, null);
	    	  
	      } else {
	    	  g.drawImage(icon.getImage(), 3, 3, 11, 11, null);
	      }
	  }
	
	public void paintBorder(Graphics g){
		
		ButtonModel model = getModel();
		if(model.isRollover() || model.isRollover()){
			g.setColor(new Color(0, 0, 0, 65));
			g.drawRoundRect(0, 0, getSize().width-1, getSize().height-1, 1,
					1);
		}				
	}
	
	public void setIcon(Icon icon){
		this.icon = (ImageIcon) icon;
	}

	public StateEnum getState(){
		return state;
	}

	public void setState(StateEnum state){
		this.state = state;
	}
}

