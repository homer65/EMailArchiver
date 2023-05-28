package org.myoggradio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
public class BodyMenu 
{
	private String filename = null;
	public BodyMenu(String body)
	{
		try
		{
			filename = Parameter.mail_temp + "mailbody.html";
			File aus = new File(filename);
			FileOutputStream fos = new FileOutputStream(aus,true);
			Writer wrt = new OutputStreamWriter(fos,"UTF-8");
			wrt.write(body);
			wrt.close();
			ProcessBuilder builder = new ProcessBuilder(Parameter.html_programm,filename); 
			builder.start();
		}
		catch (Exception e)
		{
			Protokol.write("BodyMenu::Exception");
			Protokol.write(e.toString());
		}
	}
	public void anzeigen()
	{

	}
}
