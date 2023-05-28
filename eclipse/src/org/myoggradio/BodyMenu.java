package org.myoggradio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class BodyMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	public BodyMenu(String body)
	{
		super("Body Menu ");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel cpan = new JPanel();
		cpan.setLayout(new BorderLayout());
		JTextArea ta = new JTextArea();
		ta.setText(body);
		JScrollPane span = new JScrollPane(ta);
		span.setPreferredSize(new Dimension(600,800));
		cpan.add(span,BorderLayout.CENTER);
		setContentPane(cpan);
	}
	public void anzeigen()
	{
		pack();
		setVisible(true);
	}
}
