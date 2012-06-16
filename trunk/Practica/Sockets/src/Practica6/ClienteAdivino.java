package Practica6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteAdivino {

	
	public static void main(String[] args) {
		Socket socketcliente=null;
		try {
			socketcliente = new Socket("localhost",1234);
			PrintStream clienteOut = new
			PrintStream(socketcliente.getOutputStream());
			BufferedReader clienteIn = new BufferedReader(new InputStreamReader(socketcliente.getInputStream()));
			Boolean seguir= true;
			while(seguir){
				clienteOut.println(8);
				if(clienteIn.readLine().equals("Adivine") ){
					seguir=false;
					System.out.println("Me Adivinaron");
				}
				else{
					System.out.println("No Me Adivinaron");
				}
			}
			
			clienteIn.close();
			clienteOut.close();
			socketcliente.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
