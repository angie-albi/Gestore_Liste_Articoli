package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import gui.grafica.controllo.ControlloGestore;
import gui.grafica.vista.PannelloArticoliGlobali;
import gui.grafica.vista.PannelloCategorie;
import gui.grafica.vista.PannelloListe;
import modello.Articolo;
import modello.GestioneListe;
import modello.ListaDiArticoli;
import modello.exception.ListaDiArticoliException;

/**
 * La classe {@code GestoreGui} rappresenta la finestra principale dell'interfaccia grafica
 * <p>
 * Funge da contenitore principale per le diverse sezioni del sistema, organizzate in schede:
 * <ul>
 *   <li>Gestione delle liste</li>
 *   <li>Registro delle categorie</li>
 *   <li>Registro globale degli articoli</li>
 * </ul>
 * Implementa l'integrazione tra i pannelli della vista e il relativo controller globale.
 * * @author Angie Albitres
 */
@SuppressWarnings("serial")
public class GestoreGui extends JFrame {
	/**
	 * Inizializza il frame principale del gestore.
	 * Configura le impostazioni della finestra, crea il controller globale e
	 * stabilisce le connessioni tra le viste e il controller per permettere
	 * la corretta gestione degli eventi
	 */
	public GestoreGui() {
		setTitle("Gestore Liste");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane();

		// aggiunta controllo
		ControlloGestore controllo = new ControlloGestore();
		
		PannelloListe pannelloListe = new PannelloListe(controllo);
		controllo.setVistaListe(pannelloListe);
		
		PannelloCategorie pannelloCat = new PannelloCategorie(controllo);
		controllo.setVistaCategorie(pannelloCat);
		
		PannelloArticoliGlobali pannelloArt = new PannelloArticoliGlobali(controllo);
	    controllo.setVistaArticoli(pannelloArt);
		
		// aggiunta dei tre pannelli principali
		tabbedPane.addTab("Gestione Liste", pannelloListe);
		tabbedPane.addTab("Categorie", pannelloCat);
		tabbedPane.addTab("Articoli", pannelloArt);

		add(tabbedPane);
		setVisible(true);
	}

	/**
	 * Punto di ingresso dell'interfaccia grafica.
	 * 
	 * @param args Argomenti da riga di comando (non utilizzati).
	 * @throws ListaDiArticoliException In caso di errori durante la creazione della lista di prova.
	 */
	public static void main(String[] args) throws ListaDiArticoliException {
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

		new GestoreGui();
	}
}