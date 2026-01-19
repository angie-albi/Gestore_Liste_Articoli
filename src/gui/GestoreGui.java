package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import gui.grafica.controllo.ControlloGestore;
import gui.grafica.vista.PannelloArticoliGlobali;
import gui.grafica.vista.PannelloCategorie;
import gui.grafica.vista.PannelloListe;
import modello.GestioneListe;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// prima della chiusura programma si avvisa l'utente che non ha eseguito il salvataggio
		addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent e) {
	            if (GestioneListe.getModificato()) {
	                int risposta = JOptionPane.showConfirmDialog(null, 
	                    "Ci sono modifiche non salvate. Vuoi salvare prima di uscire?", 
	                    "Salvataggio richiesto", JOptionPane.YES_NO_CANCEL_OPTION);
	                
	                if (risposta == JOptionPane.YES_OPTION) {
	                    try {
	                        GestioneListe.salvaSistema("dati_sistema.txt");
	                        System.exit(0);
	                    } catch (Exception ex) {
	                        JOptionPane.showMessageDialog(null, "Errore nel salvataggio: " + ex.getMessage());
	                    }
	                } else if (risposta == JOptionPane.NO_OPTION) {
	                    System.exit(0);
	                }
	            } else {
	                System.exit(0);
	            }
	        }
		});
		
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
     * Punto di ingresso dell'applicazione. 
     * Tenta il caricamento automatico dei dati prima di avviare la GUI.
     * 
     * @param args Argomenti da riga di comando
     */
	public static void main(String[] args){
		String nomeFile = "dati_sistema.txt";

        try {
            // tenta il ripristino automatico
            GestioneListe.caricaSistema(nomeFile);
            System.out.println("Dati caricati correttamente da " + nomeFile);
            
        } catch (java.io.FileNotFoundException e) {
            // se il file non esiste
            System.out.println("Nessun salvataggio trovato. Avvio con sistema vuoto.");
            
        } catch (Exception e) {
            // altri errori
            JOptionPane.showMessageDialog(null, 
                "Si è verificato un errore nel caricamento automatico: " + e.getMessage(), 
                "Errore di Caricamento", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        new GestoreGui();
	}
}