package gui.grafica.controllo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.grafica.vista.CestinoDialog;
import gui.grafica.vista.ContentListaPanel;
import gui.grafica.vista.DialogoArticolo;
import gui.grafica.vista.OpsListaPanel;
import gui.grafica.vista.PannelloListe;
import modello.Articolo;
import modello.GestioneListe;
import modello.ListaDiArticoli;
import modello.exception.ListaDiArticoliException;

/**
 * La classe {@code ControlloLista} gestisce le operazioni eseguibili su una singola lista di articoli
 * <p>
 * Coordina le seguenti funzionalità:
 * <ul>
 *   <li>Aggiunta e rimozione di articoli</li>
 *   <li>Selezione di articoli dal catalogo globale</li>
 *   <li>Gestione del cestino della lista</li>
 *   <li>Ricerca articoli per prefisso</li>
 * </ul>
 * 
 * @author Angie Albitres
 *
 */
public class ControlloLista implements ActionListener {
	private ContentListaPanel contenutoLista;
	private ListaDiArticoli model;
	private PannelloListe vistaPrincipale;
	private ControlloGestore controllerGlobale;
	private OpsListaPanel vistaOperazioni;

	/**
	 * Costruisce un nuovo controller per la gestione di una lista specifica
	 * 
	 * @param contenutoLista La vista del contenuto della lista
	 * @param model Il modello della lista da gestire
	 * @param vistaPrincipale Il pannello principale delle liste
	 * @param controllerGlobale Il controller globale per gli aggiornamenti
	 */
	public ControlloLista(ContentListaPanel contenutoLista, ListaDiArticoli model, 
            PannelloListe vistaPrincipale, ControlloGestore controllerGlobale) {
		this.contenutoLista = contenutoLista;
		this.model = model;
		this.vistaPrincipale = vistaPrincipale;
		this.controllerGlobale = controllerGlobale; 
	}
	
	/**
	 * Collega la vista dei controlli operativi al controller
	 * 
	 * @param vistaOperazioni Il pannello contenente i controlli operativi
	 */
	public void setVistaOperazioni(OpsListaPanel vistaOperazioni) {
        this.vistaOperazioni = vistaOperazioni;
    }

