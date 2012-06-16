package chato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class EntubadoThread extends Thread {
	
	BufferedReader clienteRecibe;
    PrintStream clienteEnvia;

	public EntubadoThread(BufferedReader inUno, PrintStream outUno){
		this.clienteRecibe = inUno;
		this.clienteEnvia = outUno;
	}
	
	
	public void run() {		
		while(true){
			try {
				clienteEnvia.println(clienteRecibe.readLine());
			} catch (IOException e) {e.printStackTrace();}			
		}
	}

}
