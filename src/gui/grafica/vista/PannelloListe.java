package gui.grafica.vista;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.ListaGui;
import modello.GestioneListe;
import modello.ListaDiArticoli;

public class PannelloListe extends JPanel {
	private JList<String> listaNomi;
	private DefaultListModel<String> listModel;

	public PannelloListe() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Elenco delle liste
		listModel = new DefaultListModel<>();
		listaNomi = new JList<>(listModel);
		add(new JScrollPane(listaNomi), BorderLayout.CENTER);

		// Bottoni
		JPanel bottoni = new JPanel();
		JButton btnCrea = new JButton("Nuova Lista");
		JButton btnApri = new JButton("Apri Selezionata");
		JButton btnElimina = new JButton("Elimina Lista");

		bottoni.add(btnCrea);
		bottoni.add(btnApri);
		bottoni.add(btnElimina);
		add(bottoni, BorderLayout.NORTH);

		// Listener per aprire la lista
		btnApri.addActionListener(e -> {
			String nome = listaNomi.getSelectedValue();
			if (nome != null) {
				try {
					ListaDiArticoli l = GestioneListe.matchLista(nome);
					new ListaGui(l); // Apre la finestra della singola lista
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			}
		});

		aggiornaDati();
	}

	public void aggiornaDati() {
		listModel.clear();
		for (ListaDiArticoli l : GestioneListe.getListeArticoli()) {
			listModel.addElement(l.getNome());
		}
	}

}
