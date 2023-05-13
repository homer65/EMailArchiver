package org.myoggradio;
public class SatzEMail 
{
	private long send = 0;
	private long received = 0;
	private String subject = "";
	private String typ = "";
	private long sfrom = 0;
	private long sto = 0;
	private int groesse = 0;
	public long getSend()
	{
		return send;
	}
	public long getReceived()
	{
		return received;
	}
	public String getSubject()
	{
		return subject;
	}
	public String getTyp()
	{
		return typ;
	}
	public long getSfrom()
	{
		return sfrom;
	}
	public long getSto()
	{
		return sto;
	}
	public int getGroesse()
	{
		return groesse;
	}
	public void setSend(long l)
	{
		send = l;
	}
	public void setReceived(long l)
	{
		received = l;
	}
	public void setSubject(String s)
	{
		subject = s;
	}
	public void setTyp(String s)
	{
		typ = s;
	}
	public void setSfrom(long l)
	{
		sfrom = l;
	}
	public void setSto(long l)
	{
		sto = l;
	}
	public void setGroesse(int i)
	{
		groesse = i;
	}
}

