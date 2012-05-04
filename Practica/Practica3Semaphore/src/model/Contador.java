package model;

public class Contador {

	public static Integer contador=0;
	
	public static synchronized void sumar(){
		System.out.println("Anterior:"  + contador);
		contador+=1;
		System.out.println("Actual:"  + contador);
	}
	
	public static void main(String[] args) {

		Thread t1= new Thread(new Runnable() {
			
			public void run() {
				while(true){
					sumar();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t2= new Thread(new Runnable() {
			
			public void run() {
				while(true){
					sumar();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t1.start();
		t2.start();
	}

}
