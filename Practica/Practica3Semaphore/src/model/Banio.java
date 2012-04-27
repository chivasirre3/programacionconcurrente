package model;

import java.util.concurrent.Semaphore;

public class Banio {

	//Variables
	private Semaphore toilets;
	private Semaphore solo; //para hacer exclucion de solo hombres o solo mujeres.
	private Integer cantHombres;
	private Integer cantMujeres;
	
	//Constructor
	public Banio(Integer cantidadToilets){
		this.setCantHombres(0);
		this.setCantMujeres(0);
		this.setToilets(new Semaphore(cantidadToilets , true));
		this.solo= new Semaphore(1);
	}
	/**
	 * Retorna True si no hay ninguna Mujer En el banio.
	 * @return Boolean
	 */
	public Boolean puedeEntrarHombre(){
		return this.getCantMujeres() == 0;
	}
	/**
	 * Retorna True si no hay ningun Hombre en el banio.
	 * @return Boolean
	 */
	public Boolean puedeEntrarMujer(){
		return this.getCantHombres() == 0;
	}
	/**
	 * Ingresa un Hombre.
	 */
	public void ingresaAlBanioUnHombre(){ //Ver Esto
			this.getToilets().acquireUninterruptibly();
			this.cantHombres+=1;
	}
	/**
	 * Sale Del Baño un Hombre.
	 */
	public void saleDelBanioUnHombre(){
		this.cantHombres-=1;
		this.getToilets().release();
	}
	/**
	 * Ingresa una Mujer.
	 */
	public void ingresaAlBanioUnaMujer(){
		this.getToilets().acquireUninterruptibly();
		this.cantMujeres+=1;
	}
	/**
	 * Sale Del banio una Mujer.
	 */
	public void saleDelBanioUnaMujer(){
		this.cantMujeres-=1;
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
