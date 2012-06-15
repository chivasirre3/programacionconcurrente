package ejercicio5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorDatos {
	
	

	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket;
			serverSocket= new ServerSocket(1234);
			
			for (int i = 0; i < 50; i++) {
				Socket serverSoc= serverSocket.accept();
				BufferedReader serverIn= new BufferedReader(new	InputStreamReader(serverSoc.getInputStream()));
				System.err.println(serverIn.readLine()); 
			}
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
