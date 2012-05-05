package model;

public class Mujer extends Persona {

	// Constructor
	public Mujer(String nombre, Banio banio) {
		this.setBanio(banio);
		this.setNombre(nombre);
	}

	/**
	 * Entra al Ba�o y sale. Luego Duerme 1 Segundo y vuelve a intentar entrar
	 */
	@Override
	public void run() {
		while (true) {
			this.getBanio().ingresaAlBanioUnaMujer();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
