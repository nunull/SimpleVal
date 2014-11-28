package de.albers.simpleval.db;

import org.sql2o.Sql2o;

import de.albers.simpleval.controller.MainController;

/**
 * Concrete database implementation for MySQL.
 */
public class MySQL extends DB {

	public MySQL(MainController mainController, String path, String username, String password) {
		super(mainController, path, username, password);
	}

	/**
	 * Initializes the connection object.
	 * 
	 * @param path The (e.g. the URL) path to the database
	 * @param username The username to connect with.
	 * @param password the password to use.
	 */
	@Override
	protected void initSQL(String path, String username, String password) {
		this.sql = new Sql2o("jdbc:mysql:" + path, "root", "root");
	}
}
