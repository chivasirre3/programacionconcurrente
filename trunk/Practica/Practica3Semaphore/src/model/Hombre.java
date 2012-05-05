package model;

public class Hombre extends Persona {

	// Constructor
	public Hombre(String nombre, Banio banio) {
		this.setBanio(banio);
		this.setNombre(nombre);
	}

	/**
	 * Entra al Ba�o y sale. Luego Duerme 1 Segundo y vuelve a intentar entrar
	 */
	@Override
	public void run() {
		while (true) {
			this.getBanio().ingresaAlBanioUnHombre();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
