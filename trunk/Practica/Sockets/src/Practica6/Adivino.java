package Practica6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Adivino {

	
	public static void main(String[] args) {
			try {
				ServerSocket serverSocket = new ServerSocket(1234);
				while(true){
					Socket serverSoc = serverSocket.accept();
					new ThreadAdivino(serverSoc).start();
					
				}	
				//serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
}
