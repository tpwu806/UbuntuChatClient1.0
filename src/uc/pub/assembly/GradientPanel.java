package uc.pub.assembly;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class GradientPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4152468688678649960L;

	private Color startColor = Color.white;
	private Color endColor = Color.white;
	private boolean isGradient = true;
	private boolean Longitudinal = true;
	
	public GradientPanel(){
		super();
	}
	
	public GradientPanel(LayoutManager arg0){
		super(arg0);
	}
	
	public GradientPanel(Color startColor, Color endColor) {
		this.startColor = startColor;
		this.endColor = endColor;
	}
	
	public GradientPanel(LayoutManager arg0, Color startColor, Color endColor) {
		super(arg0);
		this.startColor = startColor;
		this.endColor = endColor;
	}

	public void paintComponent(Graphics g){
		
		  super.paintComponent(g);		  
		  //添加以判断以换取向上转型的能力
		  if(isGradient){
			  int width = getWidth();
			  int height = getHeight();
			  Graphics2D g2d = (Graphics2D) g;
			  if(Longitudinal){
				  g2d.setPaint(new GradientPaint(0, 0, startColor, width, 0, endColor));
			  }else{
				  g2d.setPaint(new GradientPaint(0, 0, startColor, 0, height, endColor));
			  }
			  g2d.fillRect(0, 0, width, height);
		  }
	}

	public Color getStartColor() {
		return startColor;
	}

	public Color getEndColor() {
		return endColor;
	}

	public boolean isGradient() {
		return isGradient;
	}

	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

	public void setEndColor(Color endColor) {
		this.endColor = endColor;
	}

	//提供支持单色的，好使组件可以做出更多的更改、迎合各种情况
	public void setGradientColor(Color color){
		this.startColor = color;
		this.endColor = color;
	}
	//设置渐变色
	public void setGradientColor(Color startColor, Color endColor){
		this.startColor = startColor;
		this.endColor = endColor;
	}
	//设置是否启用渐变色
	public void setGradient(boolean isGradient) {
		this.isGradient = isGradient;
	}

	public boolean isLongitudinal() {
		return Longitudinal;
	}
	//设置方向
	public void setLongitudinal(boolean longitudinal) {
		Longitudinal = longitudinal;
	}
}
