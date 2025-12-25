package modello.exception;

/**
 * Eccezione specifica per segnalare errori nei dati di un {@code GestioneListe}
 * 
 * @author Angie Albitres
 */
public class GestioneListeException extends Exception{
	/**
	 * Crea una nuova eccezione con il messaggio di errore indicato
	 * 
	 * @param messaggio Descrizione dell'errore
	 */
	public GestioneListeException(String messaggio) {
        super(messaggio);
    }
}
