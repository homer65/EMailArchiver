package org.myoggradio;

import java.util.ArrayList;

public class ArchivVerarbeitung extends Thread
{
	public void run()
	{
		Postgres postgres = new Postgres();
		ArrayList<SatzTag> tags = postgres.getAllTags();
		TagMenu tm = new TagMenu(tags);
		tm.anzeigen();
	}
}
