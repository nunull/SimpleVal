package de.albers.simpleval.db.dto;

import java.util.List;

/**
 * Plain old java (data transfer) object representing mnaterial.
 */
public class Material {
	private int id;
	private String name;
	private List<Datum> data;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Datum> getData() {
		return data;
	}

	public void setData(List<Datum> data) {
		this.data = data;
	}
}
