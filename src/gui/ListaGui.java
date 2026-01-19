package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.grafica.controllo.ControlloGestore;
import gui.grafica.vista.ListaPanel;
import gui.grafica.vista.PannelloListe;
import modello.ListaDiArticoli;

/**La classe {@code ListaGui} rappresenta la finestra dedicata alla visualizzazione
 * e gestione dettagliata di una singola lista di articoli.
 * <p>
 * Questa finestra viene aperta quando l'utente seleziona una lista specifica dal
 * pannello principale e permette di:
 * <ul>
 *   <li>Visualizzare tutti gli articoli presenti nella lista</li>
 *   <li>Aggiungere nuovi articoli alla lista</li>
 *   <li>Rimuovere articoli (spostandoli nel cestino)</li>
 *   <li>Aggiungere articoli dal catalogo globale</li>
 *   <li>Visualizzare e gestire il cestino della lista</li>
 * </ul>
 * 
 * @author Angie Albitres
 */
@SuppressWarnings("serial")
public class ListaGui extends JFrame {

	public ListaGui(ListaDiArticoli model, PannelloListe vistaPrincipale, ControlloGestore controllerGlobale) {

		setTitle("Lista: " + model.getNome());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 150, 750, 500);

		JPanel listaPanel = new ListaPanel(model, vistaPrincipale, controllerGlobale);
		setContentPane(listaPanel);

		setVisible(true);
	}
}