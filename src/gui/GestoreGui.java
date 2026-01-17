package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import gui.grafica.vista.PannelloArticoliGlobali;
import gui.grafica.vista.PannelloCategorie;
import gui.grafica.vista.PannelloListe;
import modello.GestioneListe;
import modello.ListaDiArticoli;
import modello.exception.ArticoloException;
import modello.exception.GestioneListeException;
import modello.exception.ListaDiArticoliException;

public class GestoreGui extends JFrame {

	public GestoreGui() {
		setTitle("Gestore Liste");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane();

		// Aggiunta dei tre pannelli principali
		tabbedPane.addTab("Gestione Liste", new PannelloListe());
		tabbedPane.addTab("Categorie", new PannelloCategorie());
		tabbedPane.addTab("Articoli", new PannelloArticoliGlobali());

		add(tabbedPane);
		setVisible(true);
	}

	public static void main(String[] args) throws ListaDiArticoliException {
		ListaDiArticoli l = new ListaDiArticoli("Spesa");

		try {
			l.inserisciArticolo("Latte", "Alimentari", 1.50);
			l.inserisciArticolo("Pane", "Alimentari", 2.00);
			l.inserisciArticolo("Vino", "Bevande", 10.00);
		} catch (ArticoloException e) {
			// elimina try-cath
		}

		try {
			GestioneListe.inserisciLista(l);
		} catch (GestioneListeException e) {
			e.printStackTrace();
		}

		new GestoreGui();
	}
}
