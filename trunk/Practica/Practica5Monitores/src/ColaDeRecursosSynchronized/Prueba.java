package ColaDeRecursosSynchronized;

import java.util.List;
import java.util.Random;


public class Prueba {

	
	public static void main(String[] args) {
		final Administrador admin= new Administrador();
		
		for (int j = 0; j < 10; j++) {
			
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						List<Integer> nums =admin.tomarN(2);
						System.out.println("se tomo: " + nums);
						Thread.sleep(new Random().nextInt(1000)+500);
						admin.liberarN(nums);
						System.out.println("se libero: " + nums);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						List<Integer> nums =admin.tomarN(4);
						System.out.println("se tomo: " + nums);
						Thread.sleep(new Random().nextInt(1000)+500);
						admin.liberarN(nums);
						System.out.println("se libero: " + nums);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		}
	}
}
