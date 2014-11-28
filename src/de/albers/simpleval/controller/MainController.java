package de.albers.simpleval.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import de.albers.simpleval.db.DB;
import de.albers.simpleval.db.MySQL;
import de.albers.simpleval.db.dto.Material;
import de.albers.simpleval.gui.ConnectionDialog;
import de.albers.simpleval.gui.LoadingDialog;
import de.albers.simpleval.gui.MainWindow;

public class MainController {
	public MainController() {
		final MainController m = this;
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ConnectionDialog connectionDialog = new ConnectionDialog(m);
				connectionDialog.setVisible(true);
			}
		});
	}
	
	public void start(String path, String username, String password) {
		final LoadingDialog loadingDialog = new LoadingDialog();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				loadingDialog.setVisible(true);
			}
		});
				
		DB db = new MySQL(this, path, username, password);
		
		final List<Material> materials = db.getAllMaterials();
		db.close();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				loadingDialog.setVisible(false);
				
				MainWindow mainWindow = new MainWindow(materials);
				mainWindow.setVisible(true);
			}
		});
		
	}
	
	public void message(String message) {
		JOptionPane.showMessageDialog(null, message, "SimpleVal: Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void error(Exception e) {
		JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "SimpleVal: Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}
