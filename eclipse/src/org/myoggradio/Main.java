package org.myoggradio;

public class Main {

	public static void main(String[] args) throws Exception {
		Initialisieren ini = new Initialisieren();
		ini.start();
		MainMenu mm = new MainMenu();
		mm.anzeigen();
	}

}
