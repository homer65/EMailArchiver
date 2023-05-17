package org.myoggradio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.swing.JTextField;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
public class EineMessageMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Message msg = null;
	private JButton butt1 = new JButton("Show");
	private JButton butt2 = new JButton("Archive");
	private JTextField tf1 = new JTextField();
	private JLabel ltf1 = new JLabel("Tags ");
	public EineMessageMenu(Message msg)
	{
		super("Eine Message Menu");
		this.msg = msg;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String subject = "unkown";
		ArrayList<String> tolist = new ArrayList<String>();
		String fromlist = "";
		String groesse = "";
		String datum_r = "";
		String datum_s = "";
		String typ = "";
		try
		{
			subject = msg.getSubject();
			Address[] to = msg.getAllRecipients();
			for (int i=0;i<to.length;i++)
			{
				Address adr = to[i];
				if (adr instanceof InternetAddress)
				{
					InternetAddress iadr = (InternetAddress) adr;
					tolist.add(iadr.getAddress());
				}
			}
			Address[] from = msg.getFrom();
			for (int i=0;i<from.length;i++)
			{
				Address adr = from[i];
				if (adr instanceof InternetAddress)
				{
					InternetAddress iadr = (InternetAddress) adr;
					fromlist += iadr.getAddress() + ",";
				}
			}
			int igroesse = msg.getSize();
			groesse += igroesse;
			Date date_r = msg.getReceivedDate();
			LocalDateTime ldt_r = date_r.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			datum_r += dtf.format(ldt_r);
			Date date_s = msg.getReceivedDate();
			LocalDateTime ldt_s = date_s.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			datum_s += dtf.format(ldt_s);
			typ += msg.getContentType();
		}
		catch (Exception e)
		{
			System.out.println("EineMessageMenu::Exception:");
			System.out.println(e.toString());
		}
		JLabel lab0 = new JLabel("Send: " + datum_s + " Received: " + datum_r);
		JLabel lab1 = new JLabel("Subject: " +subject);
		//JLabel lab2 = new JLabel("To: " + tolist);
		JLabel lab3 = new JLabel("<= From: " + fromlist);
		JLabel lab4 = new JLabel("Size: " +groesse);
		JLabel lab5 = new JLabel("Typ: " + typ);
		JPanel lpan = new JPanel();
		lpan.setLayout(new GridLayout(tolist.size() + 5,1));
		lpan.add(lab0);
		lpan.add(lab1);
		for (int i=0;i<tolist.size();i++)
		{
			String sto = tolist.get(i);
			lpan.add(new JLabel("=> To: " + sto));
		}
		lpan.add(lab3);
		lpan.add(lab4);
		lpan.add(lab5);
		JPanel bpan = new JPanel();
		bpan.setLayout(new GridLayout(1,2));
		bpan.add(butt1);
		bpan.add(butt2);
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
				OutputStream aus = new FileOutputStream(new File("/tmp/GetEMail.eml"));
				msg.writeTo(aus);
				aus.close();
				ProcessBuilder builder = new ProcessBuilder(Parameter.mail_programm,"/tmp/GetEMail.eml"); 
				builder.start();
			}
			catch (Exception e)
			{
				System.out.println("EineMessageMenu:butt1:");
				System.out.println(e.toString());
			}
		}
		if (quelle == butt2)
		{
			String tags = tf1.getText();
			if (tags == null) tags = "";
			String[] worte = tags.split(" ");
			ArrayList<String> altags = new ArrayList<String>();
			for (int i=0;i<worte.length;i++)
			{
				String tag = worte[i].toLowerCase();
				if (!tag.equals(""))
				{
					altags.add(tag);
				}
			}
			if (altags.size() > 0)
			{
				Postgres postgres = new Postgres();
				String erg = postgres.insertMessage(msg,altags);
				if (erg != null)
				{
					System.out.println("Postgres returned: ");
					System.out.println(erg);
				}
				dispose();
			}
			else
			{
				System.out.println("Mindestens einen Tag vorgeben");
			}
		}
		
	}
}
