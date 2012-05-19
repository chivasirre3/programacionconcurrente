package Parcial;

public class PruebaCod {

	public static void main(String[] args) {
		Codificador cod= new Codificador();
		Keygen key= new Keygen(cod);
		DataGen data= new DataGen(cod);
		
		key.start();
		data.start();
		for (int i = 0; i < 4; i++) {
			new Cliente(cod).start();
		}
	}
}
