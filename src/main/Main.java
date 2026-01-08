package main;

import gui.rigaComando.InterfacciaRigaDiComando;
import jbook.util.Input;

public class Main {

	/**
	 * Main principale del programma, permette all'utente di scegliere tra
	 * Interfaccia grafica e Interfaccia da riga di comando
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		boolean on = true;
		
		while (on) {
			try {
				menuInterfacce();
				
				int scelta = Input.readInt("Scegli l'interfaccia:");
				switch (scelta){
					case 0 -> {
						System.out.println("Chiusura del programma...");
						on = false;
					}
					case 1 -> {
						System.out.println("Avvio interfaccia grafica...");
						interfacciaGrafica();
					}
					case 2 -> {
						System.out.println("Avvio interfaccia da riga di comando...");
						interfacciaRigaComando();
					}
					
					
					default -> System.out.println("Scelta non valida, riprova");
				}
			} catch (NumberFormatException e) {
	            System.out.println("Errore: Inserisci un numero valido (da 1 a 3), riprova");
	        } catch (Exception e) {
	            System.out.println("Si è verificato un errore: " + e.getMessage());
	            System.out.println("Riprova");
	        }
		}
	}
	
	private static void interfacciaRigaComando() {
		new InterfacciaRigaDiComando();
	}

	private static void interfacciaGrafica() {
		
	}
	
	/**
	 * Menu delle interfaccie disponibili per l'utente
	 */
	private static void menuInterfacce() {
		System.out.println("\n----- INTERFACCE DISPONIBILI -------");	
		System.out.println("0 - Esci");
		System.out.println("1 - Interfaccia grafica");	
		System.out.println("2 - Interfaccia da riga di comando");	
		System.out.println("------------------------------------\n");
	}
}
