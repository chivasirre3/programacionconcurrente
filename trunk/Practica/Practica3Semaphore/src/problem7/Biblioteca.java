package problem7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Biblioteca {

	private static Lock lock = new ReentrantLock(true);
	public static Condition lectores = lock.newCondition();
	public static Condition escritores = lock.newCondition();
	private static Integer lectoresLeyendo = 0;
	private static Boolean escritorEscribiendo = false;
	public static List<Tupla> cola = new ArrayList<Tupla>();

	public static void llegarLector(Lector lector) {
		lock.lock();
		if(escritorEscribiendo || !cola.isEmpty()) {
			dormirLector();
		}
		else{
			lectoresLeyendo++;			
		}
		lock.unlock();
	}


	/**
	 * El lector lee y se va.
	 * 
	 * @param lector
	 */
	public static void leer(Lector lector) {
		System.out.println("El Lector:" + lector.nombre + " Lee.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * EL Lector Se va y libera escritores si hay  esperando.
	 */
	public static void lectorSeVa(Lector lector) {
		lock.lock();
		System.out.println("El Lector:" + lector.nombre + " termino de leer.");
		lectoresLeyendo--;

		System.out.println("");
		System.out.println("Se setea lectoresLeyendo = " + lectoresLeyendo);
		System.out.println("");
		
		if (!cola.isEmpty()){
			if(lectoresLeyendo == 0){
				if(cola.get(0).escritor) { 
					cola.get(0).cantidad--;
					escritores.signal();
					if (cola.get(0).cantidad == 0) {
						cola.remove(0);
					}
				} 
			}
		}
		lock.unlock();
	}

	/**
	 * Este metodo Duerme al lector. Si la ultima tupla que hay en la lista
	 * pertenece a los lectores, este suma uno mas. Si no, crea una nueva tupla
	 * que representa a los lectores y la agrega al final de la lista. Siempre
	 * chequeando que la lista no sea vacia para no pinchar.
	 */
	private static void dormirLector() {
		lock.lock();
		if (!Biblioteca.cola.isEmpty()	&& Biblioteca.cola.get(Biblioteca.cola.size() - 1).lector) {
			Biblioteca.cola.get(Biblioteca.cola.size() - 1).cantidad += 1;
		} 
		else {
			Tupla nueva = new Tupla(true, false);
			cola.add(nueva);
		}

		try {
			lectores.await();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		lock.unlock();
	}

	/**
	 * El Escritor llega y escribe, siempre y cuando no haya otro escribiendo o
	 * lectores o que hayan esperando en la cola.
	 * 
	 * @param Escritor
	 *            escritor
	 */
	public static void llegarEscritor(Escritor escritor) {
		lock.lock();
		if (escritorEscribiendo || (lectoresLeyendo > 0) || !cola.isEmpty()) {
			dormirEscritor();
		}
		escritorEscribiendo = true;
		lock.unlock();
	}

	/**
	 * Se duerme al escritor. Si la ultima tupla que hay en la lista pertenece a
	 * los escritores, este suma uno mas. Si no, crea una nueva tupla que
	 * representa a los escritores y la agrega al final de la lista. Siempre
	 * chequeando que la lista no sea vacia para no pinchar.
	 */
	public static void dormirEscritor() {
		lock.lock();
		if (!Biblioteca.cola.isEmpty() && Biblioteca.cola.get(Biblioteca.cola.size() - 1).escritor) {
			try {
				Biblioteca.cola.get(Biblioteca.cola.size() - 1).cantidad += 1;
				escritores.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} else {
			Tupla nueva = new Tupla(false, true);
			cola.add(nueva);
			try {
				escritores.await();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		lock.unlock();
	}

	public static void escribir(Escritor escritor) {
		lock.lock();
		System.out.println("El Escritor:" + escritor.nombre	+ " Esta Escribiendo.");
		lock.unlock();
	}

	public static void escritorSeVa(Escritor escritor) {
		lock.lock();
		System.out.println("El Escritor:" + escritor.nombre + " Termino De Escribir.");
		escritorEscribiendo = false;
		if (!cola.isEmpty()){
			if(cola.get(0).lector) { 
				lectoresLeyendo = cola.get(0).cantidad;
				System.out.println("");
				System.out.println("Se setea lectoresLeyendo = " + lectoresLeyendo);
				System.out.println("");
				for (int i = 0; i < cola.get(0).cantidad; i++) {
					lectores.signal();
				}
				cola.remove(0);
			}
			else {
				cola.get(0).cantidad-=1;
				escritorEscribiendo = true;
				if (cola.get(0).cantidad == 0) {
					cola.remove(0);
				}
				escritores.signal();
			}
		}
		lock.unlock();
	}

	public static void main(String[] args) {

			
		for (int i = 0; i < 500; i++) {
			new Lector("Lector" + i).start();
			if(i%10 == 0){
				new Escritor("Escritor" + i/10).start();
			}
		}
	}
}
