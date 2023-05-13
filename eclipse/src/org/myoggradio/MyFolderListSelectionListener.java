package org.myoggradio;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.sun.mail.imap.IMAPFolder;

public class MyFolderListSelectionListener implements ListSelectionListener
{
	private JList<IMAPFolder> list = null;
	private Verarbeitung verarbeitung = null;
	public MyFolderListSelectionListener(JList<IMAPFolder> list,Verarbeitung verarbeitung)
	{
		this.list = list;
		this.verarbeitung = verarbeitung;
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) 
	{
		if (!lse.getValueIsAdjusting())
		{
			IMAPFolder name = list.getSelectedValue();
			verarbeitung.setFolder(name);
		}
	}
}