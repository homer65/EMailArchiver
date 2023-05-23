package org.myoggradio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;

public class FileExport extends Thread 
{
	public void run()
	{
		System.out.println("FileExport:run:gestartet");
		Postgres postgres = new Postgres();
		ArrayList<Long> ids = postgres.getAllId();
		for (int i=0;i<ids.size();i++)
		{
			try
			{
				long id = ids.get(i);
				InputStream ein = postgres.getBody(id);
				String filename = Parameter.export_folder + id + ".eml";
 				File file = new File(filename);
 				byte[] buffer = new byte[8192];
 				System.out.println("Will write to: " + filename);
				OutputStream aus = new FileOutputStream(file);
				int n = ein.read(buffer);
				while (n >= 0)
				{
					aus.write(buffer,0,n);
					n = ein.read(buffer);
				}
				aus.close();
				ein.close();
				String tags = postgres.getTags(id);
				String filename2 = Parameter.export_folder + id + ".tag";
				File file2 = new File(filename2);
				System.out.println("Will write to: " + filename2);
				Writer wrt = new FileWriter(file2);
				wrt.write(tags);
				wrt.close();
			}
			catch (Exception e)
			{
				System.out.println("FileExport:run:Exception:");
				System.out.println(e.toString());
			}
		}
		System.out.println("FileExport:run:beendet");
	}
}
