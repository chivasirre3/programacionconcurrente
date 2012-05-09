package problem7;

import java.util.Random;

public class Lector extends Thread {

	public String nombre;

	public Lector(String nombre) {
		this.nombre = nombre;
	}

	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(new Random().nextInt(1000) + 500);
				Biblioteca.llegarLector();
				Biblioteca.leer(this);
				Biblioteca.lectorSeVa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
