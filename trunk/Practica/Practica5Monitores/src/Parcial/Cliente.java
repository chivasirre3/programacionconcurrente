package Parcial;

public class Cliente extends Thread {

	private Codificador codificador;
	
	public Cliente(Codificador codificador){
		this.codificador=codificador;
	}
	
	public void run() {
		//while (true) {
		for (int i = 0; i < 4; i++) {
			try {
				System.out.println(this.codificador.obtenerPar());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
