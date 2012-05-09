package model;

import java.util.concurrent.Semaphore;

public class Banio {

	//Variables
	private Semaphore toilets; //cantidad de recursos que tiene el banio
	private Semaphore mutexM; //aseguran Exclusion mutua de mujeres mientras llegan o se van.
	private Semaphore mutexH; //Aseguran Exclusion Mutua de hombres mientras llegan o se van.
	private Semaphore solo; //para hacer exclucion de solo hombres o solo mujeres.
	private Integer cantHombres;
	private Integer cantMujeres;
	
	//Constructor
	public Banio(Integer cantidadToilets){
		this.cantHombres=0;
		this.cantMujeres=0;
		this.toilets=new Semaphore(cantidadToilets , true);
		this.solo= new Semaphore(1);
		this.mutexM= new Semaphore(1);
		this.mutexH= new Semaphore(1);
	}
	/**
	 * Ingresa un Hombre.
	 */
	public void ingresaAlBanioUnHombre(){ //Ver Esto
		this.mutexH.acquireUninterruptibly();
		this.cantHombres+=1;
		if(this.cantHombres==1){
			try {
				this.solo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.mutexH.release();
		
		
		this.toilets.acquireUninterruptibly();
		System.out.println("Entro Al Baño Un Hombre");
		//Uso el baño.
		
		this.mutexH.acquireUninterruptibly();
		this.cantHombres-=1;
		System.out.println("Salio del Baño Un Hombre");
		this.toilets.release();
		if(this.cantHombres == 0){
			this.solo.release();
		}
		this.mutexH.release();
		
		
		
	}
	/**
	 * Ingresa una Mujer.
	 */
	public void ingresaAlBanioUnaMujer(){
		
		this.mutexM.acquireUninterruptibly();
		this.cantMujeres+=1;
		if(this.cantMujeres==1){
			try {
				this.solo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.mutexM.release();
		
		
		this.toilets.acquireUninterruptibly();
		System.out.println("Entro Al Baño Una Mujer");
		//Usa el banio
		
		this.mutexM.acquireUninterruptibly();
		this.cantMujeres-=1;
		System.out.println("Salio Del Baño Una Mujer");
		this.toilets.release();
		if(this.cantMujeres == 0){
			this.solo.release();
		}
		this.mutexM.release();
		
	}
	
}
