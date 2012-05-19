package ferry;

public class Auto extends Thread {

	// Variables
	private String nombre;
	private Ferry ferry;

	// Constructor
	public Auto(String nombre, Ferry ferry) {
		this.nombre = nombre;
		this.ferry=ferry;
	}

	// Override de toString que imprime el nombre del auto
	public String toString() {
		return this.nombre;
	}

	public void run(){
		while(true){
		//Abordo el barco
		ferry.abordar(this);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Me Bajo del barco
		ferry.desabordar(this);
		}
	}
}
