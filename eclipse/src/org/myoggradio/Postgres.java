package org.myoggradio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            	if (Parameter.sqlite.equals("true"))
            	{
	            	Class.forName("org.sqlite.JDBC");
	            	con = DriverManager.getConnection(Parameter.sqlite_url);
	            	con.setAutoCommit(false);
            	}
            	else
            	{
	            	Class.forName(driver);
	            	con = DriverManager.getConnection(url, benutzer, passwort);
	            	con.setAutoCommit(false);
            	}
            } 
            catch (Exception e) 
            {
                con = null;
                Protokol.write("Postgres:connect:Exception:");
                Protokol.write(e.toString());
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
    		Protokol.write("Postgres:close:Exception:");
    		Protokol.write(e.toString());
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
    		Protokol.write("Postgres:commit:Exception:");
    		Protokol.write(e.toString());
    	}
    }
    public void rollback()
    {
   		Protokol.write("Postgres:rollback:Will do Rollback:");
    	try
    	{
    		con.rollback();
    	}
    	catch (Exception e)
    	{
    		Protokol.write("Postgres:rollback:Exception:");
    		Protokol.write(e.toString());
    	}
    }
    public ArrayList<SatzTag> getAllTags()
    {
       	if (con == null) connect();
    	ArrayList<SatzTag> erg = new ArrayList<SatzTag>();
        ResultSet rs = null;
        String sql = "select tag,count(*)";
        sql += " from tags";
        sql += " group by tag";
        sql += " order by count(*) desc";
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	SatzTag satz = new SatzTag();
            	satz.setTag(rs.getString(1));
            	satz.setAnzahl(rs.getInt(2));
            	erg.add(satz);
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:getAllTags:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public ArrayList<Long> getAllId()
    {
    	if (con == null) connect();
    	ArrayList<Long> erg = new ArrayList<Long>();
    	ResultSet rs = null;
    	String sql = "select send from email order by send desc";
    	try
    	{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	long id = rs.getLong(1);
            	erg.add(id);
            }
            rs.close();
            stmt.close();
            commit();
    	}
    	catch (Exception e)
    	{
            Protokol.write("Postgres:getAllId:Exception:");
            Protokol.write(e.toString());
            rollback();
    	}
    	return erg;
    }
    public InputStream getBody(long id)
    {
       	if (con == null) connect();
       	InputStream erg = null;
        ResultSet rs = null;
        String sql = "select wert";
        sql += " from body";
        sql += " where id = ?";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	erg = rs.getBinaryStream(1);
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:getBody:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public ArrayList<Long> getOneTag(String tag)
    {
       	if (con == null) connect();
    	ArrayList<Long> erg = new ArrayList<Long>();
        ResultSet rs = null;
        String sql = "select email_id";
        sql += " from tags";
        sql += " where tag = ?";
        sql += " order by email_id desc";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,tag);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	long id = rs.getLong(1);
            	erg.add(id);
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:getOneTag:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public SatzEMail getOneEMail(long id)
    {
       	if (con == null) connect();
    	SatzEMail erg = new SatzEMail();
        ResultSet rs = null;
        String sql = "select send,received,subject,typ,sfrom,sto,groesse";
        sql += " from email";
        sql += " where send = ?";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	erg.setSend(rs.getLong(1));
            	erg.setReceived(rs.getLong(2));
            	erg.setSubject(rs.getString(3));
            	erg.setTyp(rs.getString(4));
            	erg.setSfrom(rs.getLong(5));
            	erg.setSto(rs.getLong(6));
            	erg.setGroesse(rs.getInt(7));
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:getOneEMail:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public ArrayList<SatzEMail> searchEMailSubject(String search)
    {
       	if (con == null) connect();
       	ArrayList<SatzEMail> erg = new ArrayList<SatzEMail>();
       	ArrayList<Long> ids = new ArrayList<Long>();
        ResultSet rs = null;
        String sql = "select send";
        sql += " from email";
        sql += " where subject like ?";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,"%" + search + "%");
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	ids.add(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            for (int i=0;i<ids.size();i++)
            {
            	SatzEMail satz = getOneEMail(ids.get(i));
            	erg.add(satz);
            }
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:searchEMailSubject:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public String getFromList(long id)
    {
       	if (con == null) connect();
    	String erg = "";
        ResultSet rs = null;
        String sql = "select adresse";
        sql += " from sfrom";
        sql += " where id = ?";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	erg += " " +rs.getString(1);
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:getFromList:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public ArrayList<String> getToList(long id)
    {
       	if (con == null) connect();
    	ArrayList<String> erg = new ArrayList<String>();
        ResultSet rs = null;
        String sql = "select adresse";
        sql += " from sto";
        sql += " where id = ?";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	String sto = rs.getString(1);
            	erg.add(sto);
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e)
        {
            Protokol.write("Postgres:getToList:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public void removeTag(String tag)
    {
       	if (con == null) connect();
        String sql = "delete";
        sql += " from tags";
        sql += " where tag = ?";
		try
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tag);
			stmt.executeUpdate();
			stmt.close();
			con.commit();
		}
		catch (Exception e)
		{
			Protokol.write("Postgres:removeTag:Exception:");
			Protokol.write(e.toString());
			rollback();
		}
    }
    public boolean hasSpecialTag(long id)
    {
       	if (con == null) connect();
    	boolean erg = false;
        ResultSet rs = null;
        String sql = "select tag";
        sql += " from tags";
        sql += " where email_id = ?";
        sql += " and tag like ?";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.setString(2,"#%");
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	erg = true;
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:hasSpecialTag:Exception:");
            Protokol.write(e.toString());
            rollback();
            erg = false;
        }
        return erg;
    }
    public String getTags(long id)
    {
       	if (con == null) connect();
    	String erg = "";
        ResultSet rs = null;
        String sql = "select tag";
        sql += " from tags";
        sql += " where email_id = ?";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while (rs.next())
            {
            	erg += " " + rs.getString(1);
            }
            rs.close();
            stmt.close();
            commit();
        } 
        catch (Exception e) 
        {
            Protokol.write("Postgres:getTags:Exception:");
            Protokol.write(e.toString());
            rollback();
        }
        return erg;
    }
    public String insertMessage(Message msg,ArrayList<String> tags,long optionsend)
    {
    	String erg = null;
    	if (con == null) connect();
 		String subject = "unkown";
		String typ = "";
		String sql = "0";
		try
		{
		   	sql = "1";
			subject = msg.getSubject();
		   	sql = "2";
			int igroesse = msg.getSize();
		   	sql = "3";
			Date date_r = msg.getReceivedDate();
		   	sql = "4";
		   	if (date_r == null)
		   	{
		   		if (optionsend == 0) date_r = new Date();
		   		else date_r = new Date(optionsend);
		   	}
			long received = date_r.getTime();
		   	sql = "5";
			Date date_s = msg.getReceivedDate();
		   	sql = "6";
		   	if (date_s == null)
		   	{
		   		if (optionsend == 0) date_s = new Date();
		   		else date_s = new Date(optionsend);
		   	}
			long send = date_s.getTime();
		   	sql = "7";
			typ += msg.getContentType();
		   	sql = "8";
			//
		   	sql = "insert into email (";
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
			File file = new File(Parameter.mail_temp + "GetEMail.eml");
			OutputStream aus = new FileOutputStream(file);
			msg.writeTo(aus);
			aus.close();
			InputStream ein = new FileInputStream(file);
		   	sql = "insert into body (";
	    	sql += " id";
	    	sql += ",wert";
	     	sql += ")";
	    	sql += " values (?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setLong(1,send);
           	if (Parameter.sqlite.equals("true"))
        	{
           		byte[] bytes = new byte[(int)file.length()];
           		bytes = ein.readAllBytes();
           		stmt.setBytes(2,bytes);
        	}
           	else
           	{
           		stmt.setBinaryStream(2,ein,file.length());
           	}
            stmt.executeUpdate();
            stmt.close();
            ein.close();
			con.commit();
		}
		catch (Exception e)
		{
			Protokol.write("Postgres:insertMessage:Exception:");
			Protokol.write(e.toString());
			Protokol.write(sql);
			erg = e.toString();
			rollback();
		}
		return erg;
    }
    public void deleteMessage(long id)
    {
    	String sql = "unkown";
    	if (con == null) connect();
		try
		{
			sql = "delete from tags where email_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			stmt.close();
			sql = "delete from email where send = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			stmt.close();
			sql = "delete from sto where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			stmt.close();
			sql = "delete from sfrom where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			stmt.close();
			sql = "delete from body where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			stmt.close();
			con.commit();
		}
		catch (Exception e)
		{
			Protokol.write("Postgres:deleteMessage:Exception:");
			Protokol.write(e.toString());
			Protokol.write(sql);
			rollback();
		}
    }
    public String changeTags(long id,ArrayList<String> tags)
    {
    	String erg = null;
    	if (con == null) connect();
		try
		{
			String sql = "delete from tags where email_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			stmt.close();
			for (int i=0;i<tags.size();i++)
			{
				String tag = tags.get(i);
			   	sql = "insert into tags (";
		    	sql += " email_id";
		    	sql += ",tag";
		     	sql += ")";
		    	sql += " values (?,?)";
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1,id);
	            stmt.setString(2,tag);
	            stmt.executeUpdate();
	            stmt.close();
			}
			con.commit();
		}
		catch (Exception e)
		{
			Protokol.write("Postgres:changeTags:Exception:");
			Protokol.write(e.toString());
			erg = e.toString();
			rollback();
		}
		return erg;
    }   
}
