package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para simular los recorridos
 * @author Tyno
 *
 */
public class Simulador {
	//Variables
	private Recorrido berazateguiAOnce;
	private Recorrido OnceABerazategui;
	private Recorrido bosquesAConstitucion;
	private Recorrido constitucionBosques;
	private Estacion berazategui;
	private Estacion quilmes;
	private Estacion constitucion;
	private Estacion bosques;
	
	//Constructor
	public Simulador(){
		this.crearEstaciones();
		this.crearRecorridoBerazateguiConstitucion();
		this.crearRecorridoConstitucionBerazategui();
		this.crearRecorridoBosquesConstitucion();
		this.crearRecorridoConstitucionBosques();
	}
	
	public void crearRecorridoConstitucionBerazategui(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.constitucion);
		estaciones.add(this.quilmes);
		estaciones.add(this.berazategui);
		this.OnceABerazategui = new Recorrido("Constitucion A Berazategui" , estaciones);
	}
	public void crearRecorridoBerazateguiConstitucion(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.berazategui);
		estaciones.add(this.quilmes);
		estaciones.add(this.constitucion);
		this.berazateguiAOnce = new Recorrido("Berazategui A Constitucion" , estaciones);
	}
	public void crearRecorridoBosquesConstitucion(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.bosques);
		estaciones.add(this.berazategui);
		estaciones.add(this.quilmes);
		estaciones.add(this.constitucion);
		this.bosquesAConstitucion = new Recorrido("Bosques A Constitucion" , estaciones);
	}
	public void crearRecorridoConstitucionBosques(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.constitucion);
		estaciones.add(this.quilmes);
		estaciones.add(this.berazategui);
		estaciones.add(this.bosques);
		this.constitucionBosques = new Recorrido("Constitucion A Bosques" , estaciones);
	}

	public void crearEstaciones() {
		berazategui = new Estacion("Berazategui");
		quilmes = new Estacion("Quilmes");
		constitucion = new Estacion("Constitucion");
		bosques = new Estacion("Bosques");
	}
	public  List<Recorrido> recorridosTrenRoca(){
		List<Recorrido> recorridos= new ArrayList<Recorrido>();
		recorridos.add(berazateguiAOnce);
		recorridos.add(OnceABerazategui);
		return recorridos;
	}
	public  List<Recorrido> recorridosTrenBelgrano(){
		List<Recorrido> recorridos= new ArrayList<Recorrido>();
		recorridos.add(OnceABerazategui);
		recorridos.add(berazateguiAOnce);
		return recorridos;
	}
	public  List<Recorrido> recorridosTrenSarmiento(){
		List<Recorrido> recorridos= new ArrayList<Recorrido>();
		recorridos.add(bosquesAConstitucion);
		recorridos.add(constitucionBosques);
		return recorridos;
	}
	public static void main(String[] args) {
		//Creo El simulador para crear todo los objetos que necesito para probar el funcionamiento.
		Simulador simulador= new Simulador();
		
		//Creo Los trenes e inicio la simulacion.
		Tren belgrano = new Tren("Belgrano", simulador.recorridosTrenBelgrano());
		Tren roca = new Tren("Roca", simulador.recorridosTrenRoca());
		Tren sarmiento = new Tren("Sarmiento", simulador.recorridosTrenSarmiento());
		belgrano.start();
		roca.start();
		sarmiento.start();
	}
	
}
