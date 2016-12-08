package uc.pub.assembly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class TDocumentListener implements DocumentListener {
	public void insertUpdate(DocumentEvent e){}
    
    public void removeUpdate(DocumentEvent e){}
   
    public void changedUpdate(DocumentEvent e){}

}
