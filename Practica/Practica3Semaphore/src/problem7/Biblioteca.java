package problem7;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Biblioteca {
	
	private static Lock lock= new ReentrantLock(true);
	public static Condition lectores= lock.newCondition();
	public static Condition escritores= lock.newCondition();
	private static Integer lectoresLeyendo=0;
	private static Boolean escritorEscribiendo=false;
	public static List<Tupla> cola= new LinkedList<Tupla>();
	
	/**
	 * El lector llega y lee. si no puede leer porque hay esperando o escribiendo 
	 * este se pone a esperar en la cola.
	 * @param Lector lector
	 */
	public  static void llegarLector(Lector lector){
		lock.lock();
		if(!escritorEscribiendo && cola.isEmpty()){
			lectoresLeyendo++;
			}
		else{
			dormirLector();
			}
		lock.unlock();
		leer(lector);
	}
	/**
	 * El lector lee y se va.
	 * @param lector
	 */
	public  static void leer(Lector lector) {
		System.out.println("El Lector:" + lector.nombre + " Lee.");
		lectorSeVa(lector);
	}
	/**
	 * EL Lector Se va y libera si hay otros esperando.
	 */
	public  static void lectorSeVa(Lector lector){
		lock.lock();
		System.out.println("El Lector:" + lector.nombre + " termino de leer.");
		lectoresLeyendo--;
		if(!cola.isEmpty() && cola.get(0).escritor && lectoresLeyendo==0){ //si el valor de la tupla es de un escritor entonces libero 
			for (int i = 0; i < cola.get(0).cantidad ; i++) {
				escritores.signal();

			}
			cola.remove(0);
		}
		else if(!cola.isEmpty() && cola.get(0).lector){
			for (int j = 0; j < cola.get(0).cantidad; j++) {
				lectores.signal();
			}
			cola.remove(0);
		}
		lock.unlock();
}
	/**
	 * Este metodo Duerme al lector.
	 * Si la ultima tupla que hay en la lista pertenece a los lectores, este suma uno mas.
	 * Si no, crea una nueva tupla que representa a los lectores y la agrega al final de la lista.
	 * Siempre chequeando que la lista no sea vacia para no pinchar. 
	 */
	private static void dormirLector() {
		lock.lock();
		if(!Biblioteca.cola.isEmpty() && Biblioteca.cola.get(Biblioteca.cola.size()-1).lector){
			Biblioteca.cola.get(Biblioteca.cola.size()-1).cantidad+=1; 
		}
		else{
			Tupla nueva=  new Tupla(true , false);
			cola.add(nueva);
			try {
				lectores.await();
				lectoresLeyendo++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
	}

	/**
	 * El Escritor llega  y escribe, siempre y cuando no haya otro escribiendo o lectores o que hayan
	 * esperando en la cola.
	 * @param Escritor escritor
	 */
	public static void llegarEscritor(Escritor escritor){
		lock.lock();
		if(!escritorEscribiendo && lectoresLeyendo == 0 && cola.isEmpty()){
			escritorEscribiendo=true;
		}
		else{
			dormirEscritor();
			}
		escribir(escritor);
		lock.unlock();
	}
	/**
	 * Se duerme al escritor. 
	 * Si la ultima tupla que hay en la lista pertenece a los escritores, este suma uno mas.
	 * Si no, crea una nueva tupla que representa a los escritores y la agrega al final de la lista.
	 * Siempre chequeando que la lista no sea vacia para no pinchar.
	 */
	public static void dormirEscritor() {
		lock.lock();
		if(!Biblioteca.cola.isEmpty() && Biblioteca.cola.get(Biblioteca.cola.size()-1).escritor){//Si no es vacia y es un escritor
			Biblioteca.cola.get(Biblioteca.cola.size()-1).cantidad+=1;
		}
		else{
			Tupla nueva=  new Tupla(false , true);
			cola.add(nueva);
			try {
				escritores.await();
				escritorEscribiendo=true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
	/**
	 * El escritor escribe y despues se va.
	 * @param Escritor escritor
	 */
	public  static void escribir(Escritor escritor) {
		lock.lock();
				System.out.println("El Escritor:" + escritor.nombre + " Esta Escribiendo.");
				Biblioteca.escritorSeVa(escritor);
		lock.unlock();
	}
	/**
	 * El Escritor se retira y si hay en cola de espera los despierta.
	 * @param Escritor escritor
	 */
	public static void escritorSeVa(Escritor escritor){
		lock.lock();
		System.out.println("El Escritor:" + escritor.nombre + " Termino De Escribir.");
		escritorEscribiendo=false;
		if(!cola.isEmpty() && cola.get(0).lector){ //si el valor de la tupla es de un lector entonces libero lectores
			for (int i = 0; i < cola.get(0).cantidad ; i++) {
				lectores.signal();
			}
			cola.remove(0);
		}
		else if(!cola.isEmpty() && cola.get(0).escritor){
			for (int j = 0; j < cola.get(0).cantidad; j++) {
				escritores.signal();
			}
			cola.remove(0);
		}
		lock.unlock();
	}
	
	
	
	
}
