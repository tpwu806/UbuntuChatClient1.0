package uc.pub.assembly;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @Description: 用于替代  类库中 按钮获取焦点时按下ENTER键自动响应的问题
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class MyButton extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2635812551775218654L;
	
	private Icon img = null;
	private Icon hoverimg = null;
	private Icon pressimg = null;
	
	public MyButton(){
		super();
	}
	public MyButton(Icon icon){
		super(icon);
		addMouseAdapter();
		img = icon;
	}
	public MyButton(String s){
		super(s);
	}
	
	public void setPressedIcon(Icon icon){
		pressimg = icon;
	}
	public void setRolloverIcon(Icon icon){
		hoverimg = icon;
	}
	
	private void addMouseAdapter(){
		MouseAdapter mouseAdapter = new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				setIcon(hoverimg);
			}
			public void mouseExited(MouseEvent e) {
				setIcon(img);
			}
			public void mousePressed(MouseEvent e) {
				setIcon(pressimg);
			}
			public void mouseReleased(MouseEvent e) {
				setIcon(img);
			}
		};
		addMouseListener(mouseAdapter);
	}
	//由于点击最小化窗口的时候、鼠标松开事件捕捉不到所以需要手动将按钮设为初始状态
	public void initialState(){
		setIcon(img);
	}
}
