package gui.rigaComando;

import java.util.List;

import jbook.util.Input;
import modello.GestioneListe;
import modello.ListaDiArticoli;
import modello.exception.GestioneListeException;
import modello.exception.ListaDiArticoliException;

/**
 * Gestisce l'interfaccia da riga di comando, permette di gestire:
 * <ul>
 *   <il> liste </il>
 *   <il> articoli </il>
 *   <il> categorie </il>
 * </ul>
 */
public class InterfacciaRigaDiComando {

	/**
	 * Costruttore dell'interfaccia, mostra il menu principale 
	 */
	public InterfacciaRigaDiComando() {
		menuPrincipale();
	}
	
	
	// --------- GESTIONE LISTE ---------
	
	/**
	 * Menu principale che permette di effettuare le seguenti operazioni:
	 * <ul>
	 *   <li> creazione Lista</li>
	 *   <li> selezione lista</li>
	 *   <li> eliminazione lista</li>
	 * </ul>
	 */
	private void menuPrincipale() {
		boolean on = true;
		
		while(on) {
			try {
				visualizzaMenu();
				
				int scelta = Input.readInt("Scegli l'operazione:");
				switch (scelta){
					case 0 -> on = false;
					case 1 -> creaLista();
					case 2 -> visualizzaListe();
					case 3 -> selezionaLista();
					case 4 -> eliminaLista();
					//Articoli
					//Categorie
					
					
					default -> System.out.println("\nScelta non valida, riprova...");
				}
				
			} catch (NumberFormatException e) {
	            System.out.println("\nErrore: Inserisci un numero valido (da 0 a 4)");
	        } catch (Exception e) {
	            System.out.println("\nSi è verificato un errore: " + e.getMessage());
	            System.out.println("Riprova l'operazione...");
	        }
		}
		System.out.println("Chiusura interfaccia da riga di comando...");
	}
	
	/**
	 * Menu delle operazioni che può eseguire l'utente
	 */
	private void visualizzaMenu() {
		System.out.println("\n----- OPERAZIONI DISPONIBILI -------");	
		System.out.println("0 - Torna indietro (menu interfacce)");
		System.out.println("1 - Crea una lista");	
		System.out.println("2 - Visualizza liste");
		System.out.println("3 - Seleziona una lista per gestirla");	
		System.out.println("4 - Elimina una lista");
		System.out.println("------------------------------------\n");
	}
	/**
	 * Metodo interno che permette la creazione di una nuova lista
	 * 
	 * @throws ListaDiArticoliException Viene lanciata se il nome della lista è nullo o vuoto
	 * @throws GestioneListeException Viene lanciata se la lista è nulla o se il nome è già presente
	 */
	private void creaLista() throws ListaDiArticoliException, GestioneListeException {
		String nome = Input.readString("Inserisci il nome della lista da creare:");
		
		ListaDiArticoli lista = new ListaDiArticoli(nome);
		GestioneListe.inserisciLista(lista);
		System.out.println("La lista "+ lista.getNome() +" è stata creata con successo");
	}
	
	/**
	 * Seleziona una lista tra quelle disponibili per gestirla
	 * 
	 * @throws GestioneListeException Viene lanciata se il nome è vuoto o se la lista non esiste
	 */
	private void selezionaLista() throws GestioneListeException {
		visualizzaListe();
		List<ListaDiArticoli> liste = GestioneListe.getListeArticoli();
	    
	    if (!liste.isEmpty()) {
			String nome = Input.readString("Inserisci il nome della lista da selezionare:");
			
			ListaDiArticoli lista = GestioneListe.matchLista(nome);
			System.out.println("La lista "+ lista.getNome() +" è stata selezionata con successo");
		
			menuLista(lista);
	    }
	}
	
	/**
	 * Elimina una lista tra quelle disponibili
	 * 
	 * @throws GestioneListeException Viene lanciata se il nome è vuoto o se la lista non viene trovata
	 */
	private void eliminaLista() throws GestioneListeException {
		visualizzaListe();
		List<ListaDiArticoli> liste = GestioneListe.getListeArticoli();
	    
	    if (!liste.isEmpty()) {
			String nome = Input.readString("Inserisci il nome della lista da eliminare:");
		    
			GestioneListe.cancellaLista(nome);
			System.out.println("La lista "+ nome +" è stata cancellata con successo");
	    }
	}
	
	/**
	 * Visualizza tutte le liste disponibili
	 */
	private void visualizzaListe() {
		List<ListaDiArticoli> liste = GestioneListe.getListeArticoli();
	    
	    if (liste.isEmpty()) {
	        System.out.println("Non ci sono liste");
	    } else {
	        System.out.println("Liste disponibili:");
	        
	        for (ListaDiArticoli l : liste) {
	            System.out.println("- " + l.getNome());
	        }
	    }
	}
	
	
	// --------- LISTE DI ARTICOLI ---------
	
	/**
	 * Menu delle operazioni che può eseguire l'utente
	 */
	private void menuLista(ListaDiArticoli lista) {
		boolean on = true;
		
		while(on) {
			try {
				visualizzaMenuLista();
				
				int scelta = Input.readInt("Scegli l'operazione:");
				switch (scelta){
					case 0 -> on = false;
//					case 1 -> creaArticolo();
//					case 2 -> visualizzaArticolo();
//					case 3 -> cercaArticolo();
//					case 4 -> eliminaArticolo();
//					case 5 -> modificaArticolo();
//					case 6 -> calcoloPrezzoTotale();
					
					default -> System.out.println("\nScelta non valida, riprova...");
				}
			} catch (NumberFormatException e) {
	            System.out.println("\nErrore: Inserisci un numero valido (da 0 a 6)");
	        } catch (Exception e) {
	            System.out.println("\nSi è verificato un errore: " + e.getMessage());
	            System.out.println("Riprova l'operazione...");
	        }
		}
		System.out.println("Chiusura della lista " + lista.getNome() + "...");
	}
	
	/**
	 * Menu delle operazioni che può eseguire l'utente sulle liste
	 */
	private void visualizzaMenuLista() {
		System.out.println("\n----- OPERAZIONI DISPONIBILI -------");	
		System.out.println("0 - Torna indietro (menu gestione delle liste)");
		System.out.println("1 - Crea un articolo");	
		System.out.println("2 - Visualizza articoli");
		System.out.println("3 - Cerca un articolo ");	
		System.out.println("4 - Elimina un articolo ");	
		System.out.println("5 - Modifica un articolo (prezzo, categoria, nota)");
		System.out.println("6 - Calcola il prezzo totale");	
		System.out.println("------------------------------------\n");
	}
	
}
