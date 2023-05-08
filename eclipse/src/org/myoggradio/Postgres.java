package org.myoggradio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
public class Postgres
{
	private String benutzer;
	private String passwort;
	private String url;
	private String driver;
    private Connection con = null;
    private PreparedStatement stmt = null;
    public Postgres()
    {
   		String pg_user = Parameter.pg_user;
		String pg_passwort = Parameter.pg_passwort;
		String pg_url = Parameter.pg_url;
		String pg_driver = Parameter.pg_driver;
    	this.benutzer = pg_user;
    	this.passwort = pg_passwort;
    	this.url = pg_url;
    	this.driver = pg_driver;
    }
    public void connect() 
    {
        if (con == null)
        {
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(url, benutzer, passwort);
            } 
            catch (Exception e) 
            {
                con = null;
                System.out.println("Postgres:connect:Exception:");
                System.out.println(e.toString());
            }
        }
        return;
    }
    public void close()
    {
    	try
    	{
    		con.close();
    	}
    	catch (Exception e)
    	{
    		System.out.println("Postgres:close:Exception:");
    		System.out.println(e.toString());
    	}
    }
    public void commit()
    {
    	try
    	{
    		con.commit();
    	}
    	catch (Exception e)
    	{
    		System.out.println("Postgres:commit:Exception:");
    		System.out.println(e.toString());
    	}
    }
    public String insertMessage(Message msg,ArrayList<String> tags)
    {
    	String erg = null;
    	if (con == null) connect();
 		String subject = "unkown";
		String typ = "";
		try
		{
		   	con.setAutoCommit(false);
			subject = msg.getSubject();
			int igroesse = msg.getSize();
			Date date_r = msg.getReceivedDate();
			long received = date_r.getTime();
			Date date_s = msg.getReceivedDate();
			long send = date_s.getTime();
			typ += msg.getContentType();
			//
		   	String sql = "insert into email (";
	    	sql += " send";
	    	sql += ",received";
	    	sql += ",subject";
	    	sql += ",typ";
	    	sql += ",sfrom";
	    	sql += ",sto";
	    	sql += ",groesse";
	     	sql += ")";
	    	sql += " values (?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,send);
            stmt.setLong(2,received);
            stmt.setString(3,subject);
            stmt.setString(4,typ);
            stmt.setLong(5,send);
            stmt.setLong(6,send);
            stmt.setInt(7,igroesse);
            stmt.executeUpdate();
            stmt.close();
			Address[] to = msg.getAllRecipients();
			for (int i=0;i<to.length;i++)
			{
				Address adr = to[i];
				if (adr instanceof InternetAddress)
				{
					InternetAddress iadr = (InternetAddress) adr;
					String sto = iadr.getAddress();
				   	sql = "insert into sto (";
			    	sql += " id";
			    	sql += ",adresse";
			     	sql += ")";
			    	sql += " values (?,?)";
		            stmt = con.prepareStatement(sql);
		            stmt.setLong(1,send);
		            stmt.setString(2,sto);
		            stmt.executeUpdate();
		            stmt.close();
				}
			}
			Address[] from = msg.getFrom();
			for (int i=0;i<from.length;i++)
			{
				Address adr = from[i];
				if (adr instanceof InternetAddress)
				{
					InternetAddress iadr = (InternetAddress) adr;
					String sfrom = iadr.getAddress();
				   	sql = "insert into sfrom (";
			    	sql += " id";
			    	sql += ",adresse";
			     	sql += ")";
			    	sql += " values (?,?)";
		            stmt = con.prepareStatement(sql);
		            stmt.setLong(1,send);
		            stmt.setString(2,sfrom);
		            stmt.executeUpdate();
		            stmt.close();
				}
			}
			for (int i=0;i<tags.size();i++)
			{
				String tag = tags.get(i);
			   	sql = "insert into tags (";
		    	sql += " email_id";
		    	sql += ",tag";
		     	sql += ")";
		    	sql += " values (?,?)";
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1,send);
	            stmt.setString(2,tag);
	            stmt.executeUpdate();
	            stmt.close();
			}
			File file = new File("/tmp/GetEMail.eml");
			OutputStream aus = new FileOutputStream(file);
			msg.writeTo(aus);
			aus.close();
			FileInputStream ein = new FileInputStream(file);
		   	sql = "insert into body (";
	    	sql += " id";
	    	sql += ",wert";
	     	sql += ")";
	    	sql += " values (?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,send);
			stmt.setBinaryStream(2,ein,file.length());
            stmt.executeUpdate();
            stmt.close();
            ein.close();
			con.commit();
		}
		catch (Exception e)
		{
			System.out.println("Postgres:insertMessage:Exception:");
			System.out.println(e.toString());
			erg = e.toString();
			try
			{
				con.rollback();
			}
			catch (Exception ex)
			{
				System.out.println("Postgres:insertMessage:rollback:Exception:");
				System.out.println(e.toString());
			}
		}
		close();
		return erg;
    }
}
