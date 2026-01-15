package gui.grafica.vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import modello.ListaDiArticoli;
import modello.Articolo;

public class ContentPanel extends JPanel {

	private ListaDiArticoli model;
	private JTable tabella;
	private DefaultTableModel tableModel;

	public ContentPanel(ListaDiArticoli model) {
		this.model = model;
		
		setLayout(new BorderLayout());
		
		// colonne della tabella 
		String[] colonne = {"Nome", "Categoria", "Prezzo (€)", "Nota"};
		
		// creazione modello della tabella
		tableModel = new DefaultTableModel(colonne, 0) {
			// tabella non modificabile
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
		
		// creazione tabella
        tabella = new JTable(tableModel);
        
        // configurazione 
        tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        tabella.getTableHeader().setReorderingAllowed(false); // impedisce di spostare le colonne
        tabella.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        tabella.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(tabella);
		
		JLabel label = new JLabel("Contenuto lista: " + model.getNome());
		add(label, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		updateView();
	}

	/**
     * Aggiorna la vista svuotando la tabella e riempiendola con i dati aggiornati del modello
     */
    public void updateView() {
        tableModel.setRowCount(0);

        for (Articolo a : model) {
            Object[] riga = {
                a.getNome(),
                a.getCategoria(),
                String.format("%.2f", a.getPrezzo()),
                a.getNota()
            };
            tableModel.addRow(riga);
        }
    }
	
    /**
     * Recupera l'articolo corrispondente alla riga selezionata nella tabella.
     * 
     * @return L'oggetto Articolo selezionato, oppure null se nulla è selezionato.
     */
    public Articolo getArticoloSelezionato() {
        int rigaSelezionata = tabella.getSelectedRow();

        if (rigaSelezionata == -1)
            return null; 

        String nome = (String) tableModel.getValueAt(rigaSelezionata, 0);
        String categoria = (String) tableModel.getValueAt(rigaSelezionata, 1);

        try {
            Articolo articoloCerc = new Articolo(nome, categoria);

            for (Articolo a : model) {
                if (a.equals(articoloCerc)) {
                    return a;
                }
            }
        } catch (Exception e) {
            //non genera eccezioni
        }

        return null;
    }
}
