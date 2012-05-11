package problem7;

import junit.framework.TestCase;

public class Test extends TestCase{
	
	public static void testVacio(){

		for (int i = 0; i < 1000; i++) {
			new Lector("Lector" + i).start();
		}
		for (int i = 0; i < 100; i++) {
			new Escritor("Escritor" + i).start();
		}
		
		try {
			Thread.sleep(12000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, Biblioteca.cola.isEmpty());
	}
	

}
