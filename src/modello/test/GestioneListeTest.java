package modello.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modello.Articolo;
import modello.GestioneListe;
import modello.ListaDiArticoli;
import modello.exception.ArticoloException;
import modello.exception.GestioneListeException;
import modello.exception.ListaDiArticoliException;

/**
 * La classe {@code GestioneListeTest} contiene i test unitari per verificare la logica della classe {@code GestioneListe}
 * <p> Vengono verificati i seguenti aspetti:
 * <ul>
 *   <li>Inizializzazione e ripristino del sistema</li>
 *   <li>Gestione dell'anagrafica delle liste (inserimento, ricerca e cancellazione)</li>
 *   <li>Gestione delle categorie merceologiche e dei relativi vincoli</li>
 *   <li>Registrazione e rimozione globale degli articoli nel sistema</li>
 * </ul>
 * 
 * @author Angie Albitres
 */
class GestioneListeTest {

	private ListaDiArticoli l1;
	private Articolo a1;

	/**
	 * Ripristina lo stato del sistema e prepara gli oggetti di riferimento prima di ogni test
	 * 
	 * @throws Exception In caso di errori durante la configurazione
	 */
	@BeforeEach
	void setUp() throws Exception {
		GestioneListe.reset();
		l1 = new ListaDiArticoli("Spesa");
		a1 = new Articolo("Latte", "Alimentari", 1.50);
	}

	/**
	 * Verifica che l'inserimento di una lista avvenga correttamente e gestisca i duplicati o valori nulli
	 */
	@Test
	void testInserisciLista() {
		assertDoesNotThrow(() -> {
			GestioneListe.inserisciLista(l1);
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.inserisciLista(new ListaDiArticoli("Spesa"));
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.inserisciLista(null);
		});
	}
	
	/**
	 * Verifica la condivisione degli oggetti {@link Articolo} tra diverse 
	 * istanze di {@link ListaDiArticoli}
	 * 
	 * @throws ArticoloException Se i dati dell'articolo (nome o prezzo) non sono validi
	 * @throws ListaDiArticoliException Se l'articolo è già presente nella lista 
	 * o se si verificano errori durante l'inserimento
	 */
	@Test
	void testCondivisioneArticoliTraListe() throws ArticoloException, ListaDiArticoliException{
	    Articolo a1 = new Articolo("Latte", "Alimentari", 1.50);
	    
	    ListaDiArticoli listaCasa = new ListaDiArticoli("Spesa Casa");
	    ListaDiArticoli listaUfficio = new ListaDiArticoli("Spesa Ufficio");
	    
	    listaCasa.inserisciArticolo(a1);
	    listaUfficio.inserisciArticolo(a1);
	    
	    assertEquals(1.50, listaCasa.calcoloPrezzoTotale(), 0.001);
	    assertEquals(1.50, listaUfficio.calcoloPrezzoTotale(), 0.001);
	    
	    a1.setPrezzo(2.00);
	    
	    assertEquals(2.00, listaCasa.calcoloPrezzoTotale(), 0.001);
	    assertEquals(2.00, listaUfficio.calcoloPrezzoTotale(), 0.001);
	}
	
