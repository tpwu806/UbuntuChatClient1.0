package uc.pub.assembly;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

/**
 * @Description: 圆角Frame
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class FillitFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4238819591067495300L;
	
	//拖动所需
	private boolean startDrag = true;
	private Point oldposition = null;
	private MouseAdapter mouseAdapter;
	
	public FillitFrame(int width, int height){
		super();
		setSize(width, height);
		setUndecorated(true);
		setLocationRelativeTo(null);
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0.0D, 0.0D, getWidth(),  
				getHeight(), 11.0D, 11.0D));
		canDrag();
		setAlwaysOnTop(true);
	}
	
	//提供自定义圆角度数
	public FillitFrame(int width, int height, double arcWidth, double arcHeight){
		super();
		setSize(width, height);
		setUndecorated(true);
		setLocationRelativeTo(null);
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0.0D, 0.0D, getWidth(),  
				getHeight(), arcWidth, arcHeight));
		canDrag();
		setAlwaysOnTop(true);
	}
	
	//提供是否可拖动的设置
	public void isCandrag(boolean b){
		
		if(mouseAdapter != null){
			if(b){
				canDrag();
			}else{
				this.removeMouseListener(mouseAdapter);
				this.removeMouseMotionListener(mouseAdapter);
				mouseAdapter = null;
				oldposition = null;
			}
		}
	}
	
	/*
	 * 实现窗口拖动
	 * 实例化鼠标监听器为顶级窗口添加鼠标监听器
	 */
	private void canDrag() {
		mouseAdapter = new MouseAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
				if(startDrag){
					oldposition = e.getPoint(); 
					startDrag = false;
				}
		        Point newposition = e.getPoint();
		        setLocation(getX()+(newposition.x-oldposition.x), getY()+(newposition.y-oldposition.y));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				startDrag = true;
			}
		};
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
	}	
}
