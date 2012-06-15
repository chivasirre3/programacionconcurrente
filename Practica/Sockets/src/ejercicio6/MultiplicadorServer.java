package ejercicio6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiplicadorServer {

	public static void main(String[] args) {
		try{
		ServerSocket serverSocket;
		serverSocket= new ServerSocket(1234);
		Socket socket1= serverSocket.accept();
		Socket socket2= serverSocket.accept();
		BufferedReader serverIn1= new BufferedReader(new	InputStreamReader(socket1.getInputStream()));
		BufferedReader serverIn2= new BufferedReader(new	InputStreamReader(socket2.getInputStream()));
		System.out.println(Integer.parseInt(serverIn1.readLine())* Integer.parseInt(serverIn2.readLine()) ); 
		
		serverSocket.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
