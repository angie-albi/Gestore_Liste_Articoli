package gui.grafica.vista;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DialogoArticolo {
	private JTextField nome, categoria, prezzo, nota;
	private JComponent[] inputs;

	/**
	 * Contruttore che inizializza le informazione dell'articolo
	 */
	public DialogoArticolo() {
		nome = new JTextField(20); 
		categoria = new JTextField(20); 
		prezzo = new JTextField(20); 
		nota = new JTextField(20); 
		
		inputs = new JComponent []{
				new JLabel("Nome: "), nome,
				new JLabel("Categoria: "), categoria, 
				new JLabel("Prezzo: "), prezzo,
				new JLabel("Nota: "), nota,
		};
	}

	/**
     * Mostra il dialogo e restituisce i valori inseriti
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
