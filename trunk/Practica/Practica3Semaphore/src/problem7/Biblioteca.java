package problem7;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Biblioteca {
	
	private static Lock lock= new ReentrantLock(true);
	private static Condition lectores= lock.newCondition();
	private static Condition escritores= lock.newCondition();
	private static Integer lectoresLeyendo=0;
	private static Boolean escritorEscribiendo=false;
	private static List<Tupla> cola= new LinkedList<Tupla>();
	
	
	public  static void llegarLector(){
		lock.lock();
		if(!escritorEscribiendo){
			lectoresLeyendo++;
			}
		else{
			dormirLector();
			}
		lock.unlock();
	}
	public  static void leer(Lector lector) {
		System.out.println("El Lector:" + lector.nombre + " Leyo.");
	}
	public  static void lectorSeVa(){
		lock.lock();
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

	private static void dormirLector() {
		lock.lock();
		if(!Biblioteca.cola.isEmpty() && Biblioteca.cola.get(Biblioteca.cola.size()-1).lector){//Si no es vacia y es un escritor
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

	
	public static void llegarEscritor(){
		lock.lock();
		if(lectoresLeyendo == 0){
			escritorEscribiendo=true;
		}
		else{
			dormirEscritor();
			}
		lock.unlock();
		
	}
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
	public  static void escribir(Escritor escritor) {
		lock.lock();
			try {
				System.out.println("El Escritor:" + escritor.nombre + " Esta Escribiendo.");
				Thread.sleep(4000);
				System.out.println("El Escritor:" + escritor.nombre + " Termino De Escribir.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		lock.unlock();
	}

	public static void escritorSeVa(){
		lock.lock();
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
	public static void main(String[] args) {

		for (int i = 0; i < 4; i++) {
			new Lector("Lector" + i).start();
		}
		new Escritor("Clarin").start();
		new Escritor("Ole").start();
		new Escritor("News").start();
		
	}
	
	
}
