package org.myoggradio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Initialisieren 
{
	public String ini_file = "EMailArchiver.ini";
	public void start()
	{
		try
		{
			Reader rdr = new FileReader(new File(ini_file));
			BufferedReader br = new BufferedReader(rdr);
			String satz = br.readLine();
			while (satz != null)
			{
				parse(satz);
				satz = br.readLine();
			}
		}
		catch (Exception e)
		{
			System.out.println("Initialisieren:start:Exception:");
			System.out.println(e.toString());
		}
		Parameter parameter = new Parameter();
		parameter.print();
	}
	public void parse(String satz)
	{
		String[] worte = satz.split("=");
		if (worte.length >= 2)
		{
			String feld = worte[0].toLowerCase();
			String wert = worte[1];
			if (feld.equals("mail_server")) Parameter.mail_server = wert;
			if (feld.equals("mail_user")) Parameter.mail_user = wert;
			if (feld.equals("mail_passwort")) Parameter.mail_passwort = wert;
			if (feld.equals("mail_folder")) Parameter.mail_folder = wert;
			if (feld.equals("mail_programm")) Parameter.mail_programm = wert;
			if (feld.equals("mail_temp")) Parameter.mail_temp = wert;
			if (feld.equals("pg_url")) Parameter.pg_url = wert;
			if (feld.equals("pg_user")) Parameter.pg_user = wert;
			if (feld.equals("pg_passwort")) Parameter.pg_passwort = wert;
			if (feld.equals("pg_driver")) Parameter.pg_driver = wert;
			if (feld.equals("sqlite")) Parameter.sqlite = wert;
			if (feld.equals("sqlite_url")) Parameter.sqlite_url = wert;
			if (feld.equals("export_folder")) Parameter.export_folder = wert;
			if (feld.equals("import_folder")) Parameter.import_folder = wert;
			if (feld.equals("import_delete")) Parameter.import_delete = wert;
		}
	}
}
