package problem7;

import java.util.Random;

public class Escritor extends Thread {

	public String nombre;

	public Escritor(String nombre) {
		this.nombre = nombre;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(new Random().nextInt(1000) + 500);
				Biblioteca.llegarEscritor();
				Biblioteca.escribir(this);
				Biblioteca.escritorSeVa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
