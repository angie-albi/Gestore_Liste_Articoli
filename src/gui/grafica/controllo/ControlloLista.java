package gui.grafica.controllo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.grafica.vista.ContentPanel;
import gui.grafica.vista.DialogoArticolo;
import modello.Articolo;
import modello.ListaDiArticoli;
import modello.exception.ArticoloException;
import modello.exception.ListaDiArticoliException;

public class ControlloLista implements ActionListener{
	private ContentPanel contenutoLista;
	private ListaDiArticoli model;
	
	public ControlloLista(ContentPanel contenutoLista, ListaDiArticoli model) {
		this.contenutoLista = contenutoLista;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if (source.getText().equals("Aggiungi")) {
			gestisciAggiungi();
		} 
		else if (source.getText().equals("Rimuovi")) {
			gestisciRimuovi();
		} 
		else if (source.getText().equals("Aggiungi dal catalogo")) {
			gestisciAggiungiEsistente();
		} 
		else if (source.getText().equals("Visualizza Cestino")) {
			gestisciVisualizzaCestino();
		}
		
		contenutoLista.updateView();
	}
	
	private void gestisciAggiungi() {
		String[] inputs = new DialogoArticolo().getInputs("Aggiungi Articolo");
		
		if (inputs != null) {
			try {
				String nome = inputs[0];
                String categoria = inputs[1];
                double prezzo = Double.parseDouble(inputs[2]);
                String nota = inputs[3];
                
                try {
					model.inserisciArticolo(nome, categoria, prezzo, nota);
				} catch (ArticoloException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore Modello", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Il prezzo deve essere un numero valido!", "Errore Input", JOptionPane.ERROR_MESSAGE);
            } catch (ListaDiArticoliException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore Modello", JOptionPane.ERROR_MESSAGE);
            }
		}
	}
	
	private void gestisciRimuovi() {
		Articolo articoloSelezionato = contenutoLista.getArticoloSelezionato();
		
		if (articoloSelezionato == null) {
            JOptionPane.showMessageDialog(null, "Seleziona un articolo dalla lista per rimuoverlo.", "Nessuna selezione", JOptionPane.WARNING_MESSAGE);
        }
		else {
			try {
				int conferma = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler rimuovere l'articolo (" + articoloSelezionato.getNome() + ") dalla lista?");
		        
		        if (conferma == JOptionPane.YES_OPTION) {
						model.cancellaArticolo(articoloSelezionato);
		        }
			} catch (ListaDiArticoliException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void gestisciAggiungiEsistente() {
		
	}
	
	private void gestisciVisualizzaCestino() {
		
	}
}
