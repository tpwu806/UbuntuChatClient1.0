package uc.dof;

import java.util.Vector;

import javax.swing.AbstractListModel;

/**
 * @Description: �����б�
 * @author wutp 2016��10��15��
 * @version 1.0
 */
public class OnlineListModel extends AbstractListModel<Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<?> vs;
	
	public OnlineListModel(Vector<?> vs){
		this.vs = vs;
	}

	@Override
	public Object getElementAt(int index) {
		return vs.get(index);
	}

	@Override
	public int getSize() {
		return vs.size();
	}
	
}
