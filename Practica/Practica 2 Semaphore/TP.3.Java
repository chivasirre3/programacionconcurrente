import java.util.concurrent.Semaphore;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Semaphore s1 = new Semaphore(1);
		final Semaphore s2 = new Semaphore(1);
		final Semaphore s3 = new Semaphore(1);
		
		Thread p1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("R");
				s1.release();
				s3.acquireUninterruptibly();
				System.out.println("OK");
				s3.release();
				
			}
		});
		Thread p2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				s1.acquireUninterruptibly();
				System.out.println("I");
				s2.release();
				s3.acquireUninterruptibly();
				System.out.println("OK");
				s3.release();
				
			}
		});

		Thread p3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				s2.acquireUninterruptibly();
				System.out.println("O");
				System.out.println("OK");
				s3.release();
				
			}
		});
		
		s1.acquireUninterruptibly();
		s2.acquireUninterruptibly();
		s3.acquireUninterruptibly();
		p1.start();
		p2.start();
		p3.start();

	}

}
