package org.myoggradio;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import com.sun.mail.imap.IMAPFolder;
public class Verarbeitung extends Thread
{
	private IMAPFolder folder = null;
	public void setFolder(IMAPFolder folder)
	{
		this.folder = folder;
	}
	public void run()
	{
		try
		{
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect(Parameter.mail_server,Parameter.mail_user,Parameter.mail_passwort);
			folder = (IMAPFolder) store.getFolder(Parameter.mail_folder);
			ArrayList<IMAPFolder> alfolder = new ArrayList<IMAPFolder>();
			addFolder(folder,alfolder);
			FolderDialog fm = new FolderDialog(alfolder,this);
			fm.anzeigen();
			if (folder != null)
			{
				if (!folder.isOpen())
					folder.open(Folder.READ_WRITE);
				Message[] messages = folder.getMessages();
				ArrayList<Message> al = new ArrayList<Message>();
				for (int i=0;i<messages.length;i++)
				{
					al.add(messages[i]);
				}
				al.sort(new MessageComparator());
				MessageMenu mm = new MessageMenu(al,folder);
				mm.anzeigen();
			}
		}
		catch (Exception e)
		{
			System.out.println("Verarbeitung:start:Exception:");
			System.out.println(e.toString());
		}
	}
	public void addFolder(IMAPFolder folder,ArrayList<IMAPFolder> alfolder)
	{
		try
		{
			alfolder.add(folder);
			System.out.println(folder.getURLName());
			Folder[] folders = folder.list();
			for (int i=0;i<folders.length;i++)
			{
				IMAPFolder subfolder = (IMAPFolder) folders[i];
				addFolder(subfolder,alfolder);
			}
		}
		catch (Exception e)
		{
			System.out.println("Verarbeitung:addFolder");
			System.out.println(e.toString());
		}
	}
}
