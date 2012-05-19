package Parcial;

import java.util.Random;

public class DataGen extends Thread {

	Codificador cod;

	public DataGen(Codificador cod) {
		this.cod = cod;
	}

	public void run() {
		while (true) {
			try {
				cod.lock.lock();
				if (cod.datos.size() == 10) {
					cod.datosllena.await();
				}
				cod.datos.add(new Random().nextInt(9)+1);
				cod.datosVacia.signal();
				cod.lock.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
