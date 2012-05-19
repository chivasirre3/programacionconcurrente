package Parcial;

public class PruebaGim {

	public static void main(String[] args) {
		Gimnasio gim= new Gimnasio();
		
		for (int i = 0; i < 100; i++) {
			new ClienteA(gim).start();
			new ClienteB(gim).start();
		}
	}
}
