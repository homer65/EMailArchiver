package org.myoggradio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MainMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton butt1 = new JButton("Show EMail");
	private JButton butt2 = new JButton("Show Archived");
	private JButton butt3 = new JButton("Search Archived");
	private JButton butt4 = new JButton("Export to Folder");
	private JButton butt5 = new JButton("Import from Folder");
	private JLabel lab1 = new JLabel();
	public MainMenu()
	{
		super("Main Menu " + Parameter.version);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ImageIcon image = new ImageIcon(Parameter.logo);
		lab1 = new JLabel(image);
		JPanel bpan = new JPanel();
		bpan.setLayout(new BorderLayout());
		bpan.add(lab1,BorderLayout.NORTH);
		JPanel cpan = new JPanel();
		cpan.setLayout(new GridLayout(5,1));
		cpan.add(butt1);
		cpan.add(butt2);
		cpan.add(butt3);
		cpan.add(butt4);
		cpan.add(butt5);
		bpan.add(cpan,BorderLayout.CENTER);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		butt4.addActionListener(this);
		butt5.addActionListener(this);
		setContentPane(bpan);
		this.setPreferredSize(new Dimension(800,600));
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
				Protokol.write("MainMenu:actionPerformed:butt1:Exception");
				Protokol.write(e.toString());
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
		if (quelle == butt5)
		{
			FileImport fimport = new FileImport();
			fimport.start();
		}
	}
}
