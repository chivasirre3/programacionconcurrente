package Problem5Hemodialisis;

/**
 * Esta clase representa la clinica, la cual tiene un revistero que pose N cantidad de revistas y  una sala
 * que posee N cantidad de  camillas.
 * @author Melo Martin Alejandro
 *
 */
public class Clinica {

	public Revistero revistero;
	public Sala sala;
	
	public Clinica(Integer camillas ,  Integer revistas){
		this.sala= new Sala(camillas);
		this.revistero= new Revistero(revistas);
	}
	
	
	public static void main(String[] args) {
		Clinica clinica= new Clinica(4 , 10);
		for (int i = 0; i < 10; i++) {
			new Paciente("Paciente"+i, clinica).entrarAClinica();
		}
	}
	
	
}
