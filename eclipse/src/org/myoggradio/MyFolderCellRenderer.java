package org.myoggradio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import com.sun.mail.imap.IMAPFolder;

public class MyFolderCellRenderer extends JLabel implements ListCellRenderer<IMAPFolder>
{
	private static final long serialVersionUID = 1L;
	@Override
	public Component getListCellRendererComponent(
			JList<? extends IMAPFolder> arg0, 
			IMAPFolder arg1, int arg2, 
			boolean arg3,
			boolean arg4) 
	{
		String text = "Unkown";
		try
		{
			text = arg1.getURLName().toString();
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
