package org.myoggradio;

import java.awt.Color;
import java.awt.Component;

import javax.mail.Message;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyCellRenderer extends JLabel implements ListCellRenderer<Message>
{
	private static final long serialVersionUID = 1L;
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Message> arg0, 
			Message arg1, int arg2, 
			boolean arg3,
			boolean arg4) 
	{
		String text = "Unkown";
		try
		{
			text = arg1.getSubject();
		}
		catch (Exception e)
		{
			text = e.toString();
		}
		this.setText(text);
		int selected = arg0.getSelectedIndex();
		if (selected == arg2) this.setForeground(Color.RED);
		if (selected != arg2) this.setForeground(Color.BLACK);
		return this;
	}

}
