package Ascensor;

public class Baja extends Thread implements Persona{

	private String nombre;
	private Ascensor ascensor;
	
	public Baja(String nombre, Ascensor ascensor){
		this.nombre=nombre;
		this.ascensor= ascensor;
	}
	
	
	public String toString(){
		return this.nombre;
	}
	public void run(){
		this.ascensor.irAPlantaBaja(this);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.ascensor.descenderPlantaBaja(this);
	}
}
