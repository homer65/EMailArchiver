package org.myoggradio;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class SearchMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton butt1 = new JButton("Search Subject");
	private JTextField tf1 = new JTextField(50);
	public SearchMenu()
	{
		super("Search Menu");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel cpan = new JPanel();
		cpan.setLayout(new GridLayout(2,1));
		cpan.add(tf1);
		cpan.add(butt1);
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
			String search = tf1.getText().trim();
			if (search.equals(""))
			{
				System.out.println("Bitte Search String vorgeben");
			}
			else
			{
				SearchVerarbeitung sv = new SearchVerarbeitung(search);
				sv.start();
				dispose();
			}
		}
	}
}
