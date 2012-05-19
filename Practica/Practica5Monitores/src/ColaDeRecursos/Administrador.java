package ColaDeRecursos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Administrador {

	private List<Integer> numeros;
	private Lock lock= new ReentrantLock(true);
	private Condition esperando= lock.newCondition();
	
	public Administrador(){
		this.numeros= new ArrayList<Integer>();
		for (int i = 0; i < 10 ; i++) {
			this.numeros.add(i);
		}
	}

	public synchronized Integer tomar(){
		while(this.numeros.isEmpty()){ //duerme mientras no haya la cantidad necesaria de elementos necesitados
			try {
				this.esperando.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Integer i= this.numeros.get(0);
		this.numeros.remove(0);
		return i;
	}
	public synchronized void liberar(Integer num){
		this.numeros.add(num);
	}
	
	public  List<Integer> tomarN(Integer cantidad){
		lock.lock();
		while(this.numeros.size()<cantidad){ //duerme mientras no haya la cantidad necesaria de elementos necesitados
			try {
				this.esperando.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<Integer> tomados = new ArrayList<Integer>();
		for (int j = 0; j < cantidad; j++) {
			tomados.add(this.numeros.get(0));
			this.numeros.remove(0);
		}
		lock.unlock();
		return tomados;
	}
	public void liberarN(List<Integer> num){
		lock.lock();
		this.numeros.addAll(num);
		this.esperando.signalAll();
		lock.unlock();
	}
	
}
