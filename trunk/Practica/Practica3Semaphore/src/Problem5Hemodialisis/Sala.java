package Problem5Hemodialisis;

import java.util.concurrent.Semaphore;

public class Sala extends Semaphore {

	private static final long serialVersionUID = 1L;

	public Sala(Integer cantidad) {
		super(cantidad, true);
	}

}
