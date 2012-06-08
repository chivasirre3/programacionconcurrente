package Practica6;

import java.io.PrintStream;
import java.net.Socket;

public class QuieroConcatenar {
	public static void main(String[] args) {
		Socket socketcliente = null;
		try {
			for (int i = 0; i < 50; i++) {

				socketcliente = new Socket("localhost", 1234);
				PrintStream clienteOut = new PrintStream(socketcliente.getOutputStream());

				clienteOut.println("Hola"+i);

				socketcliente.close();
				
				socketcliente = new Socket("localhost", 1234);
				PrintStream clienteOtroOut = new PrintStream(socketcliente.getOutputStream());
				clienteOtroOut.println("Y Chau" + i);

				socketcliente.close();

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
