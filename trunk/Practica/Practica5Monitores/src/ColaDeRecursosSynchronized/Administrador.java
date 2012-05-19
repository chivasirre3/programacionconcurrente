package ColaDeRecursosSynchronized;

import java.util.ArrayList;
import java.util.List;


public class Administrador {

	private List<Integer> numeros;
	
	public Administrador(){
		this.numeros= new ArrayList<Integer>();
		for (int i = 0; i < 10 ; i++) {
			this.numeros.add(i);
		}
	}

	public  synchronized List<Integer> tomarN(Integer cantidad){
		while(this.numeros.size()<cantidad){ //duerme mientras no haya la cantidad necesaria de elementos necesitados
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<Integer> tomados = new ArrayList<Integer>();
		for (int j = 0; j < cantidad; j++) {
			tomados.add(this.numeros.get(0));
			this.numeros.remove(0);
		}
		return tomados;
	}
	public synchronized void liberarN(List<Integer> num){
		this.numeros.addAll(num);
		this.notifyAll();
	}
	
}
