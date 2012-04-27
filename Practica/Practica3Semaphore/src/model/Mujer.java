package model;

public class Mujer extends Persona {

	// Constructor
	public Mujer(String nombre, Banio ba�o) {
		this.setBanio(ba�o);
		this.setNombre(nombre);
	}

	/**
	 * Entra al Ba�o y sale. Luego Duerme 1 Segundo y vuelve a intentar entrar
	 */
	@Override
	public void run() {
		while (true) {
			this.getBanio().ingresaAlBanioUnaMujer();
			System.out.println(this.getNombre() + " Entro al Ba�o");
			// Seccion Critica
			this.getBanio().saleDelBanioUnaMujer();
			System.out.println(this.getNombre() + " Salio del Ba�o");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
