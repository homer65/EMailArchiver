package org.myoggradio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;
import com.sun.mail.imap.IMAPFolder;

public class FolderDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton butt1 = new JButton("choose Folder");
	public FolderDialog(ArrayList<IMAPFolder> alfolder,Verarbeitung verarbeitung)
	{
		this.setModal(true);
		this.setTitle("FolderDialog");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		IMAPFolder[] folders = new IMAPFolder[alfolder.size()];
		for (int i=0;i<alfolder.size();i++)
		{
			folders[i] = alfolder.get(i);
		}
		JList<IMAPFolder> list = new JList<IMAPFolder>(folders);
		ListCellRenderer<IMAPFolder> renderer = new MyFolderCellRenderer();
		list.setCellRenderer(renderer);
		ListSelectionListener listener = new MyFolderListSelectionListener(list,verarbeitung);
		list.addListSelectionListener(listener);
		JScrollPane span = new JScrollPane(list);
		span.setPreferredSize(new Dimension(800,400));
		JPanel cpan = new JPanel();
		cpan.setLayout(new BorderLayout());
		cpan.add(span,BorderLayout.CENTER);
		cpan.add(butt1,BorderLayout.SOUTH);
		butt1.addActionListener(this);
		setContentPane(cpan);
	}
	public void anzeigen()
	{
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		Object quelle = ae.getSource();
		if (quelle == butt1)
		{
			dispose();
		}
	}
}
