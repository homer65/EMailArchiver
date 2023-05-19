package org.myoggradio;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyEMailListSelectionListener implements ListSelectionListener
{
	private JList<SatzEMail> list = null;
	public MyEMailListSelectionListener(JList<SatzEMail> list)
	{
		this.list = list;
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) 
	{
		if (!lse.getValueIsAdjusting())
		{
			Long id = list.getSelectedValue().getSend();
			EineEMailMenu eem = new EineEMailMenu(id);
			eem.anzeigen();
		}
	}
}