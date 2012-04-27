package practica2;

import java.util.concurrent.Semaphore;
/**
 * 8- Considere los siguientes dos procesos
 * T_1 = while true do print(A)
 * T_2 = while true do print(B)
 * a) Utilizar semáforos para garantizar que en todo momento la cantidad de A y B difiera al
 * máximo en 1
 * b) Modificar la solución para que la única salida posible sea ABABABABAB……
 * @author Tynette
 *
 */
public class Exercise8 {

	public static void main(String[] args) {

		final Semaphore s1 = new Semaphore(1);
		final Semaphore s2 = new Semaphore(0);

		/**
		 * Creo los Threads
		 */
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					s1.acquireUninterruptibly();
					System.out.println("A");
					s2.release();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					s2.acquireUninterruptibly();
					System.out.println("B");
					s1.release();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		/**
		 * Comienzan Los Threads
		 */
		t1.start();
		t2.start();

	}

}
