package org.myoggradio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
 				System.out.println("Will write to: " + filename);
				OutputStream aus = new FileOutputStream(file);
				int x = ein.read();
				while (x >= 0)
				{
					aus.write(x);
					x = ein.read();
				}
				aus.close();
				ein.close();
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
