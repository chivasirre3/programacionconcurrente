package Parcial;

import java.util.Random;

public class Keygen extends Thread {

	Codificador cod;

	public Keygen(Codificador cod) {
		this.cod = cod;
	}

	public void run() {
		while (true) {
			try {
				cod.lock.lock();
				if (cod.claves.size() == 10) {
					cod.clavesllena.await();
				}
				cod.claves.add(new Random().nextInt(9)+1);
				cod.clavesVacia.signal();
				cod.lock.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
