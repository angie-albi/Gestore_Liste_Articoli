package gui.rigaComando;

import jbook.util.Input;
import modello.GestioneListe;
import modello.ListaDiArticoli;
import modello.exception.GestioneListeException;
import modello.exception.ListaDiArticoliException;

/**
 * Gestisce l'interfaccia da riga di comando, permette di eseguire 
 * tutte le operazioni sulle liste
 */
public class InterfacciaRigaDiComando {

	/**
	 * Costruttore dell'interfaccia, mostra il menu principale
	 */
	public InterfacciaRigaDiComando() {
		menuPrincipale();
	}
	
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
					case 1 -> creaLista();
					case 2 -> selezionaLista();
					case 3 -> eliminaLista();
					//Articoli
					//Categorie
					case 4 -> on = false;
					
					default -> System.out.println("Scelta non valida, riprova");
				}
				
			} catch (NumberFormatException e) {
	            System.out.println("Errore: Inserisci un numero valido (da 1 a 4)");
	        } catch (Exception e) {
	            System.out.println("Si è verificato un errore: " + e.getMessage());
	            System.out.println("Riprova l'operazione");
	        }
			
		}
	}
	
	/**
	 * Menu delle operazioni che può eseguire l'utente
	 */
	private void visualizzaMenu() {
		System.out.println("\n----- OPERAZIONI DISPONIBILI -----");		
		System.out.println("1 - Crea una lista");	
		System.out.println("2 - Seleziona e gestisci lista");	
		System.out.println("3 - Elimina lista");
		System.out.println("4 - Torna indietro (menu interfacce)\n");	
	}
	/**
	 * Metodo interno che permette la creazione di una nuova lista
	 * 
	 * @throws ListaDiArticoliException Viene lanciata se il nome della lista è nullo o vuoto
	 * @throws GestioneListeException Viene lanciata se la lista è nulla o se il nome è già presente
	 */
	private void creaLista() throws ListaDiArticoliException, GestioneListeException {
		System.out.println("");
		String nome = Input.readString("Inserisci il nome della lista da creare:");
		
		ListaDiArticoli lista = new ListaDiArticoli(nome);
		GestioneListe.inserisciLista(lista);
		System.out.println("La lista "+ lista.getNome() +" è stata creata con successo");
	}
	
	private void selezionaLista() {
		
	}
	
	private void eliminaLista() throws GestioneListeException {
		System.out.println("");
		visualizzaListe();
		String nome = Input.readString("Inserisci il nome della lista da eliminare:");
	    
		GestioneListe.cancellaLista(nome);
		
	}
	
	
	private void visualizzaListe() {
	}
}
