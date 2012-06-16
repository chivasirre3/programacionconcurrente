package chato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatCliente {


	public class MostradorTextos extends Thread{
		BufferedReader leerServidor;
		public MostradorTextos(BufferedReader in){
			this.leerServidor = in;
		}
		@Override
		public void run() {
			while(true){
				try {
					System.out.println(leerServidor.readLine());
				} catch (IOException e) {e.printStackTrace();}				
			}
		}
	}
	public class LectorTextos extends Thread{
		PrintStream escribirServidor;
		public LectorTextos(PrintStream out){
			this.escribirServidor = out;
		}
		@Override
		public void run() {
			BufferedReader bufferPantalla = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				try {
					escribirServidor.println(bufferPantalla.readLine());
				} catch (IOException e) {e.printStackTrace();}
			}
		}
	}
	
	public static void main(String[] args) {		
		try {
			
			Socket socket = new Socket("10.9.1.183", 1234);
			BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			ChatCliente chatcliente = new ChatCliente();
			chatcliente.new MostradorTextos(in).start();
			chatcliente.new LectorTextos(out).start();			
			
		} catch (Exception e) {e.printStackTrace();}
	}
}
