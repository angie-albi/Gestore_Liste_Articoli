package gui.grafica.controllo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.grafica.vista.CestinoDialog;
import gui.grafica.vista.ContentPanel;
import modello.Articolo;
import modello.ListaDiArticoli;
import modello.exception.ListaDiArticoliException;

public class ControlloCestino implements ActionListener{
	
	private CestinoDialog vistaCestino;
    private ListaDiArticoli model;
    private ContentPanel vistaPrincipale;
    
    public ControlloCestino(ListaDiArticoli model, ContentPanel vistaPrincipale) {
        this.model = model;
        this.vistaPrincipale = vistaPrincipale;
    }
    
    public void setVista(CestinoDialog vistaCestino) {
        this.vistaCestino = vistaCestino;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
        String comando = source.getText();

        if (comando.equals("Recupera")) {
            gestisciRecupera();
        } else if (comando.equals("Svuota Cestino")) {
            gestisciSvuotaCestino();
        }
	}
	
	private void gestisciRecupera() {
		Articolo daRecuperare = vistaCestino.getArticoloSelezionato();
        if (daRecuperare == null) {
            JOptionPane.showMessageDialog(vistaCestino, "Seleziona un articolo da recuperare");
            return;
        }

        try {
            model.recuperaArticolo(daRecuperare);
        } catch (ListaDiArticoliException ex) {
            JOptionPane.showMessageDialog(vistaCestino, ex.getMessage());
        }
	}

	private void gestisciSvuotaCestino() {
		int conferma = JOptionPane.showConfirmDialog(vistaCestino, "Eliminare definitivamente tutto il cestino?");
		
        if (conferma == JOptionPane.YES_OPTION) 
            model.svuotaCancellati();
	}
}
