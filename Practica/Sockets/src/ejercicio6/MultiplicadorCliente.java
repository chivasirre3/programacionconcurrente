package ejercicio6;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class MultiplicadorCliente extends Thread{

	@Override
	public void run() {
		Socket socketcliente = null;
		try {
			socketcliente = new Socket("localhost", 1234);
			PrintStream clienteOut = new PrintStream(socketcliente.getOutputStream());

			clienteOut.println(new Random().nextInt(10));
			socketcliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new MultiplicadorCliente().start();
		}
	}

}
