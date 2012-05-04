package model;

import java.util.ArrayList;
import java.util.List;

public class Exercise4Banios {

	public static void main(String[] args) {
		/**
		 * Creo el Banio.
		 */
		Banio unisex= new Banio(2);
		
		
		/**
		 * Creo Los Hombres y las Mujeres
		 */
		List<Persona> personas= new ArrayList<Persona>();
		personas.add(new Hombre("Jorge" , unisex));
		personas.add(new Mujer("Catalina" , unisex));
		personas.add(new Hombre("Rogelio" , unisex));
		personas.add(new Mujer("Rosita" , unisex));
		personas.add(new Hombre("Domingo" , unisex));
		personas.add(new Mujer("Virgilia" , unisex));
		/**
		 * Inicializo Todo
		 */
		for (Persona persona: personas) {
			persona.start();
		}
	}
}
