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
			if (feld.equals("pg_server")) Parameter.pg_server = wert;
			if (feld.equals("pg_user")) Parameter.pg_user = wert;
			if (feld.equals("pg_passwort")) Parameter.pg_passwort = wert;
			if (feld.equals("pg_db")) Parameter.pg_db = wert;
		}
	}
}