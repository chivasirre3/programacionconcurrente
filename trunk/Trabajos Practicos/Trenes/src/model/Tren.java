package model;

import java.util.List;

public class Tren extends Thread{

	//Variables
	private String numero;
	private List<Recorrido> recorridos;
	
	//Constructor
	public Tren(String numero, List<Recorrido> recorridos){
		this.numero=numero;
		this.recorridos=recorridos;
	}
	
	//Metodos
	/**
	 * Agrega el recorrido a la lista que ya tiene el tren
	 * @param recorrido
	 */
	public void agregarRecorrido(Recorrido recorrido){
		this.getRecorridos().add(recorrido);
	}
	
	/**
	 * Hace Iniciar Todos los Recorrido del tren por toda la lista de estaciones. Al Terminar Un Recorrido Continua al Siguiente.
	 * Se Podria Considerar que el tren al momento de comenzar sus recorridos ya tiene todos.
	 */
	@Override
	public void run() {
		for (Recorrido recorrido : this.getRecorridos()) {
			System.out.println("Comienzo De Recorrido: " + recorrido + " Para El Tren: " + this);
			for (Estacion estacion : recorrido.getEstaciones()) {
				this.simularPasoDelTiempo(400);
				this.simularPasoPorEstacion(estacion);
			}
		System.out.println("Fin Del Recorrido:" + recorrido +" Del Tren:" + this);
		}
		System.out.println("Fin De Todos Los Recorridos Para El Tren: "  + this);
	}

	/**
	 * Simula el paso por una estacion, primero pide permiso para entrar y despues simula un demora por carga de pasajeros
	 * que no son mas que sleeps del thread.
	 * @param Estacion estacion
	 */
	private void simularPasoPorEstacion(Estacion estacion) {
		try {
			estacion.pedirPermisoDeIngreso();
			System.out.println("Tren:" + this +" Entrando a Estacion:" + estacion);
			this.simularCargaDePasajeros();
			System.out.println("Tren:" + this + " Saliendo de Estacion:" + estacion);
			estacion.liberarPermisoDeIngreso();
		} catch (InterruptedException e) {
			e.printStackTrace();
			estacion.release();
			System.out.println("Choque En La Estacion:" + estacion);
		}
		
	}

	/**
	 * Duerme 2 segundos al Thread simulando carga de pasajeros .
	 */
	private void simularCargaDePasajeros() {
		this.simularPasoDelTiempo(2000);
	}


	/**
	 * Tira a dormir n segundos el Thread para simular un paso del tiempo.
	 * Ya sea para simular 
	 */
	private void simularPasoDelTiempo(Integer tiempo) {
		try {
			Tren.sleep(tiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Exploto Todo");
		}
	}

	/**
	 * Override de toString que imprime el numero del Tren unicamente
	 * @return String numero
	 */
	public String toString(){
		return this.getNumero();
	}
	
	//Getters & Setters
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public List<Recorrido> getRecorridos() {
		return recorridos;
	}
	public void setRecorridos(List<Recorrido> recorridos) {
		this.recorridos = recorridos;
	}
}
