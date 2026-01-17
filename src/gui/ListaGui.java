package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.grafica.vista.ListaPanel;
import modello.ListaDiArticoli;
import modello.exception.ArticoloException;
import modello.exception.ListaDiArticoliException;

public class ListaGui extends JFrame {

	public ListaGui(ListaDiArticoli model) {

		setTitle("Lista: " + model.getNome());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 600, 500);

		JPanel listaPanel = new ListaPanel(model);
		setContentPane(listaPanel);

		setVisible(true);
	}

}
