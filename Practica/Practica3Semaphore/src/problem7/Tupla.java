package problem7;

public class Tupla {

		
		public Boolean lector;
		public Boolean escritor;
		public Integer cantidad;
		


		public Tupla(Boolean esLector, Boolean esEscritor){
			this.escritor=esEscritor;
			this.lector=esLector;
			this.cantidad=1;
		}
		
		public String imprimir(){
			if(escritor & !lector){
				return "escritor" + cantidad;
			}

			if(!escritor & lector){
				return "lector" + cantidad;
			}
			return "Melo puto";
		}
		
}
