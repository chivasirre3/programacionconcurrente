package buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

	public Random random = new Random();
	public List<Integer> numeros;
	public Lock lock = new ReentrantLock();
	public Condition esperandoProducir = lock.newCondition();
	public Condition esperandoConsumir = lock.newCondition();

	public Buffer(Integer capacidad) {
		this.numeros = new ArrayList<Integer>(capacidad);
	}

	public void consumir() {
		lock.lock();
		try {
			while (numeros.isEmpty()) {
				this.esperandoConsumir.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Se consumio: " + numeros.get(0));
		numeros.remove(0);
		this.esperandoProducir.signalAll();
		lock.unlock();
	}

	public void producir() {
		lock.lock();
		try {
			while (!this.hayLugar()) {
				this.esperandoProducir.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Integer i= this.random.nextInt(10);
		this.numeros.add(i);
		System.out.println("Se Produjo: " + i);
		this.esperandoConsumir.signalAll();
		lock.unlock();
	}

	public boolean hayLugar() {
		return this.numeros.size()<10;
	}
	
	public static void main(String[] args) {
		Buffer buffer= new Buffer(10);
		for (int i = 0; i < 11; i++) {
			new Consumidor(buffer).start();
		}
		for (int i = 0; i < 15; i++) {
			new Productor(buffer).start();
		}
	}

}
