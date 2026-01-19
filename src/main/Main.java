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
	        // --- LISTA 1: SPESA SETTIMANALE ---
	        ListaDiArticoli spesa = new ListaDiArticoli("Spesa Settimanale");
	        Articolo a1 = new Articolo("Latte", "Alimentari", 1.50, "Intero");
	        Articolo a2 = new Articolo("Pane", "Alimentari", 2.00);
	        Articolo a3 = new Articolo("Detersivo", "Pulizia", 5.50);

	        // Registrazione globale e inserimento nella lista
	        GestioneListe.inserisciArticolo(a1);
	        GestioneListe.inserisciArticolo(a2);
	        GestioneListe.inserisciArticolo(a3);
	        
	        spesa.inserisciArticolo(a1);
	        spesa.inserisciArticolo(a2);
	        spesa.inserisciArticolo(a3);
	        spesa.cancellaArticolo(a2); // Pane nel cestino
	        GestioneListe.inserisciLista(spesa);

	        // --- LISTA 2: PROGETTI BRICO ---
	        ListaDiArticoli brico = new ListaDiArticoli("Lavori in Casa");
	        Articolo b1 = new Articolo("Martello", "Attrezzi", 12.00);
	        Articolo b2 = new Articolo("Vernice", "Bricolage", 25.40, "Colore Blu");
	        Articolo b3 = new Articolo("Viti", "Ferramenta", 4.50, "Pacco da 100");

	        GestioneListe.inserisciArticolo(b1);
	        GestioneListe.inserisciArticolo(b2);
	        GestioneListe.inserisciArticolo(b3);
	        
	        brico.inserisciArticolo(b1);
	        brico.inserisciArticolo(b2);
	        brico.inserisciArticolo(b3);
	        brico.cancellaArticolo(b3); // Viti nel cestino
	        GestioneListe.inserisciLista(brico);

	        // --- LISTA 3: UFFICIO E TECNOLOGIA ---
	        ListaDiArticoli ufficio = new ListaDiArticoli("Materiale Ufficio");
	        Articolo u1 = new Articolo("Mouse", "Elettronica", 15.00);
	        Articolo u2 = new Articolo("Tastiera", "Elettronica", 30.00, "Meccanica");
	        Articolo u3 = new Articolo("Carta", "Cancelleria", 5.00, "A4 500 fogli");

	        GestioneListe.inserisciArticolo(u1);
	        GestioneListe.inserisciArticolo(u2);
	        GestioneListe.inserisciArticolo(u3);
	        
	        ufficio.inserisciArticolo(u1);
	        ufficio.inserisciArticolo(u2);
	        ufficio.inserisciArticolo(u3);
	        GestioneListe.inserisciLista(ufficio);

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
