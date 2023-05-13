package org.myoggradio;
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
	public MainMenu()
	{
		super("Main Menu");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel cpan = new JPanel();
		cpan.setLayout(new GridLayout(1,2));
		cpan.add(butt1);
		cpan.add(butt2);
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
			try
			{
				Verarbeitung v = new Verarbeitung();
				v.start();
				dispose();
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
			dispose();
		}
	}
}
