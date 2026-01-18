package gui.grafica.vista;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.grafica.controllo.ControlloGestore;
import modello.GestioneListe;
import modello.ListaDiArticoli;

/**
 * Vista per la gestione delle categorie globali in formato tabellare.
 * Mostra il numero di articoli associati a ogni categoria tra tutte le liste
 */
@SuppressWarnings("serial")
public class PannelloCategorie extends JPanel {
	private JTable tabellaCategorie;
    private DefaultTableModel tableModel;

    public PannelloCategorie(ControlloGestore controllo) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        String[] colonne = {"Nome Categoria", "Articoli", "Cestino"};
        
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tabellaCategorie = new JTable(tableModel);
        tabellaCategorie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaCategorie.setFillsViewportHeight(true); 
        
        add(new JScrollPane(tabellaCategorie), BorderLayout.CENTER);

        // Pannello per i bottoni di operazione
        JPanel bottoni = new JPanel();
        JButton btnAggiungi = new JButton("Aggiungi Categoria");
        JButton btnElimina = new JButton("Elimina Categoria");

        // Assegnazione del controller ai bottoni
        btnAggiungi.addActionListener(controllo);
        btnElimina.addActionListener(controllo);

        bottoni.add(btnAggiungi);
        bottoni.add(btnElimina);
        add(bottoni, BorderLayout.NORTH);

        aggiornaDati();
    }

    /**
     * Sincronizza la JTable con i dati presenti nel modello.
     * Calcola l'utilizzo globale di ogni categoria in tutte le liste
     */
    public void aggiornaDati() {
        tableModel.setRowCount(0);
        
        for (String cat : GestioneListe.getCategorie()) {
            int contaAttivi = 0;
            int contaCestino = 0;
            
            // per ogni categoria, scansioniamo tutte le liste esistenti
            for (ListaDiArticoli lista : GestioneListe.getListeArticoli()) {
                java.util.List<modello.Articolo> cancellati = lista.getArticoliCancellati();
                
                
                for (modello.Articolo a : lista) {
                    if (a.getCategoria().equalsIgnoreCase(cat)) {
                        if (cancellati.contains(a)) {
                            contaCestino++;
                        } else {
                            contaAttivi++;
                        }
                    }
                }
            }
            
            Object[] riga = { cat, contaAttivi, contaCestino };
            tableModel.addRow(riga);
        }
    }

    /**
     * Restituisce il nome della categoria selezionata nella tabella
     */
    public String getCategoriaSelezionata() {
        int riga = tabellaCategorie.getSelectedRow();
        if (riga == -1) 
        	return null;
        
        return (String) tableModel.getValueAt(riga, 0);
    }
}
