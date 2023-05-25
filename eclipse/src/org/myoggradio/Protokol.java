package org.myoggradio;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Protokol 
{
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static void write(String s)
	{
		System.out.println(s);
		try
		{
			LocalDateTime datum = LocalDateTime.now();
			String sdatum = datum.format(formatter);
			File file = new File(Parameter.protokol);
			Writer wrt = new FileWriter(file,true);
			wrt.write(sdatum + " " + s + "\n");
			wrt.close();
		}
		catch (Exception e)
		{
			System.out.println("Protokol:write:Exception:");
			System.out.println(e.toString());
		}
	}
}
