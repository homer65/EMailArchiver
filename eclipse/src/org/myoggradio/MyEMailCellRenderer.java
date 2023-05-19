package org.myoggradio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyEMailCellRenderer extends JLabel implements ListCellRenderer<SatzEMail>
{
	private static final long serialVersionUID = 1L;
	@Override
	public Component getListCellRendererComponent(
			JList<? extends SatzEMail> arg0, 
			SatzEMail arg1, int arg2, 
			boolean arg3,
			boolean arg4) 
	{
		String text = "Unkown";
		text = arg1.getSubject();
		this.setText(text);
		int selected = arg0.getSelectedIndex();
		if (selected == arg2) this.setForeground(Color.RED);
		if (selected != arg2) this.setForeground(Color.BLACK);
		return this;
	}
}
