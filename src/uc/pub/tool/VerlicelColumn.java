package uc.pub.tool;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class VerlicelColumn implements LayoutManager, java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int vgap = 0;

    public VerlicelColumn() {
    }

    public VerlicelColumn(int vg) {
        this.vgap = vg;
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    //确定布局的首选大小    
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            int ncomponents = parent.getComponentCount();
            int h = 0;
            
            for (int i = 0; i < ncomponents; i++) {
                Component comp = parent.getComponent(i);
                h += comp.getPreferredSize().height + vgap;
            }
            return new Dimension(parent.getWidth(), h + 2 * vgap);
        }
    }

    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    //该方法设置了所包含的组件的位置和大小
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int ncomponents = parent.getComponentCount();
            if (ncomponents == 0) {
                return;
            }
            
            // y 的值就是各子组件的上下间距
            int y = vgap;
            for (int c = 0; c < ncomponents; c++) {
                int h = parent.getComponent(c).getPreferredSize().height;
                parent.getComponent(c).setBounds(
                        0,
                        y,
                        parent.getWidth(),
                        h);
                y += h + vgap;
            }
        }
    }
}

