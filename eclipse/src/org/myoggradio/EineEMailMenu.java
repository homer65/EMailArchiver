package org.myoggradio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class EineEMailMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton butt1 = new JButton("Show");
	private JLabel tf1 = new JLabel();
	private JLabel ltf1 = new JLabel("Tags ");
	private long id = 0;
	public EineEMailMenu(long id)
	{
		super("Eine EMail Menu");
		this.id = id;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Postgres postgres = new Postgres();
		SatzEMail satz = postgres.getOneEMail(id);
		String subject = satz.getSubject();
		ArrayList<String> tolist = postgres.getToList(satz.getSto());
		String fromlist = postgres.getFromList(id);
		String groesse = satz.getGroesse() + "";
		String tags = postgres.getTags(id);
		tf1.setText(tags);
		String datum_r = "";
		String datum_s = "";
		String typ = satz.getTyp();
		try
		{
			Date date_r = new Date(satz.getReceived());
			LocalDateTime ldt_r = date_r.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			datum_r += dtf.format(ldt_r);
			Date date_s = new Date(satz.getSend());
			LocalDateTime ldt_s = date_s.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			datum_s += dtf.format(ldt_s);
		}
		catch (Exception e)
		{
			System.out.println("EineEMailMenu::Exception:");
			System.out.println(e.toString());
		}
		JLabel lab0 = new JLabel("Send: " + datum_s + " Received: " + datum_r);
		JLabel lab1 = new JLabel("Subject: " +subject);
		JLabel lab3 = new JLabel("<= From: " + fromlist);
		JLabel lab4 = new JLabel("Size: " +groesse);
		JLabel lab5 = new JLabel("Typ: " + typ);
		JPanel lpan = new JPanel();
		lpan.setLayout(new GridLayout(5 + tolist.size(),1));
		lpan.add(lab0);
		lpan.add(lab1);
		for (int i=0;i<tolist.size();i++)
		{
			lpan.add(new JLabel("=> To: " + tolist.get(i)));
		}
		lpan.add(lab3);
		lpan.add(lab4);
		lpan.add(lab5);
		JPanel bpan = new JPanel();
		bpan.setLayout(new GridLayout(1,1));
		bpan.add(butt1);
		JPanel tpan = new JPanel();
		tpan.setLayout(new BorderLayout());
		tpan.add(ltf1,BorderLayout.WEST);
		tpan.add(tf1,BorderLayout.CENTER);
		JPanel cpan = new JPanel();
		cpan.setLayout(new BorderLayout());
		cpan.add(lpan,BorderLayout.NORTH);
		cpan.add(tpan,BorderLayout.CENTER);
		cpan.add(bpan,BorderLayout.SOUTH);
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
			try
			{
				Postgres postgres = new Postgres();
				InputStream ein = postgres.getBody(id);
				OutputStream aus = new FileOutputStream(new File(Parameter.mail_temp + "GetEMail.eml"));
				int n = ein.read();
				while (n >= 0)
				{
					aus.write(n);
					n = ein.read();
				}
				aus.close();
				ein.close();
				ProcessBuilder builder = new ProcessBuilder(Parameter.mail_programm,Parameter.mail_temp + "GetEMail.eml"); 
				builder.start();
			}
			catch (Exception e)
			{
				System.out.println("EineEMailMenu:butt1:");
				System.out.println(e.toString());
			}
		}
	}
}
