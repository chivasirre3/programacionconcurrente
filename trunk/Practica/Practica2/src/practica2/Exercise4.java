package practica2;

import java.util.concurrent.Semaphore;

public class Exercise4 {

	public static Integer y=0;
	public static Integer z=0;
	public static void main(String[] args) {

		final Semaphore s1 = new Semaphore(1);
		

		Thread p1 = new Thread(new Runnable() {

			@Override
			public void run() {
				s1.acquireUninterruptibly();
				Integer x;
				x = y + z;
				System.out.println("X Vale: " + x);
				s1.release();
			}
		});
		Thread p2 = new Thread(new Runnable() {

			@Override
			public void run() {
				s1.acquireUninterruptibly();
				Integer x;
				y = 1;
				z = 2;
				s1.release();
			}
		});
		p1.start();
		p2.start();

	}

}
