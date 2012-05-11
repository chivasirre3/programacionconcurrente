package problem7;

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
		for (int i = 0; i < 5; i++) {
		//while(true){	
			try {
				Thread.sleep(new Random().nextInt(1000) + 500);
				Biblioteca.llegarLector(this);//en este metodo se hacen los otros 2(Leer e Irse)
				Biblioteca.leer(this);
				Biblioteca.lectorSeVa(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
