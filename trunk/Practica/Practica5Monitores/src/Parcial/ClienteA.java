package Parcial;

public class ClienteA extends Thread {

	
	
	private Gimnasio gimnasio;
	public ClienteA(Gimnasio gimnasio){
		this.gimnasio=gimnasio;
	}
	
	public void run() {
		//while (true) {
		for (int i = 0; i < 4; i++) {
			try {
				this.gimnasio.ejercicio(2);
				Thread.sleep(2000);
				this.gimnasio.ejercicio(1);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
