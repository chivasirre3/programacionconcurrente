package problem7;

import java.util.Random;

public class Escritor extends Thread {

	public String nombre;

	public Escritor(String nombre) {
		this.nombre = nombre;
	}
	public String toString(){
		return this.nombre;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
		//while(true) {
			try {
				Thread.sleep(new Random().nextInt(500) + 300);
				Biblioteca.llegarEscritor(this);//en este metodo se hacen los otros 2(escribir e Irse)
				System.out.println(nombre + "Empieza a escribir");
				
				Thread.sleep(new Random().nextInt(500) + 300);
				
				System.out.println(nombre + "termina de escribir");
				Biblioteca.escritorSeVa(this);
				//Biblioteca.escribir(this);
				//Biblioteca.escritorSeVa(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
