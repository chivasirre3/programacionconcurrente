package practica2;

import java.util.concurrent.Semaphore;

/**
 * 9- Los siguientes procesos cooperan para calcular el valor N2 que es la suma
 * de los primero N números impares. Los procesos comparten las variables N y N2
 * inicializadas en N = 50 y N2 = 0.
 * 
 * @author Tynette
 * 
 */
public class Exercise9 {
	public static Integer n=50;
	public static Integer n2=0;
	public static void main(String[] args) {
		
		/**
		 * Mis Variables
		 */
		final Semaphore s1 = new Semaphore(1);
		final Semaphore s2 = new Semaphore(0);

		/**
		 * Creo los Threads
		 */
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (n > 0) {
					s1.acquireUninterruptibly();
					n = n-1;
					s2.release();
				}
				System.out.println(n2);
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					s2.acquireUninterruptibly();
					n2 = n2 + 2*n+ 1;
					s1.release();
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
