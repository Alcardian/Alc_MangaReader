package se.alcardian.manga_reader;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AMR_Frame extends JFrame{
	private String title = "Alcardian Manga Reader";
	private int withd = 500;
	private int height = 98;
	
	
	// Objects
	private JPanel mainPanel = new JPanel();
	private JTextField urlField = new JTextField();
	private JTextField locationField = new JTextField();
	private JButton dlButton = new JButton("Download");
	private JButton locationButton = new JButton("Set Save Location");
	private JTextArea textArea = new JTextArea();

	public AMR_Frame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		setSize(withd, height);	// X, Y
		setResizable(false);
		add(mainPanel);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// URL Textfield
		//urlField.setSize(100, 50);
		urlField.setPreferredSize(new Dimension(300, 28));
		urlField.setToolTipText("e.g. http://www.mangareader.net/log-horizon");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;	// Added so there isn't a wierd space between textfield & button
		mainPanel.add(urlField, c);
		
		// Button
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(dlButton, c);
		
		// Location Textfield
		locationField.setPreferredSize(new Dimension(300, 28));
		locationField.setText("E:\\MangaReader\\");
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(locationField, c);
		
		// Location Button
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(locationButton, c);
		
		// Textarea
		textArea.setSize(500, 100);
		textArea.setLineWrap(true);
	    textArea.setEditable(false);
	    textArea.setVisible(true);
		JScrollPane scrollV = new JScrollPane (textArea);
		scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		//mainPanel.add(textArea, c);
		
		//ActionListener
		ButtonListener bListener = new ButtonListener();
		dlButton.addActionListener(bListener);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == dlButton){
				// Do something?
				//ZonedDateTime zdt = ZonedDateTime.now();
				//System.out.println(zdt.toString() + ": Hey! Listen!");
				Alc_MangaReader_Main.downloadChapters(urlField.getText(), locationField.getText(), 100);
			}
		}
	}
}
