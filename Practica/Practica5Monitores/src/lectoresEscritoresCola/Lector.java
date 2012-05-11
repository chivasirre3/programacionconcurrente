package lectoresEscritoresCola;

import java.util.Random;

public class Lector extends Thread {

	public String nombre;

	public Lector(String nombre) {
		this.nombre = nombre;
	}
	public String toString(){
		return this.nombre;
	}

	public void run() {
		while(true){	
			try {
				Thread.sleep(new Random().nextInt(1000) + 500);
				Biblioteca.leer(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
