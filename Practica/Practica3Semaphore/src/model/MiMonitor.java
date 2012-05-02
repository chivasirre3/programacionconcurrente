package model;

public class MiMonitor {
	public int i = 0;

	public synchronized void add() {
		for (i = 0; i < 100000;) {
			i++;
		}
		System.out.println(i);
	}

	public  int get() {
		return i;
	}

	public static void main(String args[]) {
		try {
			MiMonitor s = new MiMonitor();
			MiThread hola = new MiThread(s);
			MiThread hola2 = new MiThread(s);
			hola.start();
			hola2.start();
			System.err.println(s.get());
		} catch (Exception e) {
			System.out.println(1);
		}
	}
}
