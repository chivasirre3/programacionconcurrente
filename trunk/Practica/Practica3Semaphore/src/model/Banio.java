package model;

import java.util.concurrent.Semaphore;

public class Banio {

	//Variables
	private Semaphore toilets;
	private Semaphore mutex;
	private Semaphore mutexE;
	private Semaphore solo; //para hacer exclucion de solo hombres o solo mujeres.
	private Integer cantHombres;
	private Integer cantMujeres;
	
	//Constructor
	public Banio(Integer cantidadToilets){
		this.setCantHombres(0);
		this.setCantMujeres(0);
		this.setToilets(new Semaphore(cantidadToilets , true));
		this.solo= new Semaphore(1);
		this.mutex= new Semaphore(1);
		this.mutexE= new Semaphore(1);
	}
	/**
	 * Ingresa un Hombre.
	 */
	public void ingresaAlBanioUnHombre(){ //Ver Esto
		this.mutex.acquireUninterruptibly();
		if(this.cantHombres==0){
			try {
				this.solo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.mutex.release();
		
		this.getToilets().acquireUninterruptibly();
		System.out.println("Entro Al Baño Un Hombre");
		this.mutex.acquireUninterruptibly();
		this.cantHombres+=1;
		this.mutex.release();
		
		//Uso el baño.
		
		this.mutex.acquireUninterruptibly();
		this.cantHombres-=1;
		this.mutex.release();
		
		if(this.cantHombres == 0){
			this.solo.release();
		}
		System.out.println("Salio del Baño Un Hombre");
		this.getToilets().release();
		
		
	}
	/**
	 * Ingresa una Mujer.
	 */
	public void ingresaAlBanioUnaMujer(){
		
		this.mutexE.acquireUninterruptibly();
		if(this.cantMujeres==0){
			try {
				this.solo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.mutexE.release();
		
		
		this.getToilets().acquireUninterruptibly();
		
		this.mutexE.acquireUninterruptibly();
		System.out.println("Entro Al Baño Una Mujer");
		this.cantMujeres+=1;
		this.mutexE.release();

		//Uso el baño
		
		this.mutexE.acquireUninterruptibly();
		this.cantMujeres-=1;
		this.mutexE.release();
		
		if(this.cantMujeres == 0){
			this.solo.release();
		}
		System.out.println("Salio Del Baño Una Mujer");
		this.getToilets().release();
	}
	
	//Getters & Setters
	public Integer getCantMujeres() {
		return cantMujeres;
	}
	public void setCantMujeres(Integer cantMujeres) {
		this.cantMujeres = cantMujeres;
	}
	public Integer getCantHombres() {
		return cantHombres;
	}
	public void setCantHombres(Integer cantHombres) {
		this.cantHombres = cantHombres;
	}
	public Semaphore getToilets() {
		return toilets;
	}
	public void setToilets(Semaphore toilets) {
		this.toilets = toilets;
	}
}
