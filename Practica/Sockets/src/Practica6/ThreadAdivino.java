package Practica6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class ThreadAdivino extends Thread {

	
	public Socket socket;

	public ThreadAdivino(Socket serverSoc) {
		this.socket=serverSoc;
	}

	@Override
	public void run() {
		try{
		BufferedReader serverIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		PrintStream serverOut = new	PrintStream(this.socket.getOutputStream());
		Random random= new Random();
		
		Boolean noAdivino=true;
		while(noAdivino){
			Integer paraAdivinar = Integer.parseInt(serverIn.readLine());
			if(random.nextInt(10)==paraAdivinar){
				serverOut.println("Adivine");
				noAdivino=false;
			} else {
				serverOut.println("No Adivine");
			}
		}
		
		serverIn.close();
		serverOut.close();
		this.socket.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Se Rompio Todo");
		}
	}
	
}
