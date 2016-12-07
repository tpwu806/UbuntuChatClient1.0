package uc.pub.assembly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class JDataWindow {
	// 弄好节点，之后做GroupLayout
	protected final static ImageIcon arrow = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_collapseTexture.png");
	protected final static ImageIcon arrowHover = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_collapseTextureHighlight.png");
	protected final static ImageIcon arrowSelected = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_expandTexture.png");
	protected final static ImageIcon arrowSelectedHover = new ImageIcon(
			"Image\\use\\MainPanel_FolderNode_expandTextureHighlight.png");
	/*
	 * 按钮专用的监听器
	 */
	protected ActionListener actionAdapter = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			/*if(e.getSource() == closeButton){				
				System.exit(0);				
			}else if(e.getSource() == minimizationButton){				
				System.out.println("最小化");
				outSetExtendedState(JFrame.ICONIFIED);	
			}else if(e.getSource() == friendsTab){
				System.out.println("friendsTab");
				cardLayout.show(forCard, "friendsTab");
			}else if(e.getSource() == groupTab){
				System.out.println("groupTab");
				cardLayout.show(forCard, "groupTab");
			}else if(e.getSource() == conversationTab){
				System.out.println("conversationTab");
				cardLayout.show(forCard, "conversationTab");
			}*/
		}		
	};
	//节点的点击监听器
	protected ActionListener nodeActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JToggleButton pressButton = (JToggleButton) e.getSource();
			pressButton.setRolloverIcon(null);
			if(pressButton.getModel().isSelected()){
				//openEffect(pressButton);
			}else{
				//closeEffect(pressButton);
			}
		}
	};
	//节点ENTER键的监听器
	protected KeyAdapter nodeKeyAdapter = new KeyAdapter(){
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				JToggleButton pressButton = (JToggleButton) e.getSource();
				pressButton.setRolloverIcon(null);
				if(pressButton.getModel().isSelected()){
					//closeEffect(pressButton);
					pressButton.setSelected(false);
				}else{
					//openEffect(pressButton);
					pressButton.setSelected(true);
				}
			}
		}
	};
	//节点的焦点监听器
	protected FocusListener nodeFocusListener = new FocusListener(){
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e){
			final JToggleButton pressButton = (JToggleButton) e.getSource();
			if(pressButton.getModel().isSelected()){
				Thread t = new Thread(){
					public void run(){
						//pressButton.setSelectedIcon(arrowSelected);
						pressButton.setRolloverSelectedIcon(arrowSelectedHover);
					}
				};
				t.start();
			}else{
				Thread t = new Thread(){
					public void run(){
						//pressButton.setIcon(arrow);
						//pressButton.setRolloverIcon(arrowHover);
					}
				};
				t.start();
			}				
		}
	};

	//双击创建聊天面板
	protected MouseAdapter friendItemMouseAdapter = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			if (e.getClickCount() == 2) {
				//ActionFriendList(e);
			}
		}
	};
	//双击创建聊天面板
	protected MouseAdapter groupItemMouseAdapter = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			if (e.getClickCount() == 2) {
				//ActionGroupList(e);
			}
		}		
	};
}
