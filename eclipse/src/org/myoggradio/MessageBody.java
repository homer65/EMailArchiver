package org.myoggradio;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;

public class MessageBody 
{
	public SatzHTML getBody(Message msg,boolean html)
	{
		SatzHTML erg = new SatzHTML();
		try
		{
			Object body = msg.getContent();
			if (body instanceof Multipart)
			{
				SatzHTML test = processMultipart((Multipart) body,html);
				if (test != null) erg = test;
			}
			else
			{
				SatzHTML test = processPart(msg,html);
				if (test != null) erg = test;
			}
		}
		catch (Exception e)
		{
			Protokol.write("MessageBody:getBody:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	public SatzHTML processMultipart(Multipart mp,boolean html)
	{
		SatzHTML erg = null;
		try
		{
			for (int i=0;i<mp.getCount();i++)
			{
				SatzHTML test = processPart(mp.getBodyPart(i),html);
				if (test != null) erg = test;
			}
		}
		catch (Exception e)
		{
			Protokol.write("MessageBody:processMultipart:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	public SatzHTML processPart(Part p,boolean html)
	{
		SatzHTML erg = null;;
		try
		{
			String contentType = p.getContentType();
			if (contentType.toLowerCase().startsWith("multipart/"))
			{
				SatzHTML test = processMultipart((Multipart) p.getContent(),html);
				if (test != null) erg = test;
			}
			else if (contentType.toLowerCase().startsWith("text/plain"))
			{
				Protokol.write("MessageBody:processPart:text/plain detected");
				if (!html)
				{
					String shtml = (String) p.getContent();
					String charset = getCharset(contentType);
					erg = new SatzHTML();
					erg.setHTML(shtml);
					erg.setCharset(charset);
				}
			}
			else if (contentType.toLowerCase().startsWith("text/html"))
			{
				Protokol.write("MessageBody:processPart:text/html detected");
				Protokol.write(contentType);
				if (html)
				{
					String shtml = (String) p.getContent();
					String charset = getCharset(contentType);
					erg = new SatzHTML();
					erg.setHTML(shtml);
					erg.setCharset(charset);
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
	public String getCharset(String satz)
	{
		String erg = "UTF-8";
		String[] worte = satz.split(";");
		for (int i=0;i<worte.length;i++)
		{
			String wort = worte[i].trim();
			String[] token = wort.split("=");
			try
			{
				if (token[0].equals("charset"))
				{
					erg = untrim(token[1]);
				}
			}
			catch (Exception e)
			{
				Protokol.write("MessageBody:getCharset:Exception");
				Protokol.write(e.toString());
			}
		}
		return erg;
	}
	public String untrim(String wort)
	{
		String erg = "";
		for (int i=0;i<wort.length();i++)
		{
			String ch = wort.substring(i,i+1);
			if (!ch.equals("\"")) erg +=ch;
		}
		return erg;
	}
}
