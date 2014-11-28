package de.albers.simpleval.db.dto;

/**
 * Plain old java (data transfer) object representing a datum.
 */
public class Datum {
	private int id;
	private int material;
	private Material materialObj;
	private float u;
	private float i;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMaterial() {
		return material;
	}
	
	public void setMaterial(int material) {
		this.material = material;
	}
	
	public float getU() {
		return u;
	}
	
	public void setU(float u) {
		this.u = u;
	}
	
	public float getI() {
		return i;
	}
	
	public void setI(float i) {
		this.i = i;
	}

	public Material getMaterialObj() {
		return materialObj;
	}

	public void setMaterialObj(Material materialObj) {
		this.materialObj = materialObj;
	}
}
