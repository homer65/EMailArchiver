package org.myoggradio;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyIdListSelectionListener implements ListSelectionListener
{
	private JList<Long> list = null;
	public MyIdListSelectionListener(JList<Long> list)
	{
		this.list = list;
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) 
	{
		if (!lse.getValueIsAdjusting())
		{
			Long id = list.getSelectedValue();
			EineEMailMenu eem = new EineEMailMenu(id);
			eem.anzeigen();
		}
	}
}