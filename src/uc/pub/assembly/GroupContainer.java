package uc.pub.assembly;

import javax.swing.*;

import uc.pub.tool.VerlicelColumn;

import java.awt.*;

/**
 * @Description: 包含一组按钮的面板
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class GroupContainer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4682584157233015966L;
	
	//节点/展开按钮
	private JToggleButton node;
	//好友列表
	private JPanel friendList;
	
	{
		setLayout(new BorderLayout());
		setOpaque(false);
	}
	
	public GroupContainer(JToggleButton node){
		this.node = node;
		add(this.node, "North");
		
		friendList = new JPanel(new VerlicelColumn());
		friendList.setOpaque(false);
		friendList.setVisible(false);
		add(friendList, "Center");
	}

    /**
     * 取得组的标题按钮
     * 
     * @return JToggleButton
     */
    public JToggleButton getTitleButton() {
        return node;
    }

    /**
     * 收缩组
     */
    public void collapse() {
    	friendList.setVisible(false);
        this.revalidate();
    }

    /**
     * 展开组
     */
    public void expand() {
        friendList.setVisible(true);
        this.revalidate();
    }

    /**
     * 取得组名
     * 
     * @return String
     */
    public String getName() {
        return node.getText();
    }

    /**
     * 添加一个成员组件
     * 
     * @param index
     *            int 顺序号
     * @param c
     *            Component 成员组件
     */
    public void addMember(int index, Component c) {
//    	if(friendList == null){
//    		friendList = new JPanel(new VerlicelColumn(1));
//    		friendList.setOpaque(false);
//    		friendList.setVisible(false);
//    		add(friendList, "Center");
//    	}
        friendList.add(c, index);
        friendList.doLayout();
    }
    
    public void addMember(Component c) {
//    	if(friendList == null){
//    		friendList = new JPanel(new VerlicelColumn(1));
//    		friendList.setOpaque(false);
//    		friendList.setVisible(false);
//    		add(friendList, "Center");
//    	}
    	friendList.add(c);
    	friendList.doLayout();
    }
    
    /**
     * 返回List中包含的所有成员
     */
    public Component[] getMembers(){    	
    	return friendList.getComponents();
    }
    /**
     * 返回List中包含的成员
     */
    public Component getMembers(int index){
    	return friendList.getComponent(index);
    }
    /**
     * 返回List中包含的成员数
     */
    public int getMemberCount(){
    	return friendList.getComponentCount();
    }
}
