package uc.pub.assembly;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import uc.common.StateEnum;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class StateItem extends JMenuItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 50758049687381612L;

	private StateEnum state;
	
	public StateItem(StateEnum state){
		
		this.state = state;
		confirmation();
	}
	
	//返回给选项所代表的的状态
	public StateEnum getState(){		
		return state;
	}
	
	//考虑到如果会有手动设置的情况所以就添加了这个方法
	public void setState(StateEnum state){
		this.state = state;
		confirmation();
	}

	//确定 选项按钮代表的值
	public void confirmation(){

		switch(state){ 
		//代表在线
		case ONLINE:
			setText("我在线上");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\imonline.png"));
			break;
		//代表Q我吧
		case QME:
			setText("Q我吧");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\Qme.png"));
			break;
		//代表离开
		case LEAVE:
			setText("离开");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\leave.png"));
			break;
		//代表忙碌
		case BEBUSY:
			setText("忙碌");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\busy.png"));
			break;
		//代表请勿打扰
		case DONTDISTURB:
			setText("请勿打扰");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\dontDisturb.png"));
			break;
		//代表隐身
		case INVISIBLE:
			setText("隐身");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\invisible.png"));
			break;
		}
	}


	public StateItem(String string, ImageIcon imageIcon) {
		super(string, imageIcon);
	}
	
	public StateItem(){
		super();
	}
}