	/**
	 * Gestisce gli eventi generati dai componenti dell'interfaccia.
	 * Smista le richieste ai metodi specifici e aggiorna le viste dopo ogni operazione
	 * 
	 * @param e L'evento di azione generato
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = "";

		// se la fonte è un bottone, leggiamo il testo (es. "Aggiungi")
        if (e.getSource() instanceof JButton) {
            comando = ((JButton) e.getSource()).getText();
        } else
        // se la fonte è il JTextField (pressione di INVIO)
        if (e.getSource() instanceof JTextField) {
            comando = "Cerca";
        }
		
	    switch (comando) {
	        case "Aggiungi" -> gestisciAggiungi();
	        case "Rimuovi" -> gestisciRimuovi();
	        case "Aggiungi dal catalogo" -> gestisciAggiungiEsistente();
	        case "Visualizza Cestino" -> gestisciVisualizzaCestino();
	        case "Cerca" -> gestisciRicerca();
	        case "Reset" -> gestisciReset();
	    }
	    
	 // setta a true il valore modifica del GestoreListe
        switch(comando) {
        	case "Aggiungi", "Rimuovi", "Aggiungi dal catalogo" -> GestioneListe.setModificato(true);
        }
        
	    if (!comando.equals("Cerca") && !comando.equals("Reset")) {
            contenutoLista.updateView();
        }
        
        if (controllerGlobale != null) {
            controllerGlobale.aggiornaTutto();
        }
		vistaPrincipale.aggiornaDati();
	}

	/**
	 * Gestisce l'aggiunta di un nuovo articolo tramite dialogo di input.
	 * L'articolo viene aggiunto alla lista locale e al catalogo globale
	 */
	private void gestisciAggiungi() {
	    String[] inputs = new DialogoArticolo().getInputs("Aggiungi Articolo");

	    if (inputs != null) {
	        try {
	            Articolo nuovo = new Articolo(inputs[0], inputs[1], 
	                                          Double.parseDouble(inputs[2]), inputs[3]);

	            // aggiunta dell'articolo nella lista locale
	            model.inserisciArticolo(nuovo);
	            try {
	                GestioneListe.inserisciArticolo(nuovo);
	            } catch (Exception e) {
	                // se l'articolo esiste già globalmente, il sistema ignora l'errore 
	            }

	            // aggiorna la vista corrente
	            contenutoLista.updateView(); 

	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage());
	        }
	    }	
	}


	/**
	 * Gestisce la rimozione dell'articolo selezionato spostandolo nel cestino.
	 * Richiede conferma prima di procedere
	 */
	private void gestisciRimuovi() {
		Articolo articoloSel = contenutoLista.getArticoloSelezionato();

		if (articoloSel == null) {
			JOptionPane.showMessageDialog(null, "Seleziona un articolo dalla lista per rimuoverlo", "Nessuna selezione", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int conferma = JOptionPane.showConfirmDialog(null,
				"Sei sicuro di voler rimuovere l'articolo " + articoloSel.getNome()
						+ " dalla lista? (verra spostato el cestino)",
				"Conferma rimozione", JOptionPane.YES_NO_OPTION);
		if (conferma == JOptionPane.YES_OPTION) {
			try {
				model.cancellaArticolo(articoloSel);
			} catch (ListaDiArticoliException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	/**
	 * Gestisce l'aggiunta alla lista di un articolo selezionato dal catalogo globale
	 */
	private void gestisciAggiungiEsistente() {
	    List<Articolo> catalogo = GestioneListe.getArticoli();
	    if (catalogo.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Il catalogo globale è vuoto!");
	        return;
	    }

	    String[] opzioni = new String[catalogo.size()];
	    for (int i = 0; i < catalogo.size(); i++) {
	        Articolo a = catalogo.get(i);
	        opzioni[i] = a.getNome() + " [" + a.getCategoria() + "]";
	    }

	    String scelta = (String) JOptionPane.showInputDialog(null, 
	            "Seleziona un articolo dal catalogo:", "Aggiungi articolo dal catalogo",
	            JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);

	    if (scelta != null) {
	        for (int i = 0; i < opzioni.length; i++) {
	            if (opzioni[i].equals(scelta)) {
	                try {
	                    model.inserisciArticolo(catalogo.get(i));
	                    break;
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, ex.getMessage());
	                }
	            }
	        }
	    }
	}

	/**
	 * Apre la finestra del cestino per visualizzare e gestire gli articoli cancellati
	 */
	private void gestisciVisualizzaCestino() {
		ControlloCestino controllerCestino = new ControlloCestino(model, contenutoLista, vistaPrincipale);
		new CestinoDialog(null, model, controllerCestino);

		contenutoLista.updateView();
	}
	
	/**
	 * Gestisce la ricerca di articoli per prefisso del nome.
	 * Mostra il pulsante Reset se la ricerca produce risultati
	 */
	private void gestisciRicerca() {
        if (vistaOperazioni == null) return;
        
        String prefisso = vistaOperazioni.getTestoRicerca();
        
        if (prefisso == null || prefisso.isBlank()) {
            gestisciReset(); // se premo invio su campo vuoto, resetta
        } else {
            java.util.List<Articolo> risultati = model.ricercaArticolo(prefisso);
            contenutoLista.mostraRisultatiRicerca(risultati);
            vistaOperazioni.mostraReset(true); // mostra il tasto Reset
        }
    }
	
	/**
	 * Ripristina la visualizzazione normale della lista dopo una ricerca
	 */
	private void gestisciReset() {
        if (vistaOperazioni == null) return;
        
        vistaOperazioni.pulisciCampo();   // svuota il JTextField
        vistaOperazioni.mostraReset(false); // nasconde il tasto Reset
        contenutoLista.updateView();      // torna alla lista normale (solo attivi)
    }
}
