package Problem5Hemodialisis;

import java.util.concurrent.Semaphore;

public class Revistero extends Semaphore {

	private static final long serialVersionUID = 1L;

	public Revistero(Integer cantidad) {
		super(cantidad);
	}

}
