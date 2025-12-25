package modello.exception;

/**
 * Eccezione specifica per segnalare errori nei dati di un {@code ListaDiArticoli}
 * 
 * @author Angie Albitres
 */
public class ListaDiArticoliException extends Exception{
	/**
	 * Crea una nuova eccezione con il messaggio di errore indicato
	 * 
	 * @param messaggio Descrizione dell'errore
	 */
	public ListaDiArticoliException(String messaggio) {
        super(messaggio);
    }
}
