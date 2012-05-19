package ferry;

public class Prueba {

	
	public static void main(String[] args) {
		Ferry ferry= new Ferry(100);
		ferry.start();
		
		for (int i = 0; i < 120; i++) {
			new Auto("Auto" + i , ferry).start();
		}
	}
}
