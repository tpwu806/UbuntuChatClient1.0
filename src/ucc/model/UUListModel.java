package ucc.model;

import java.util.Vector;

import javax.swing.AbstractListModel;

public class UUListModel extends AbstractListModel{
	
	private Vector vs;
	
	public UUListModel(Vector vs){
		this.vs = vs;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return vs.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return vs.size();
	}
	
}
