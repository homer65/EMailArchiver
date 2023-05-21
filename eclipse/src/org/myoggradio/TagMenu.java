package org.myoggradio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;
public class TagMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private String tag = null;
	private JButton butt1 = new JButton("choose Tag");
	private JButton butt2 = new JButton("delete Tag");
	public void setTag(String s)
	{
		tag = s;
	}
	public TagMenu(ArrayList<SatzTag> tags)
	{
		super("Tag Menu");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SatzTag[] stags = new SatzTag[tags.size()];
		for (int i=0;i<tags.size();i++)
		{
			stags[i] = tags.get(i);
			tag = stags[0].getTag();
		}
		JList<SatzTag> list = new JList<SatzTag>(stags);
		ListCellRenderer<SatzTag> renderer = new MyTagCellRenderer();
		list.setCellRenderer(renderer);
		ListSelectionListener listener = new MyTagListSelectionListener(list,this);
		list.addListSelectionListener(listener);
		JScrollPane span = new JScrollPane(list);
		span.setPreferredSize(new Dimension(800,800));
		JPanel cpan = new JPanel();
		cpan.setLayout(new BorderLayout());
		cpan.add(span,BorderLayout.CENTER);
		JPanel bpan = new JPanel();
		bpan.setLayout(new GridLayout(1,2));
		bpan.add(butt1);
		bpan.add(butt2);
		cpan.add(bpan,BorderLayout.SOUTH);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
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
			EinTagMenu etm = new EinTagMenu(tag);
			etm.anzeigen();
			dispose();
		}
		if (quelle == butt2)
		{
			if (tag != null)
			{
				if (tag.length() > 1)
				{
					String x = tag.substring(0,1);
					if (x.equals("#"))
					{
						Postgres postgres = new Postgres();
						ArrayList<Long> ids = postgres.getOneTag(tag);
						for (int i=0;i<ids.size();i++)
						{
							long id = ids.get(i);
							postgres.deleteMessage(id);
						}
					}
					else
					{
						System.out.println("Tag muss mit # anfangen um geloescht zu werden");
					}
				}
				else
				{
					System.out.println("Tag muss laenger 1 sein");
				}
			}
			else
			{
				System.out.println("Bitte ein Tag anklicken");
			}
		}
	}
}
