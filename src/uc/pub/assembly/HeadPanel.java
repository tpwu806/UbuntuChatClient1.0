package uc.pub.assembly;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class HeadPanel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6127751628415613865L;
	
	private ImageIcon border = new ImageIcon("Image\\client\\login's\\login_head_bkg.png");
	
	public void paintComponent(Graphics g){	
		
		super.paintComponent(g);
		
		if(border!=null){
			g.drawImage(border.getImage(), 
					0, 
					0, 
					getWidth(), 
					getHeight(), 
					null);	
		}			
	}
	
	public void setHeadBorder(ImageIcon border){
		this.border = border;
	}
}
