package Practica6;

import java.net.ServerSocket;
import java.net.Socket;

public class Concatenador {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			for (int i = 0; i < 50; i++) {

				Socket socket1 = serverSocket.accept();
				Socket socket2 = serverSocket.accept();

				new ThreadConcatenador(socket1, socket2).start();
			}
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
