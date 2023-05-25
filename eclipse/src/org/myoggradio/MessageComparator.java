package org.myoggradio;
import java.util.Comparator;

import javax.mail.Message;
public class MessageComparator implements Comparator<Message>
{

	@Override
	public int compare(Message arg0, Message arg1)
	{
		int erg = 0;
		try
		{
			long l0 = arg0.getSentDate().getTime();
			long l1 = arg1.getSentDate().getTime();
			if (l0 < l1) erg = 1;
			if (l1 < l0) erg = -1;
		}
		catch (Exception e)
		{
			Protokol.write("MessageComparator:compare:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
}
