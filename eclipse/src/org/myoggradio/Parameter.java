package org.myoggradio;

public class Parameter 
{
	public static String version = "Version 0.19 24.05.2023";
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
	public void print()
	{
		System.out.println("mail_server=" + mail_server + "=");
		System.out.println("mail_user=" + mail_user + "=");
		System.out.println("mail_folder=" + mail_folder + "=");
		System.out.println("mail_programm=" + mail_programm + "=");
		System.out.println("mail_temp=" + mail_temp + "=");
		System.out.println("mail_delete=" + mail_delete + "=");
		System.out.println("pg_url=" + pg_url + "=");
		System.out.println("pg_user=" + pg_user + "=");
		System.out.println("pg_driver=" + pg_driver + "=");
		System.out.println("sqlite=" + sqlite + "=");
		System.out.println("sqlite_url=" + sqlite_url + "=");
		System.out.println("exort_folder=" + export_folder + "=");
		System.out.println("import_folder=" + import_folder + "=");
	}
}
