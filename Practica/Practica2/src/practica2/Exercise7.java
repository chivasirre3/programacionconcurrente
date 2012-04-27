package practica2;

import java.util.concurrent.Semaphore;

/**
 * 7- Se tienen tres procesos A, B, C. Se desea que la operación op_C que
 * ejecuta C se realice sólo luego de que A haya ejecutado op_A y B haya
 * ejecutado op_B.
 * 
 * @author Tynette
 * 
 */
public class Exercise7 {

	public static void main(String[] args) {

		final Semaphore s1 = new Semaphore(1);
		final Semaphore s2 = new Semaphore(0);
		final Semaphore s3 = new Semaphore(0);

		/**
		 * Creo los Threads
		 */
		Thread p1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					s1.acquireUninterruptibly();
					System.out.println("OP A");
					s2.release();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread p2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					s2.acquireUninterruptibly();
					System.out.println("OP B");
					s3.release();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread p3 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					s3.acquireUninterruptibly();
					System.out.println("OP C");
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
		p1.start();
		p2.start();
		p3.start();

	}

}
