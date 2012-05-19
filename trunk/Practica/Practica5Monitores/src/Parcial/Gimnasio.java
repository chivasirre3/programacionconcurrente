package Parcial;

import java.util.concurrent.Semaphore;

public class Gimnasio {

	private Semaphore mutex;
	private Semaphore aparatos;
	private Semaphore discos;
	private Semaphore mutexE;
	
	public Gimnasio(){
		this.aparatos=new Semaphore(4);
		this.discos=new Semaphore(4);
		this.mutex=new Semaphore(4);
		this.mutexE=new Semaphore(4);
	}
	
	public void ejercicio(Integer cantidad) {
		this.mutex.acquireUninterruptibly();
			this.discos.acquireUninterruptibly(cantidad);
		this.mutex.release();
		
		this.aparatos.acquireUninterruptibly();
		System.out.println("Se Esta usando un aparato");
		this.aparatos.release();
		
		this.mutexE.acquireUninterruptibly();
			this.discos.release(cantidad);
		this.mutexE.release();
	}

}
