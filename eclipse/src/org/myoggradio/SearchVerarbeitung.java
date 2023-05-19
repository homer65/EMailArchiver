package org.myoggradio;

import java.util.ArrayList;

public class SearchVerarbeitung extends Thread
{
	private String search = null;
	public SearchVerarbeitung(String search)
	{
		this.search = search;
	}
	public void run()
	{
		Postgres postgres = new Postgres();
		ArrayList<SatzEMail> emails = postgres.searchEMailSubject(search);
		EMailMenu em = new EMailMenu(emails);
		em.anzeigen();
	}
}