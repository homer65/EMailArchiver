package org.myoggradio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class FileExport extends Thread 
{
	public void run()
	{
		Protokol.write("FileExport:run:gestartet");
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(Parameter.export_folder));
		chooser.setDialogTitle("Export Directory auswählen");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			Parameter.export_folder = chooser.getSelectedFile().getAbsolutePath() + File.separator;
			Protokol.write("FileExport:run:Export Directory: " + Parameter.export_folder);
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
	 				Protokol.write("Will write to: " + filename);
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
					Protokol.write("FileExport:run:Exception:");
					Protokol.write(e.toString());
				}
			}
		}
		Protokol.write("FileExport:run:beendet");
	}
}
