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
				s1.acquireUninterruptibly();
				System.out.println("C");
				s2.release();
				System.out.println("E");
				s3.release();
				
			}
		});
		Thread p2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("A");
				s1.release();
				s2.acquireUninterruptibly();
				System.out.println("R");
				s3.acquireUninterruptibly();
				System.out.println("O");
				
			}
		});
		
		s1.acquireUninterruptibly();
		s2.acquireUninterruptibly();
		s3.acquireUninterruptibly();
		p1.start();
		p2.start();

	}

}