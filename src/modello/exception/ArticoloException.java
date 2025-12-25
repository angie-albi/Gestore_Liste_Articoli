package modello.exception;

/**
 * Eccezione specifica per segnalare errori nei dati di un {@code Articolo}
 * 
 * @author Angie Albitres
 */
public class ArticoloException extends Exception{
	/**
	 * Crea una nuova eccezione con il messaggio di errore indicato
	 * 
	 * @param messaggio Descrizione dell'errore
	 */
	public ArticoloException(String messaggio) {
        super(messaggio);
    }
}
