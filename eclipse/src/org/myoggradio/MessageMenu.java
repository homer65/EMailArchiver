package org.myoggradio;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;
import javax.mail.Message;
import com.sun.mail.imap.IMAPFolder;
public class MessageMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	public MessageMenu(ArrayList<Message> al,IMAPFolder folder)
	{
		super("Message Menu");
		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
            public void windowClosing(WindowEvent we)
            {
            	try
            	{
            		System.out.println("MessageMenu::windowClosing:Will close Folder");
            		folder.close(true);
            	}
            	catch (Exception e)
            	{
            		System.out.println("MessageMenu::windowClosing:Exception:");
            		System.out.println(e.toString());
            	}
                dispose();
            }
        });
		Message[] messages = new Message[al.size()];
		for (int i=0;i<al.size();i++)
		{
			messages[i] = al.get(i);
		}
		JList<Message> list = new JList<Message>(messages);
		ListCellRenderer<Message> renderer = new MyCellRenderer();
		list.setCellRenderer(renderer);
		ListSelectionListener listener = new MyListSelectionListener(list);
		list.addListSelectionListener(listener);
		JScrollPane span = new JScrollPane(list);
		span.setPreferredSize(new Dimension(800,800));
		JPanel cpan = new JPanel();
		cpan.add(span);
		setContentPane(cpan);
	}
	public void anzeigen()
	{
		pack();
		setVisible(true);
	}
}
