package org.myoggradio;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;
public class EMailMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	public EMailMenu(ArrayList<SatzEMail> emails)
	{
		super("EMail Menu");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SatzEMail[] saetze = new SatzEMail[emails.size()];
		for(int i=0;i<emails.size();i++)
		{
			saetze[i] = emails.get(i);
		}
		JList<SatzEMail> list = new JList<SatzEMail>(saetze);
		ListCellRenderer<SatzEMail> renderer = new MyEMailCellRenderer();
		list.setCellRenderer(renderer);
		ListSelectionListener listener = new MyEMailListSelectionListener(list);
		list.addListSelectionListener(listener);
		JScrollPane span = new JScrollPane(list);
		span.setPreferredSize(new Dimension(800,800));
		JPanel cpan = new JPanel();
		cpan.add(span);
		setContentPane(cpan);
	}
	public void anzeigen()
	{
		pack();
		setVisible(true);
	}
}
