package org.myoggradio;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;

public class MessageBody 
{
	public String getBody(Message msg,boolean html)
	{
		String erg = "";
		try
		{
			Object body = msg.getContent();
			if (body instanceof Multipart)
			{
				erg += processMultipart((Multipart) body,html);
			}
			else
			{
				erg += processPart(msg,html);
			}
		}
		catch (Exception e)
		{
			Protokol.write("MessageBody:getBody:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	public String processMultipart(Multipart mp,boolean html)
	{
		String erg = "";
		try
		{
			for (int i=0;i<mp.getCount();i++)
			{
				erg += processPart(mp.getBodyPart(i),html);
			}
		}
		catch (Exception e)
		{
			Protokol.write("MessageBody:processMultipart:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	public String processPart(Part p,boolean html)
	{
		String erg = "";
		try
		{
			String contentType = p.getContentType();
			if (contentType.toLowerCase().startsWith("multipart/"))
			{
				erg += processMultipart((Multipart) p.getContent(),html);
			}
			else if (contentType.toLowerCase().startsWith("text/plain"))
			{
				Protokol.write("MessageBody:processPart:text/plain detected");
				if (!html)
				{
					erg += (String) p.getContent();
				}
			}
			else if (contentType.toLowerCase().startsWith("text/html"))
			{
				Protokol.write("MessageBody:processPart:text/html detected");
				if (html)
				{
					erg += (String) p.getContent();
				}
			}
			else
			{
				Protokol.write("MessageBody:processPart:" + contentType + " detected");
			}
		}
		catch (Exception e)
		{
			Protokol.write("MessageBody:processPart:Exception:");
			Protokol.write(e.toString());	
		}
		return erg;
	}
}
