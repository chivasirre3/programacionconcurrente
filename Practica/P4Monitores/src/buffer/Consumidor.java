package buffer;

public class Consumidor extends Thread{
	Buffer buffer;
	public Consumidor(Buffer buffer){
		this.buffer=buffer;
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			this.buffer.consumir();
		}
	}

	
}
