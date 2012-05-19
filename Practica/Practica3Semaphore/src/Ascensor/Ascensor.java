package Ascensor;

import java.util.Random;
import java.util.concurrent.Semaphore;


public class Ascensor extends Thread {

	private Integer cantPersonasMax;
	private Integer cantPersonasDentro;
	private Integer esperandoSubir;
	private Integer esperandoBajar;
	private Semaphore mutex;
	private Semaphore descargar;
	private Semaphore plantaAlta;
	private Semaphore plantaBaja;
	private Boolean seguirTrabajando;
	
	public Ascensor(Integer cantMax){
		this.cantPersonasDentro=0;
		this.cantPersonasMax=cantMax;
		this.mutex = new Semaphore(1 , true);
		this.descargar = new Semaphore(0 , true);
		this.plantaAlta = new Semaphore(0 , true);
		this.plantaBaja = new Semaphore(0 , true);
		this.esperandoSubir=0;
		this.esperandoBajar=0;
		this.seguirTrabajando=true;
	}
	

	public void irAPlantaAlta(Persona persona){
		mutex.acquireUninterruptibly();
		this.esperandoSubir++;
		mutex.release();
		
		this.plantaAlta.acquireUninterruptibly();
		
		mutex.acquireUninterruptibly();
		try {
			Thread.sleep(new Random().nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cantPersonasDentro++;
		System.out.println("Subio: " + persona);
		mutex.release();
		
		
	}
	public void irAPlantaBaja(Persona persona){
		mutex.acquireUninterruptibly();
		this.esperandoBajar++;
		mutex.release();
		
		this.plantaBaja.acquireUninterruptibly();
		
		mutex.acquireUninterruptibly();
		try {
			Thread.sleep(new Random().nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cantPersonasDentro++;
		System.out.println("Subio: " + persona);
		mutex.release();
		
		
	}
	public void descenderPlantaBaja(Persona persona){
		descargar.acquireUninterruptibly();

		mutex.acquireUninterruptibly();
		try {
			Thread.sleep(new Random().nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.cantPersonasDentro--;
		this.esperandoBajar--;
		System.out.println("Bajo: " + persona);
		if(cantPersonasDentro==0){
			System.out.println("El Ascensor Se Encuentra Vacio!");
			this.plantaAlta.release(this.cantPersonasMax);
		}
		mutex.release();
		
		
		
	}
	public void descenderPlantaAlta(Persona persona){
		this.descargar.acquireUninterruptibly();
		
		this.mutex.acquireUninterruptibly();
		try {
			Thread.sleep(new Random().nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.cantPersonasDentro--;
		this.esperandoSubir--;
		System.out.println("Bajo: " + persona);
		if(cantPersonasDentro==0){
			System.out.println("El Ascensor Se Encuentra Vacio!");
			this.plantaBaja.release(this.cantPersonasMax);
		}
		this.mutex.release();
		
		
		
	}


	public void run() {
		while(this.seguirTrabajando){
			
			//Sube
			mutex.acquireUninterruptibly();
			System.out.println("Cargando Gente ");
			this.plantaAlta.release(this.cantPersonasMax);
			mutex.release();
			
			//Esperar a que se llene
			while(cantPersonasDentro<cantPersonasMax && this.hayEsperandoParaSubir()){ //Duerme Mientras no este lleno
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Simulacion para subir a Planta Alta
			mutex.acquireUninterruptibly();
			System.out.println("Llendo a Planta Alta");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mutex.release();
			
			//Llega A Planta Alta
			mutex.acquireUninterruptibly();
			System.out.println("Llego A Planta Alta");
			System.out.println("Descendiendo Gente");
			this.descargar.release(this.cantPersonasMax);
			mutex.release();
			
			//Esperar a que se Vacie
			while(cantPersonasDentro!=0){ //Duerme Mientras hasta que este Vacio
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Baja
			mutex.acquireUninterruptibly();
			System.out.println("Cargando Gente ");
			this.plantaBaja.release(this.cantPersonasMax);
			mutex.release();
			
			//Esperar a que se llene
			while(cantPersonasDentro<cantPersonasMax && this.hayEsperandoParaBajar()){ //Duerme Mientras no este lleno
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Simulador Ascensor llendo a planta baja
			mutex.acquireUninterruptibly();
			System.out.println("Llendo a Planta Baja");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mutex.release();
			
			//Llega A Planta Baja
			mutex.acquireUninterruptibly();
			System.out.println("Llego A Planta Baja");
			System.out.println("Descendiendo Gente");
			this.descargar.release(this.cantPersonasMax);
			mutex.release();
			
			//Esperar a que se Vacie
			while(cantPersonasDentro!=0){ //Duerme Mientras hasta que este Vacio
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			mutex.acquireUninterruptibly();
			if(this.hayEsperandoParaSubir() || this.hayEsperandoParaBajar()){
				this.seguirTrabajando=false;
			}
			mutex.release();
			
		}
		
	}


	public Boolean hayEsperandoParaSubir() {
		return this.esperandoSubir>0;
	}
	public Boolean hayEsperandoParaBajar() {
		return this.esperandoBajar>0;
	}
	
	
	
	
}
