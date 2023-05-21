package org.myoggradio;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MainMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton butt1 = new JButton("Show EMail");
	private JButton butt2 = new JButton("Show Archived");
	private JButton butt3 = new JButton("Search Archived");
	private JButton butt4 = new JButton("Export to Folder");
	public MainMenu()
	{
		super("Main Menu " + Parameter.version);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel cpan = new JPanel();
		cpan.setLayout(new GridLayout(4,1));
		cpan.add(butt1);
		cpan.add(butt2);
		cpan.add(butt3);
		cpan.add(butt4);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		butt4.addActionListener(this);
		setContentPane(cpan);
		this.setPreferredSize(new Dimension(400,200));
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
			try
			{
				Verarbeitung v = new Verarbeitung();
				v.start();
				//dispose();
			}
			catch (Exception e)
			{
				System.out.println("MainMenu:actionPerformed:butt1:Exception");
				System.out.println(e.toString());
			}
		}
		if (quelle == butt2)
		{
			ArchivVerarbeitung av = new ArchivVerarbeitung();
			av.start();
			//dispose();
		}
		if (quelle == butt3)
		{
			SearchMenu sm = new SearchMenu();
			sm.anzeigen();
		}
		if (quelle == butt4)
		{
			FileExport export = new FileExport();
			export.start();
		}
	}
}
