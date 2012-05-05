package problem7;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
	
	
	public static void write(Escritor escritor){
		lock.lock();
		
	}
	public  static void leer(Lector lector) {
		try {
			System.out.println("El Lector:" + lector.nombre + " Esta Leyendo.");
			Thread.sleep(new Random().nextInt(1000)+500);
			System.out.println("El Lector:" + lector.nombre + " Termino De Leer.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public  static void escribir(Escritor escritor) {
		lock.lock();
		try {
			System.out.println("El Escritor:" + escritor.nombre + " Esta Escribiendo.");
			Thread.sleep(new Random().nextInt(1000)+500);
			System.out.println("El Escritor:" + escritor.nombre + " Termino De Escribir.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public  static void llegarLector(Lector lector){
		lock.lock();
		if(!escritorEscribiendo){
			lectoresLeyendo++;
			}
		else{
			if(!Biblioteca.cola.isEmpty() && Biblioteca.cola.get(Biblioteca.cola.size()-1).esLector){//Si no es vacia y es un escritor
				Biblioteca.cola.get(Biblioteca.cola.size()-1).cantidad+=1;
			}
			else{
				Tupla nueva=  new Tupla(true);
				cola.add(nueva);
				try {
					lectores.await();
					lectoresLeyendo++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		lock.unlock();
	}
	public  static void lectorSeVa(){
			lock.lock();
			lectoresLeyendo--;
			if(!cola.isEmpty() && !cola.get(0).esLector && lectoresLeyendo==0){ //si el valor de la tupla es de un escritor entonces libero 
				for (int i = 0; i < cola.get(0).cantidad ; i++) {
					escritores.signal();
				}
			}
			else if(!cola.isEmpty() && cola.get(0).esLector){
				for (int j = 0; j < cola.get(0).cantidad; j++) {
					lectores.signal();
				}
			}
			lock.unlock();
	}
	
	public static void llegarEscritor(){
		lock.lock();
		if(!escritorEscribiendo && lectoresLeyendo == 0){
			escritorEscribiendo=true;
		}
		else{
			if(!Biblioteca.cola.isEmpty() && !Biblioteca.cola.get(Biblioteca.cola.size()-1).esLector){//Si no es vacia y es un escritor
				Biblioteca.cola.get(Biblioteca.cola.size()-1).cantidad+=1;
			}
			else{
				Tupla nueva=  new Tupla(false);
				cola.add(nueva);
				try {
					escritores.await();
					escritorEscribiendo=true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		lock.unlock();
		
	}

	public static void escritorSeVa(){
		lock.lock();
		escritorEscribiendo=false;
		if(!cola.isEmpty() && cola.get(0).esLector){ //si el valor de la tupla es de un lector entonces libero lectores
			for (int i = 0; i < cola.get(0).cantidad ; i++) {
				lectores.signal();
			}
		}
		else if(!cola.isEmpty() && !cola.get(0).esLector){
			for (int j = 0; j < cola.get(0).cantidad; j++) {
				escritores.signal();
			}
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
