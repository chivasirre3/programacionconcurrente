package ejercicio5;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class ClienteDatos {

	
	public static void main(String[] args) {
		Socket socketcliente = null;
		try {
			for (int i = 0; i < 50; i++) {

				socketcliente = new Socket("localhost", 1234);
				PrintStream clienteOut = new PrintStream(socketcliente.getOutputStream());

				clienteOut.println("Dato"+i);
				Thread.sleep(new Random().nextInt(1000));
			}
			socketcliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
