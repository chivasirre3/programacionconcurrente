package Parcial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Codificador {

	public List<Integer> claves;
	public List<Integer> datos;
	public Lock lock= new ReentrantLock(true);
	public Condition clavesVacia= lock.newCondition();
	public Condition datosVacia= lock.newCondition();
	public Condition clavesllena= lock.newCondition();
	public Condition datosllena= lock.newCondition();
	
	public Codificador(){
		this.claves= new ArrayList<Integer>(10);
		this.datos= new ArrayList<Integer>(10);
	}
	
	public Integer obtenerPar() throws InterruptedException {
		lock.lock();
		if (claves.isEmpty()) {
			this.clavesVacia.await();
		}
		Integer clave= this.claves.get(0);
		this.claves.remove(0);

		if (datos.isEmpty()) {
			this.datosVacia.await();
		}
		Integer dato= this.datos.get(0);
		this.datos.remove(0);
		
		Integer parCodificado= this.codificar(dato , clave);
		lock.unlock();
		return parCodificado;
	}

	public Integer codificar(Integer dato, Integer clave) {
		lock.lock();
		Integer datoCod= dato*clave;
		lock.unlock();
		return datoCod;
	}

}
