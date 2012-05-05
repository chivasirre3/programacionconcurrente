package Problem5Hemodialisis;

import java.util.concurrent.Semaphore;

public class Paciente {

	//Variables
	private Thread threadRevista; //Representa el que espera leer revistas y puede ser interrumpido por el thread de las camillas
	private Thread threadCamilla; //Representa el que espera por una camilla libre en la sala.
	private Boolean tieneRevista; //para saber si esta leyendo.
	private Boolean estaEnCamilla;//Si se encuentra en la camilla
	private Boolean seFue; //Si ya paso por la sala y termino de usar la camilla.
	private Semaphore mutex;
	private String nombre;
	private Clinica clinica;

	//Contructor
	public Paciente(final String nombre, final Clinica clinica) {
		this.mutex = new Semaphore(1);
		this.nombre = nombre;
		this.clinica = clinica;
		this.tieneRevista = false;
		this.estaEnCamilla = false;
		this.seFue=false;
	}
	/**
	 * Este metodo se llama cuando se interrumpe el Thread y esta para que si el paciente
	 * tiene una revista la devuelva.
	 */
	public void devolverRevista() {
		if(tieneRevista){
			tieneRevista=false;
			clinica.revistero.release();
		}
	}
	
	/**
	 * Le doy el comportamiento a  los 2 thread y los hace Correr los 2 threads
	 */
	public void entrarAClinica() {
		this.threadRevista = new Thread(new Runnable() {

			public void run() {
				while (!estaEnCamilla && !seFue ) {
					try {
						System.out.println("Espera la revista" + nombre);
						clinica.revistero.acquireUninterruptibly();
						System.out.println("Lee la revista" + nombre);
						tieneRevista = true;
						Thread.sleep(2000);
						tieneRevista = false;
						System.out.println("Dejo la revista" + nombre);
						clinica.revistero.release();

					} catch (InterruptedException e) {
						System.out.println("Se Interrumpio La Lectura porque Fue Atendido: " + nombre);
					}
				}
			}
		});
		this.threadCamilla = new Thread(new Runnable() {

			public void run() {
				try {
					System.out.println("Esperando a entrear a  Sala " + nombre);
					clinica.sala.acquireUninterruptibly();
					if (tieneRevista) {
						threadRevista.interrupt();
						devolverRevista();
					}
					System.out.println("Entro a Sala" + nombre);
					estaEnCamilla = true;
					Thread.sleep(2000);
					System.out.println("Salio De Sala " + nombre);
					clinica.sala.release();
					estaEnCamilla=false;
					seFue = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		threadCamilla.start();
		threadRevista.start();
	}

	

}
