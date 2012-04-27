package model;

public abstract class Persona extends Thread {

	// Variables
	private String nombre;
	private Banio banio;

	// Getters & Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Banio getBanio() {
		return banio;
	}
	public void setBanio(Banio banio) {
		this.banio = banio;
	}
}
