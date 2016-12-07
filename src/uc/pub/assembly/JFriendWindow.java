package uc.pub.assembly;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import uc.common.FriendGroupModel;
import uc.common.FriendItemModel;

public class JFriendWindow extends JDataWindow {
	CardLayout cardLayout;
	JPanel forCard;
	JScrollPane scrollPane1;
	JPanel friendPanel;
	
	
	public void initUI(ArrayList<FriendGroupModel> groups){
		cardLayout = new CardLayout();
		forCard = new JPanel(cardLayout);
		scrollPane1 = new JScrollPane(forCard,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		friendPanel = new JPanel(new VerlicelColumn(5)){
			/**
			 * 
			 */
			private static final long serialVersionUID = 2549744010310864813L;
			public void paintComponent(Graphics g){
				g.setColor(new Color(255,255,255,235));
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		friendPanel.setOpaque(false);
		forCard.add(friendPanel);
		
		//groups = user.getFriendList();
		for(FriendGroupModel group : groups){
			GroupContainer gropContainer = createGroupNode(group.getGroupName());
			int length = group.size();
			for(int i=0; i<length; i++){
				FriendItemModel friendModel = group.get(i);	
				FriendItem friend = new FriendItem(friendModel);
				gropContainer.addMember(friend);
				//friend.addMouseListener(friendItemMouseAdapter);
			}
			friendPanel.add(gropContainer);
		}
	}
	
	//添加节点
	private GroupContainer createGroupNode(String name){
		final JToggleButton node = new JToggleButton(name,
				super.arrow){
			/**
			 * 
			 */
			private static final long serialVersionUID = 392923895832778450L;
			private ButtonModel buttonModel = getModel();
			private final Color startColor = new Color(255,255,255,10);
			private final Color endColor = new Color(49,143,197,35);
			public void paintComponent(Graphics g){
				if(buttonModel.isRollover() && !isFocusOwner()){
					  int width = getWidth();
					  int height = getHeight();
					Graphics2D g2d = (Graphics2D)g;
				    g2d.setPaint(new GradientPaint(0, 0, startColor, 0, height, endColor));
					g2d.fillRect(0, 0, width, height);
				}
				super.paintComponent(g);
			}
		};
		node.setRolloverIcon(arrowHover);
		
		node.setContentAreaFilled(false);
		node.setOpaque(false);
		node.setFocusPainted(false);
		node.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));	
		node.setPreferredSize(new Dimension(0, 25));
		node.setHorizontalAlignment(SwingConstants.LEFT);
		node.addActionListener(super.nodeActionListener);
		node.addKeyListener(nodeKeyAdapter);
		node.addFocusListener(nodeFocusListener);
		
		return new GroupContainer(node);
	}

}
