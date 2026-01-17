package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.grafica.vista.ListaPanel;
import modello.ListaDiArticoli;
import modello.exception.ArticoloException;
import modello.exception.ListaDiArticoliException;

public class ListaGui extends JFrame{

	public ListaGui(ListaDiArticoli model){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 600, 500);
		setTitle("Lista: " + model.getNome());
		JPanel listaPanel = new ListaPanel(model);
		setContentPane(listaPanel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			ListaDiArticoli l = new ListaDiArticoli("Spesa");
			
			
			try {
				l.inserisciArticolo("Latte", "Alimentari", 1.50);
				l.inserisciArticolo("Pane", "Alimentari", 2.00);
				l.inserisciArticolo("Vino", "Bevande", 10.00);
			} catch (ArticoloException e) {
				// elimina try-cath
			}

			new ListaGui(l);
			
		} catch (ListaDiArticoliException e) {
			// elimina try-cath
		}
	}

}



