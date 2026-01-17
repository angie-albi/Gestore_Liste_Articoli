package gui.grafica.controllo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.grafica.vista.PannelloCategorie;
import modello.GestioneListe;
import modello.exception.GestioneListeException;

/**
 * Controller per la gestione delle operazioni globali (Categorie e Liste).
 */
public class ControlloGestore implements ActionListener {

	private PannelloCategorie vistaCategorie;

	/**
	 * Metodo per collegare la vista al controller dopo la creazione
	 */
	public void setVistaCategorie(PannelloCategorie vista) {
		this.vistaCategorie = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = ((JButton) e.getSource()).getText();

        switch (comando) {
            case "Aggiungi Categoria" -> gestisciAggiungiCategoria();
            case "Elimina Categoria" -> gestisciAggiungiCategoria();
        }

	}

	private void gestisciAggiungiCategoria() {
        String nome = JOptionPane.showInputDialog(null, "Inserisci il nome della nuova categoria:");
        if (nome != null && !nome.isBlank()) {
            try {
                GestioneListe.inserisciCategoria(nome);
                vistaCategorie.aggiornaDati();
            } catch (GestioneListeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void gestisciEliminaCategoria() {
        String selezionata = vistaCategorie.getCategoriaSelezionata();
        if (selezionata == null) {
            JOptionPane.showMessageDialog(null, "Seleziona una categoria da eliminare.");
            return;
        }

        try {
            GestioneListe.cancellaCategoria(selezionata);
            vistaCategorie.aggiornaDati();
        } catch (GestioneListeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

}