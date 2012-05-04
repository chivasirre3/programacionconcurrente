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
			System.out.println(this.getNombre() + " Entro al Banio");
			// Seccion Critica
			System.out.println(this.getNombre() + " Salio del Banio");
			this.getBanio().saleDelBanioUnHombre();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
