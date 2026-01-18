package main;

import gui.GestoreGui;
import gui.rigaComando.InterfacciaRigaDiComando;
import jbook.util.Input;
import modello.Articolo;
import modello.GestioneListe;
import modello.ListaDiArticoli;

/**
 * Classe Main principale del programma che avvia le interfaccie disponibili dell'applicazione
 */
public class Main {

	/**
	 * Main principale del programma, permette all'utente di scegliere tra
	 * <ul>
	 *   <li> Interfaccia grafica </li>
	 *   <li> Interfaccia da riga di comando </li>
	 * </ul>
	 * @param args Argomenti da riga di comando
	 */
	public static void main(String[] args) {
		boolean on = true;
		
		while (on) {
			try {
				menuInterfacce();
				
				int scelta = Input.readInt("Scegli l'interfaccia:");
				switch (scelta){
					case 0 -> on = false;
					case 1 -> {
						System.out.println("Avvio interfaccia grafica...");
						interfacciaGrafica();
					}
					case 2 -> {
						System.out.println("Avvio interfaccia da riga di comando...");
						interfacciaRigaComando();
					}
					
			
					default -> System.out.println("\nScelta non valida, riprova");
				}
			} catch (NumberFormatException e) {
	            System.out.println("\nErrore: Inserisci un numero valido (da 0 a 2), riprova");
	        } catch (Exception e) {
	            System.out.println("\nSi è verificato un errore: " + e.getMessage());
	            System.out.println("Riprova");
	        }
		}
		System.out.println("Chiusura del programma...");
	}
	
	/**
	 * Metodo per avviare l'interfaccia da riga di comando
	 */
	private static void interfacciaRigaComando() {
		new InterfacciaRigaDiComando();
	}

	/**
	 * Metodo per avviare l'interfaccia grafica
	 */
	private static void interfacciaGrafica() {
		caricaDatiEsempio(); 
	    new GestoreGui();			
	}
	
	private static void caricaDatiEsempio() {
	    try {
	        // Creazione di una lista di esempio
	        ListaDiArticoli spesa = new ListaDiArticoli("Spesa Settimanale");
	        
	        // Creazione di articoli con diverse categorie
	        Articolo a1 = new Articolo("Latte", "Alimentari", 1.50, "Intero");
	        Articolo a2 = new Articolo("Pane", "Alimentari", 2.00);
	        Articolo a3 = new Articolo("Detersivo", "Pulizia", 5.50);
	        
	        // Inserimento negli elenchi globali (registra anche le categorie)
	        GestioneListe.inserisciArticolo(a1);
	        GestioneListe.inserisciArticolo(a2);
	        GestioneListe.inserisciArticolo(a3);
	        
	        // Aggiunta alla lista specifica
	        spesa.inserisciArticolo(a1);
	        spesa.inserisciArticolo(a2);
	        spesa.inserisciArticolo(a3);
	        
	        // Spostiamo un elemento nel cestino per testare i contatori
	        spesa.cancellaArticolo(a2);
	        
	        // Registrazione della lista nel sistema
	        GestioneListe.inserisciLista(spesa);
	        
	    } catch (Exception e) {
	        System.err.println("Errore nel caricamento dati esempio: " + e.getMessage());
	    }
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
