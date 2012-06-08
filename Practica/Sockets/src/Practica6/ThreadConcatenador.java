package Practica6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadConcatenador extends Thread {

	public Socket socket1;
	public Socket socket2;

	public ThreadConcatenador(Socket socket1, Socket socket2) {
		this.socket1 = socket1;
		this.socket2 = socket2;
	}

	@Override
	public void run() {
		try{
		BufferedReader serverIn1 = new BufferedReader(new InputStreamReader(this.socket1.getInputStream()));
		BufferedReader serverIn2 = new BufferedReader(new InputStreamReader(this.socket2.getInputStream()));
		System.out.println(serverIn1.readLine() + serverIn2.readLine());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
