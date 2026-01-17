package gui.grafica.vista;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gui.grafica.controllo.ControlloLista;
import modello.ListaDiArticoli;

public class ListaPanel extends JPanel{

	public ListaPanel(ListaDiArticoli model) {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		ContentListaPanel contenutoLista = new ContentListaPanel(model);
		ControlloLista controllo = new ControlloLista(contenutoLista, model);
		
		OpsListaPanel operazioniLista = new OpsListaPanel(controllo);
		
		add(contenutoLista, BorderLayout.CENTER);
		add(operazioniLista, BorderLayout.NORTH);
	}

}
