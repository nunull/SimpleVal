package de.albers.simpleval.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.albers.simpleval.controller.MainController;

/**
 * The dialog window for selecting the database connection.
 */
public class ConnectionDialog extends JDialog {
	private static final long serialVersionUID = 1279066887797465051L;
	private MainController mainController;
	private ActionListener actionListener;
	private JTextField pathTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private File defaultFile;
	private String defaultDB = "//localhost:3306/simpleval"; //$NON-NLS-1$
	private String defaultUsername = "root"; //$NON-NLS-1$
	private String defaultPassword = "root"; //$NON-NLS-1$

	/**
	 * Constructor.
	 * 
	 * @param mainController The main application controller.
	 */
	public ConnectionDialog(MainController mainController) {
		super((Frame) null, Messages.getString("ConnectionDialog.TITLE")); //$NON-NLS-1$
		
		this.mainController = mainController;
		defaultFile = new File("res/default_connection.properties"); //$NON-NLS-1$
		
		readDefault();
		
		setLayout(new GridBagLayout());
		initListeners();
		initComponents();
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Reads the default database connection from disk.
	 */
	private void readDefault() {
		try {
			BufferedReader r = new BufferedReader(new FileReader(defaultFile));
			
			while(r.ready()) {
				String[] tmp = r.readLine().split("="); //$NON-NLS-1$
				String key = tmp[0];
				String value = tmp[1];
				
				switch(key) {
					case "DB": //$NON-NLS-1$
						defaultDB = value;
						break;
					case "USERNAME": //$NON-NLS-1$
						defaultUsername = value;
						break;
					case "PASSWORD": //$NON-NLS-1$
						defaultPassword = value;
						break;
				}
			}
			
			r.close();
		} catch (IOException | IndexOutOfBoundsException e) {
			// Defaults to hard coded values
		}
	}
	
	/**
	 * Writes the default database connection to the disk.
	 */
	private void writeDefault() {
		String value = "DB=" + pathTextField.getText() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		value += "USERNAME=" + usernameTextField.getText() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		value += "PASSWORD=" + passwordTextField.getText() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		
		try {
			FileWriter w = new FileWriter(defaultFile);
			w.write(value);
			w.flush();
			w.close();
			
			mainController.message(Messages.getString("ConnectionDialog.LANG_DEFAULTSAVED")); //$NON-NLS-1$
		} catch (IOException e) {
			mainController.error(e);
		}
	}
	
	/**
	 * Initializes all event listeners.
	 */
	private void initListeners() {
		this.actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
					case "cancel": //$NON-NLS-1$
						System.exit(0);
						break;
					case "connect": //$NON-NLS-1$
						setVisible(false);
						
						mainController.start(pathTextField.getText(),
								usernameTextField.getText(),
								passwordTextField.getText());
						
						break;
					case "savedefault": //$NON-NLS-1$
						writeDefault();
						
						break;
				}
			}
		};
	}
	
	/**
	 * Initializes all components.
	 */
	private void initComponents() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel(Messages.getString("ConnectionDialog.LANG_DATABASE")), c); //$NON-NLS-1$
		c.gridx = 1;
		pathTextField = new JTextField(defaultDB);
		pathTextField.setPreferredSize(new Dimension(300, 30));
		add(pathTextField, c);
		
		c.gridy = 1;
		c.gridx = 0;
		add(new JLabel(Messages.getString("ConnectionDialog.LANG_USERNAME")), c); //$NON-NLS-1$
		c.gridx = 1;
		usernameTextField = new JTextField(defaultUsername);
		usernameTextField.setPreferredSize(new Dimension(300, 30));
		add(usernameTextField, c);
		
		c.gridy = 2;
		c.gridx = 0;
		add(new JLabel(Messages.getString("ConnectionDialog.LANG_PASSWORD")), c); //$NON-NLS-1$
		c.gridx = 1;
		passwordTextField = new JTextField(defaultPassword);
		passwordTextField.setPreferredSize(new Dimension(300, 30));
		add(passwordTextField, c);
		
		c.gridy = 3;
		c.gridx = 0;
		JButton button = new JButton(Messages.getString("ConnectionDialog.LANG_CANCEL")); //$NON-NLS-1$
		button.setPreferredSize(new Dimension(150, 30));
		button.setActionCommand("cancel"); //$NON-NLS-1$
		button.addActionListener(actionListener);
		add(button, c);
		
		c.gridx = 1;
		button = new JButton(Messages.getString("ConnectionDialog.LANG_CONNECT")); //$NON-NLS-1$
		button.setPreferredSize(new Dimension(300, 30));
		button.setActionCommand("connect"); //$NON-NLS-1$
		button.addActionListener(actionListener);
		add(button, c);
		
		c.gridx = 2;
		button = new JButton(Messages.getString("ConnectionDialog.LANG_SAVEDEFAULT")); //$NON-NLS-1$
		button.setPreferredSize(new Dimension(150, 30));
		button.setActionCommand("savedefault"); //$NON-NLS-1$
		button.addActionListener(actionListener);
		add(button, c);
	}
}
