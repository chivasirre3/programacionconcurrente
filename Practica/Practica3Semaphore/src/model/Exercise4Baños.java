package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Exercise4Baños {

	public static void main(String[] args) {
		/**
		 * Creo el Banio
		 */
		Banio unisex= new Banio(2);
		
		
		/**
		 * Creo Los Hombres y las Mujeres
		 */
		List<Persona> personas= new ArrayList<Persona>();
		for (int i = 0; i < 3; i++) {
			personas.add(new Hombre("Rogelio" + i, unisex));
		}
		for (int i = 0; i < 3; i++) {
			personas.add(new Mujer("Catalina" + i, unisex));
		}
		/**
		 * Inicializo Todo
		 */
		for (Persona persona: personas) {
			persona.start();
		}
	}
}
