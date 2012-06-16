package chato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatServer {
	
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
            Socket clienteUNO = serverSocket.accept();
            BufferedReader inUno = new BufferedReader( new InputStreamReader(clienteUNO.getInputStream()));
			PrintStream outUno = new PrintStream(clienteUNO.getOutputStream());
			
            Socket clienteDos = serverSocket.accept();
            BufferedReader inDos = new BufferedReader( new InputStreamReader(clienteDos.getInputStream()));
			PrintStream outDos = new PrintStream(clienteDos.getOutputStream());

			
			new EntubadoThread(inUno, outDos).start();
			new EntubadoThread(inDos, outUno).start();
			
		} catch (UnknownHostException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
	
	}
}
