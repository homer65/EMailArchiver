package org.myoggradio;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyTagListSelectionListener implements ListSelectionListener
{
	private JList<SatzTag> list = null;
	private TagMenu tm = null;
	public MyTagListSelectionListener(JList<SatzTag> list,TagMenu tm)
	{
		this.list = list;
		this.tm = tm;
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) 
	{
		if (!lse.getValueIsAdjusting())
		{
			SatzTag satz = list.getSelectedValue();
			tm.setTag(satz.getTag());
		}
	}
}