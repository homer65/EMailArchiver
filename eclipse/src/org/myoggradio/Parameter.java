package org.myoggradio;

public class Parameter 
{
	public static String version = "Version 0.27 25.05.2023";
	public static String mail_server = "";
	public static String mail_user = "";
	public static String mail_passwort = "";
	public static String mail_folder = "INBOX";
	public static String mail_programm = "thunderbird";
	public static String mail_temp = "/tmp/";
	public static String mail_delete = "true";
	public static String pg_url = "";
	public static String pg_user = "";
	public static String pg_driver = "org.postgres.Driver";
	public static String pg_passwort = "";
	public static String sqlite = "false";
	public static String sqlite_url = "jdbc:sqlite:EMailArchiver.db";
	public static String export_folder = "/home/christian/email/";
	public static String import_folder = "/home/christian/email/";
	public static String import_delete = "false";
	public static String protokol = "EMailArchiver.log";
	public void print()
	{
		Protokol.write("mail_server=" + mail_server + "=");
		Protokol.write("mail_user=" + mail_user + "=");
		Protokol.write("mail_folder=" + mail_folder + "=");
		Protokol.write("mail_programm=" + mail_programm + "=");
		Protokol.write("mail_temp=" + mail_temp + "=");
		Protokol.write("mail_delete=" + mail_delete + "=");
		Protokol.write("pg_url=" + pg_url + "=");
		Protokol.write("pg_user=" + pg_user + "=");
		Protokol.write("pg_driver=" + pg_driver + "=");
		Protokol.write("sqlite=" + sqlite + "=");
		Protokol.write("sqlite_url=" + sqlite_url + "=");
		Protokol.write("exort_folder=" + export_folder + "=");
		Protokol.write("import_folder=" + import_folder + "=");
		Protokol.write("import_delete=" + import_delete + "=");
		Protokol.write("protokol=" + protokol + "=");
	}
}
