package buffer;

public class Productor extends Thread{
	Buffer buffer;
	
	public Productor(Buffer buffer){
		this.buffer=buffer;
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			this.buffer.producir();
		}
	}

}
