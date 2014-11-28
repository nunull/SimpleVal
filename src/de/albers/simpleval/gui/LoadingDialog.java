package de.albers.simpleval.gui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * A window shown to indicate loading processes.
 */
public class LoadingDialog extends JDialog {
	private static final long serialVersionUID = 6164019336532942291L;

	public LoadingDialog() {
		super((Frame) null, "SimpleVal: Loading");
		
		add(new JLabel(Messages.getString("LoadingDialog.LANG_LOADING"))); //$NON-NLS-1$
		
		setSize(new Dimension(200, 100));
		setLocationRelativeTo(null);
	}
}
