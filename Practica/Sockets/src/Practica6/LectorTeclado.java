package Practica6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LectorTeclado {

	
	public static void main(String[] args) {
		while(true){
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader (isr);

			try {
				System.out.println(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
