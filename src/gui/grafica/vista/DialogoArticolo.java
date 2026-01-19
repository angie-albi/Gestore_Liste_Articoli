package gui.grafica.vista;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modello.Articolo;

/**
 * La classe {@code DialogoArticolo} rappresenta una finestra di dialogo modale
 * per l'inserimento o la modifica dei dati di un articolo.
 * <p>
 * Il dialogo presenta quattro campi di input per raccogliere:
 * <ul>
 *   <li>Nome dell'articolo</li>
 *   <li>Categoria merceologica</li>
 *   <li>Prezzo (inizializzato a 0)</li>
 *   <li>Nota descrittiva opzionale</li>
 * </ul>
 * 
 * @author Angie Albitres
 */
public class DialogoArticolo {
	/** Campi di input per raccogliere i dati dell'articolo */
    private JTextField nome, categoria, prezzo, nota;
    
    /** Array di componenti per l'organizzazione del layout nel dialogo */
    private JComponent[] inputs;

    /**
     * Crea un nuovo oggetto DialogoArticolo inizializzando i componenti grafici.
     */
	public DialogoArticolo() {
		nome = new JTextField(20); 
		categoria = new JTextField(20); 
		prezzo = new JTextField("0", 20); // inizializzato a 0
		nota = new JTextField(20); 
		
		inputs = new JComponent []{
				new JLabel("Nome: "), nome,
				new JLabel("Categoria: "), categoria, 
				new JLabel("Prezzo: "), prezzo,
				new JLabel("Nota: "), nota,
		};
	}
	
	/**
     * Precompila i campi del dialogo con i dati di un articolo esistente.
     * 
     * @param a L'oggetto {@link Articolo} i cui dati devono essere mostrati nel dialogo.
     */
	public void setDatiArticolo(Articolo a) {
	    nome.setText(a.getNome());
	    nome.setEditable(false);
	    categoria.setText(a.getCategoria());
	    prezzo.setText(String.valueOf(a.getPrezzo()));
	    nota.setText(a.getNota());
	}

	/**
     * Mostra il dialogo e restituisce i valori inseriti
     * 
     * @param msg Il titolo del dialogo
     * @return Un array di String con [nome, categoria, prezzo, nota] oppure null se annullato
     */
	public String[] getInputs(String msg) {
		String[] res = new String[4];

		int result = JOptionPane.showConfirmDialog(null, inputs, msg, JOptionPane.CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			res[0] = nome.getText();
			res[1] = categoria.getText();
			res[2] = prezzo.getText();
			res[3] = nota.getText();
			return res;
		} 
		else {
			return null;
		}
	}


}
