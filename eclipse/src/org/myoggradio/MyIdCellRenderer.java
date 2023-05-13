package org.myoggradio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyIdCellRenderer extends JLabel implements ListCellRenderer<Long>
{
	private static final long serialVersionUID = 1L;
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Long> arg0, 
			Long arg1, int arg2, 
			boolean arg3,
			boolean arg4) 
	{
		String text = "Unkown";
		try
		{
			Postgres postgres = new Postgres();
			SatzEMail satz = postgres.getOneEMail(arg1);
			text = satz.getSubject();
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
