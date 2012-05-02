package model;

public class MiThread extends Thread {
	public MiMonitor m;

	public MiThread(MiMonitor m) {
		this.m = m;
	}

	public void run() {
		m.add();
	}
}
