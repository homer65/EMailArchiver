package org.myoggradio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class FileImport extends Thread 
{
	public void run()
	{
		System.out.println("FileImport:run:gestartet");
		File[] files = new File(Parameter.import_folder).listFiles();
		Postgres postgres = new Postgres();
		for (int i=0;i<files.length;i++)
		{
			try
			{
				File file = files[i];
				System.out.println("Will read from: " + file.getAbsolutePath());
				InputStream ein = new FileInputStream(file.getAbsolutePath());
				Properties props = new Properties();
	            Session mailSession = Session.getDefaultInstance(props, null);
	            MimeMessage message = new MimeMessage(mailSession,ein);
	            ein.close();
		        ArrayList<String> tags = new ArrayList<String>();
		        tags.add("#imported");
				long nummer = getNummer(file.getName());
				postgres.insertMessage(message,tags,nummer);
			}
			catch (Exception e)
			{
				System.out.println("FileImport:run:Exception:");
				System.out.println(e.toString());
			}
		}
		System.out.println("FileImport:run:beendet");
	}
	public long getNummer(String name)
	{
		long erg = 0;
		String sl = "";
		for (int i=0;i<name.length();i++)
		{
			String x = name.substring(i,i+1);
			if (isDigit(x)) sl += x;
		}
		if (sl.length() > 11)
		{
			erg = Long.parseLong(sl);
		}
		return erg;
	}
	public boolean isDigit(String x)
	{
		boolean erg = false;
		if (x.equals("0")) erg = true;
		else if (x.equals("1")) erg = true;
		else if (x.equals("2")) erg = true;
		else if (x.equals("3")) erg = true;
		else if (x.equals("4")) erg = true;
		else if (x.equals("5")) erg = true;
		else if (x.equals("6")) erg = true;
		else if (x.equals("7")) erg = true;
		else if (x.equals("8")) erg = true;
		else if (x.equals("9")) erg = true;
		return erg;
	}
}
