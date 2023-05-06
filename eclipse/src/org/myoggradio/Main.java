package org.myoggradio;

public class Main {

	public static void main(String[] args) throws Exception {
		Initialisieren ini = new Initialisieren();
		ini.start();
		Verarbeitung v = new Verarbeitung();
		v.start();
	}

}
