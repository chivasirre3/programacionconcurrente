package model;

import java.util.List;

/**
 * Esta clase Representa al Recorrido que efectuan los trenes. El Recorrido de Berazategui a Once Y A La Inversa son 2 recorridos diferentes, si bien son basicamente
 * la misma lista de estaciones pero en sentido inverso, la idea seria representarlos en 2 recorridos diferentes. 
 * @author  Martin Melo
 *
 */
public class Recorrido {

	//Variables
	private List<Estacion> estaciones;
	private String nombre;
	
	//Constructor
	public Recorrido(String nombre , List<Estacion> estaciones){
		this.nombre= nombre;
		this.setEstaciones(estaciones);
	}

	//Metodos
	/**
	 * Agrega una Estacion Al recorrido
	 * @param estacion
	 */
	public void agregarEstacion(Estacion estacion){
		this.getEstaciones().add(estacion);
	}
	/**
	 * Override de toString que imprime el nombre del recorrido
	 */
	public String toString(){
		return nombre;
	}
	//Getters & Setters
	public List<Estacion> getEstaciones() {
		return estaciones;
	}
	public void setEstaciones(List<Estacion> estaciones) {
		this.estaciones = estaciones;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
