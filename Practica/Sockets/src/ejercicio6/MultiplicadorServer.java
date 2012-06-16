package ejercicio6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * esto se resuelve usando 2 threads, uno para cada cliente.
 * @author Tyno
 *
 */
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
		
		//Solucion 6
		/**
		 * Servidor Thread
		 * s1.accept();
		 * s2.accept();
		 * productor.start(s2);
		 * value=readLine();
		 * if(buffer.noEstaVacio()){
		 * 		value2=buffer.pop(); Saca la cabecera y la devuelve;
		 * }
		 * syso(value2);
		 */
		
		/**
		 * Productor
		 * 
		 * while(true){
		 * 		buffer.push(in.readLine(s2));
		 * }
		 */
		//Solucion 7
		/**
		 * Servidor Thread
		 * s1.accept();
		 * productor.start(s2);
		 * value=readLine();
		 * if(buffer.noEstaVacio()){
		 * 		value2=buffer.pop(); Saca la cabecera y la devuelve;
		 * }
		 * syso(value2);
		 */
		
		/**
		 * Productor
		 * s2.accept();
		 * while(true){
		 * 		buffer.push(in.readLine(s2));
		 * }
		 */
		
		serverSocket.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
