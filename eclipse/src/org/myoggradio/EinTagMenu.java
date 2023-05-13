package org.myoggradio;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;
public class EinTagMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	public EinTagMenu(String tag)
	{
		super("Ein Tag Menu");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Postgres postgres = new Postgres();
		ArrayList<Long> ids = postgres.getOneTag(tag);
		Long[] mids = new Long[ids.size()];
		for (int i=0;i<ids.size();i++)
		{
			mids[i] = ids.get(i);
		}
		JList<Long> list = new JList<Long>(mids);
		ListCellRenderer<Long> renderer = new MyIdCellRenderer();
		list.setCellRenderer(renderer);
		ListSelectionListener listener = new MyIdListSelectionListener(list);
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
