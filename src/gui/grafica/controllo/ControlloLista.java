package gui.grafica.controllo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.grafica.vista.CestinoDialog;
import gui.grafica.vista.ContentListaPanel;
import gui.grafica.vista.DialogoArticolo;
import gui.grafica.vista.PannelloListe;
import modello.Articolo;
import modello.GestioneListe;
import modello.ListaDiArticoli;
import modello.exception.ListaDiArticoliException;

public class ControlloLista implements ActionListener {
	private ContentListaPanel contenutoLista;
	private ListaDiArticoli model;
	private PannelloListe vistaPrincipale;

	public ControlloLista(ContentListaPanel contenutoLista, ListaDiArticoli model, PannelloListe vistaPrincipale) {
		this.contenutoLista = contenutoLista;
		this.model = model;
		this.vistaPrincipale = vistaPrincipale;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = ((JButton) e.getSource()).getText();

	    switch (comando) {
	        case "Aggiungi" -> gestisciAggiungi();
	        case "Rimuovi" -> gestisciRimuovi();
	        case "Aggiungi dal catalogo" -> gestisciAggiungiEsistente();
	        case "Visualizza Cestino" -> gestisciVisualizzaCestino();
	    }

		contenutoLista.updateView();
		vistaPrincipale.aggiornaDati();
	}

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

	private void aggiornaInterfacciaGlobale() {
		// TODO Auto-generated method stub
		
	}

	private void gestisciRimuovi() {
		Articolo articoloSel = contenutoLista.getArticoloSelezionato();

		if (articoloSel == null) {
			JOptionPane.showMessageDialog(null, "Seleziona un articolo dalla lista per rimuoverlo",
					"Nessuna selezione", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int conferma = JOptionPane.showConfirmDialog(null,
				"Sei sicuro di voler rimuovere l'articolo " + articoloSel.getNome()
						+ " dalla lista? (verra spostato el cestino)",
				"Conferma rimozione articolo", JOptionPane.YES_NO_OPTION);
		if (conferma == JOptionPane.YES_OPTION) {
			try {
				model.cancellaArticolo(articoloSel);
				JOptionPane.showMessageDialog(null, "Articolo rimosso");
			} catch (ListaDiArticoliException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void gestisciAggiungiEsistente() {

	}

	private void gestisciVisualizzaCestino() {
		ControlloCestino controllerCestino = new ControlloCestino(model, contenutoLista, vistaPrincipale);
		new CestinoDialog(null, model, controllerCestino);

		contenutoLista.updateView();
	}
}
