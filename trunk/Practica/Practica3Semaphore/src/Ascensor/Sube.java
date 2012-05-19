package Ascensor;

public class Sube extends Thread implements Persona{
	
	private String nombre;
	private Ascensor ascensor;
	
	public Sube(String nombre, Ascensor ascensor){
		this.nombre=nombre;
		this.ascensor= ascensor;
	}
	
	
	public String toString(){
		return this.nombre;
	}
	public void run(){
		this.ascensor.irAPlantaAlta(this);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.ascensor.descenderPlantaAlta(this);
	}
}
