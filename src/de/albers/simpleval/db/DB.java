package de.albers.simpleval.db;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import de.albers.simpleval.controller.MainController;
import de.albers.simpleval.db.dto.Datum;
import de.albers.simpleval.db.dto.Material;

/**
 * Abstract class representing a database
 */
public abstract class DB {
	protected Sql2o sql;
	private Connection con;
	
	/**
	 * Constructor.
	 * 
	 * @param mainController The main application controller.
	 * @param path The (e.g. the URL) path to the database
	 * @param username The username to connect with.
	 * @param password the password to use.
	 */
	public DB(MainController mainController, String path, String username, String password) {
		initSQL(path, username, password);
		
		try {
			con = sql.open();
		} catch(Sql2oException e) {
			mainController.error(e);
		}
	}
	
	/**
	 * Initializes the connection object.
	 * 
	 * @param path The (e.g. the URL) path to the database
	 * @param username The username to connect with.
	 * @param password the password to use.
	 */
	protected abstract void initSQL(String path, String username, String password);
	
	/**
	 * Closes the connection.
	 */
	public void close() {
		con.close();
	}
	
	/**
	 * Returns all materials.
	 * 
	 * @return The materials.
	 */
	public List<Material> getAllMaterials() {
		List<Material> materials = con.createQuery("select material_id id, name from material").executeAndFetch(Material.class);
		
		for(Material m : materials) {
			m.setData(getDataByMaterial(m.getId()));
		}
		
		return materials;
	}
	
	/**
	 * Returns all data.
	 * 
	 * @return The data.
	 */
	public List<Datum> getAllData() {
		List<Material> materials = getAllMaterials();
		List<Datum> data = con.createQuery("select daten_id id, mat_id material, u, i from daten").executeAndFetch(Datum.class);
		
		// Map Material to Data
		for(Datum d : data) {
			int materialId = d.getMaterial();
			for(Material m : materials) {
				if(m.getId() == materialId) {
					d.setMaterialObj(m);
				}
			}
		}
		
		return data;
	}
	
	/**
	 * Returns the material associated with the given ID.
	 * 
	 * @param material The ID of the material to be returned.
	 * @return The material.
	 */
	public List<Datum> getDataByMaterial(int material) {
		return con.createQuery("select daten_id id, mat_id material, u, i from daten where mat_id = :matId")
			.addParameter("matId", material)
			.executeAndFetch(Datum.class);
	}
}
