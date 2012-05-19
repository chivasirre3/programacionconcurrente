package ferry;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Ferry extends Thread {

	private Integer cantAutosMax;
	private Integer cantAutosDentro;
	private Semaphore mutex;
	private Semaphore carga;
	private Semaphore descarga;

	public Ferry(Integer cantidadMax) {
		this.cantAutosDentro = 0;
		this.cantAutosMax = cantidadMax;
		this.carga = new Semaphore(cantidadMax, true);
		this.descarga = new Semaphore(0);
		this.mutex = new Semaphore(1);
	}

	public void abordar(Auto auto) {
		carga.acquireUninterruptibly();
		try {
			Thread.sleep(new Random().nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		mutex.acquireUninterruptibly();
		cantAutosDentro++;
		System.out.println("El Auto: " + auto + "Abordo");
		mutex.release();


	}

	public void desabordar(Auto auto) {
		descarga.acquireUninterruptibly();
		try {
			Thread.sleep(new Random().nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mutex.acquireUninterruptibly();
		cantAutosDentro--;
		System.out.println("El Auto: " + auto + "Desabordo");
		if (cantAutosDentro == 0) {//Si es ultimo auto, libero todos los permisos de carga
			System.out.println("Ferry Libre para la carga");
			carga.release(this.cantAutosMax);
		}
		mutex.release();

	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				
				//Se duerme un 1 segundo hasta que halla al menos 50 autos dentro
				while(cantAutosDentro<50){
					Thread.sleep(200);
				}
				
				// Corta la carga y se duerme simulando un viaje.
				mutex.acquireUninterruptibly();
				if(cantAutosDentro<cantAutosMax){//Si hay menos del maximo.
					this.carga.acquireUninterruptibly(this.cantAutosMax-this.cantAutosDentro); //Adquiero los permisos que quedan para la carga.
				}
				System.out.println("Viajando");
				mutex.release();

				Thread.sleep(2000);//Duermo 2 segundoss
				System.out.println("Llego A Destino");
				System.out.println("Descargando");
				
				mutex.acquireUninterruptibly();
				this.descarga.release(this.cantAutosMax);//libero todos los permisos para descargar
				mutex.release();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
