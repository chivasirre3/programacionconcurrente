package sockets;

import java.io.IOException;
import java.net.Socket;

public class ConsumidorSockets {

	public static void main(String[] args) {
		
		try {
			Socket socket= new Socket("localhost", 1234);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
