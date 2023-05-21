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
				postgres.insertMessage(message,tags);
			}
			catch (Exception e)
			{
				System.out.println("FileImport:run:Exception:");
				System.out.println(e.toString());
			}
		}
		System.out.println("FileImport:run:beendet");
	}
}
