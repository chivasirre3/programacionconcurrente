package model;

public class Hombre extends Persona {

	// Constructor
	public Hombre(String nombre, Banio baño) {
		this.setBanio(baño);
		this.setNombre(nombre);
	}

	/**
	 * Entra al Ba�o y sale. Luego Duerme 1 Segundo y vuelve a intentar entrar
	 */
	@Override
	public void run() {
		while (true) {
			this.getBanio().ingresaAlBanioUnHombre();
			System.out.println(this.getNombre() + " Entro al Ba�o");
			// Seccion Critica
			System.out.println(this.getNombre() + " Salio del Ba�o");
			this.getBanio().saleDelBanioUnHombre();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