	/**
	 * Verifica la ricerca di una lista tramite il nome e la gestione dei casi di lista non trovata
	 * 
	 * @throws GestioneListeException Se si verificano errori durante l'inserimento della lista di test
	 */
	@Test
	void testMatchLista() throws GestioneListeException {
		GestioneListe.inserisciLista(l1);
		
		assertEquals(l1, GestioneListe.matchLista("Spesa"));
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.matchLista("Lavoro");
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.matchLista("");
		});
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.matchLista(null);
		});
	}

	/**
	 * Verifica la rimozione definitiva di una lista dal sistema e la gestione degli errori di ricerca
	 * 
	 * @throws GestioneListeException Se si verificano errori durante l'inserimento iniziale
	 */
	@Test
	void testCancellaLista() throws GestioneListeException {
		GestioneListe.inserisciLista(l1);
		
		assertDoesNotThrow(() -> {
			GestioneListe.cancellaLista("Spesa");
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.matchLista("Spesa");
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaLista("NonEsiste");
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaLista("");
		});
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaLista(null);
		});
	}

	/**
	 * Verifica la registrazione di nuove categorie e il blocco dei duplicati o nomi non validi
	 */
	@Test
	void testInserisciCategoria() {
		assertDoesNotThrow(() -> {
			GestioneListe.inserisciCategoria("Elettronica");
		});
		
		assertTrue(GestioneListe.esisteCategoria("Elettronica"));
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.inserisciCategoria("Elettronica");
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.inserisciCategoria("");
		});
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.inserisciCategoria(null);
		});
	}
	
	/**
	 * Verifica la rimozione delle categorie e l'impossibilità di eliminare la categoria predefinita
	 * 
	 * @throws GestioneListeException Se si verificano errori durante la creazione della categoria di test
	 */
	@Test
	void testCancellaCategoria() throws GestioneListeException {
		GestioneListe.inserisciCategoria("Bricolage");
		
		assertDoesNotThrow(() -> {
			GestioneListe.cancellaCategoria("Bricolage");
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaCategoria("Bricolage");
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaCategoria(GestioneListe.CATEGORIA_DEFAULT);
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaCategoria("");
		});
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaCategoria(null);
		});
	}

	/**
	 * Verifica che l'inserimento di un articolo nel registro globale avvenga senza duplicati
	 */
	@Test
	void testInserisciArticolo() {
		assertDoesNotThrow(() -> {
			GestioneListe.inserisciArticolo(a1);
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.inserisciArticolo(a1);
		});
	}

	/**
	 * Verifica la corretta rimozione di un articolo dal registro globale di sistema
	 * 
	 * @throws GestioneListeException Se si verificano errori durante l'inserimento iniziale
	 */
	@Test
	void testCancellaArticolo() throws GestioneListeException {
		GestioneListe.inserisciArticolo(a1);
		
		assertDoesNotThrow(() -> {
			GestioneListe.cancellaArticolo(a1);
		});
		
		assertThrows(GestioneListeException.class, () -> {
			GestioneListe.cancellaArticolo(a1);
		});
	}
	
	/**
	 * Verifica che il metodo getListeArticoli restituisca correttamente l'elenco delle liste
	 * 
	 * @throws ListaDiArticoliException Viene lanciata se il nome della lista è nullo o vuoto
	 * @throws GestioneListeException Viene lanciata se la lista è nulla o se il nome è già presente
	 */
	@Test
	void testGetListeArticoliECopiaDifensiva() throws GestioneListeException, ListaDiArticoliException {
	    GestioneListe.reset();
	    GestioneListe.inserisciLista(new ListaDiArticoli("Spesa Settimanale"));
	    GestioneListe.inserisciLista(new ListaDiArticoli("Ufficio"));
	    
	    List<ListaDiArticoli> risultato = GestioneListe.getListeArticoli();
	    assertEquals(2, risultato.size());
	    
	    risultato.clear();
	    
	    assertEquals(2, GestioneListe.getListeArticoli().size());
	}
	
	/**
     * Verifica il corretto funzionamento del flag di modifica del sistema, 
     * assicurando che lo stato venga impostato e letto correttamente.
     */
	@Test
	void testGestioneModifica() {
	    GestioneListe.setModificato(false);
	    assertFalse(GestioneListe.getModificato());
	    GestioneListe.setModificato(true);
	    assertTrue(GestioneListe.getModificato());
	}

	/**
     * Verifica che i metodi di accesso ai registri globali restituiscano correttamente l'elenco dei dati.
     * 
     * @throws GestioneListeException In caso di errori nella gestione delle liste.
     * @throws ArticoloException In caso di errori nella creazione degli articoli di test.
     */
	@Test
	void testGetArticoliECategorie() throws GestioneListeException, ArticoloException {
	    GestioneListe.inserisciCategoria("TestCat");
	    assertTrue(GestioneListe.getCategorie().contains("TestCat"));
	    
	    Articolo a = new Articolo("TestArt");
	    GestioneListe.inserisciArticolo(a);
	    assertTrue(GestioneListe.getArticoli().contains(a));
	}
}