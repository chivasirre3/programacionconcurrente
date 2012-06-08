package ejercicio5;

import java.util.ArrayList;
import java.util.List;

public class MostradorServer extends Thread {
	
	
	List<String> datos;
	Integer maxDatos;
	Integer datosEnBuffer;
	
	public MostradorServer(Integer tamanioBuffer){
		this.datos = new ArrayList<String>();
	}

	public synchronized void agregarDato(){
		//TODO: Agregar Logica.
	}
	
}
