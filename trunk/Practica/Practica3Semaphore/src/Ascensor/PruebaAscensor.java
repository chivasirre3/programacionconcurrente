package Ascensor;

public class PruebaAscensor {

	
	public static void main(String[] args) {
		Ascensor asc= new Ascensor(20);
		asc.start();
		
		
		for (int i = 0; i<500; i++) {
			new Sube("Persona: " + i, asc).start();
			new Baja("Persona: " + i, asc).start();
		}
	}
}
