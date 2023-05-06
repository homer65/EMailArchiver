package org.myoggradio;

import javax.mail.Message;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyListSelectionListener implements ListSelectionListener
{
	private JList<Message> list = null;
	public MyListSelectionListener(JList<Message> list)
	{
		this.list = list;
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) 
	{
		if (!lse.getValueIsAdjusting())
		{
			Message msg = list.getSelectedValue();
			EineMessageMenu emm = new EineMessageMenu(msg);
			emm.anzeigen();
		}
	}
}
