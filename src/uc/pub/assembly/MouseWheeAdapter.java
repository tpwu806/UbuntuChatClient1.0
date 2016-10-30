package uc.pub.assembly;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JScrollBar;

/**
 * @Description: 实现滚动条的齿轮滚动
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class MouseWheeAdapter implements MouseWheelListener {
	
	protected JScrollBar scrollBar = null;
	
	/**
	 * @param JScrollBar 要监听的 JScrollBar description
	 */
	public MouseWheeAdapter(JScrollBar jScrollBar){
		this.scrollBar = jScrollBar;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		//为了防止会有其他方法更改了滚动条的位置，所以每次都重新获取滚动条位置
	    int position = scrollBar.getValue();
	    //向上滚动为负数 向下为正， 因此直接获取滚动值 相加即可确定滚动后位置
	    int count = e.getWheelRotation();
	    position += count;
	    
	    //判断是否滚动的超过了边界，超出了就将位置设置为边界
	    if (position<0) {
	    	
	    	position = 0;
	    	return;
	    }else if (position > 130) {
	    	
	    	position = 130;
	    	return;
	    }
		scrollBar.setValue(position);
	}	
}
