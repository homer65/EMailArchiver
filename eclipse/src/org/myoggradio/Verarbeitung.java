package org.myoggradio;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import com.sun.mail.imap.IMAPFolder;

public class Verarbeitung {
	public void start() throws Exception 
	{
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect(Parameter.mail_server,Parameter.mail_user,Parameter.mail_passwort);
		IMAPFolder folder = (IMAPFolder) store.getFolder("inbox");
		if (!folder.isOpen())
			folder.open(Folder.READ_ONLY);
		Message[] messages = folder.getMessages();
		ArrayList<Message> al = new ArrayList<Message>();
		for (int i=0;i<messages.length;i++)
		{
			al.add(messages[i]);
		}
		al.sort(new MessageComparator());
		MessageMenu mm = new MessageMenu(al);
		mm.anzeigen();
	}
}
