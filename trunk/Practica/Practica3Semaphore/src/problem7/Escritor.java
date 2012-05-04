package problem7;

import java.util.Random;

public class Escritor extends Thread{

	public String nombre;
	
	public Escritor(String nombre){
		this.nombre=nombre;
	}
	
	public void run() {
		super.run();
		try {
			Thread.sleep(new Random().nextInt(1000)+500);
			Biblioteca.llegarEscritor();
			Biblioteca.escribir(this);
			Biblioteca.escritorSeVa();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
