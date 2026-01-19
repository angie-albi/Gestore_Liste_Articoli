package modello;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import modello.exception.*;

/**
 * La classe {@code GestioneListe} funge da centro di controllo statico per l'intero sistema
 * <p> Gestisce centralmente:
 * <ul>
 *   <li>L'elenco globale di tutte le liste create ({@code listeArticoli})</li>
 *   <li>L'anagrafica di tutte le categorie merceologiche disponibili</li>
 *   <li>Il registro di tutti gli articoli inseriti nel sistema</li>
 * </ul>
 * 
 * @author Angie Albitres
 */
public class GestioneListe {
	/**
	 * Elenco di tutte le liste di articoli gestite dal sistema
	 */
	private static List<ListaDiArticoli> listeArticoli; 
	/**
	 * Elenco delle categorie merceologiche definite dall'utente
	 */
	private static List<String> categorie; 
	/**
	 * Registro globale di tutti gli articoli esistenti nel sistema
	 */
	private static List<Articolo> articoli; 
	
	/**
	 * Nome della categoria predefinita assegnata agli articoli non categorizzati
	 */
	public static final String CATEGORIA_DEFAULT = "Non categorizzato";
	
	/**
	 * Flag modifica, è true se si è eseguito una modfica ai dati del sistema, false altrimenti 
	 */
	private static boolean modificato = false; 

	/**
	 * Getter del flag modifica
	 */
    public static boolean getModificato() { 
    	return modificato; 
    }
    
    /**
     * Setter del flag modifica
     * 
     * @param stato Stato della modifica, true o false
     */
    public static void setModificato(boolean stato) {
    	modificato = stato; 
    }
	
	/**
	 * Costruttore privato per impedire l'istanziazione della classe
	 */
	public GestioneListe() {}
	
	static {
		reset();	
	}
	/**
	 * Ripristina lo stato iniziale del sistema creando nuove liste vuote
	 * La categoria di default viene aggiunta automaticamente
	 */
	public static void reset() {
		listeArticoli = new ArrayList<ListaDiArticoli>();
		categorie = new ArrayList<String>();
		articoli = new ArrayList<Articolo>();
		
		categorie.add(CATEGORIA_DEFAULT);
	}

	/**
	 * Aggiunge una nuova lista al sistema verificando che non ne esista già una con lo stesso nome
	 * 
	 * @param list L'oggetto {@code ListaDiArticoli} da inserire
	 * 
	 * @throws GestioneListeException Viene lanciata se la lista è nulla o se il nome è già presente
	 */
	public static void inserisciLista(ListaDiArticoli list) throws GestioneListeException{
		if (list == null)
			throw new GestioneListeException("La lista non può essere nulla");
			
		if (trovaListaPerNome(list.getNome()) != null) 
            throw new GestioneListeException("Lista già presente");
		
		listeArticoli.add(list);
	}	
		
	/**
	 * Rimuove definitivamente una lista dal sistema cercandola per nome
	 * 
	 * @param nome Il nome della lista da eliminare
	 * 
	 * @throws GestioneListeException Viene lanciata se il nome è vuoto o se la lista non viene trovata
	 */
	public static void cancellaLista(String nome) throws GestioneListeException {
		if(nome== null ||nome.isBlank()) 
			throw new GestioneListeException("Il nome della lista non può essere vuoto");
		
		ListaDiArticoli listCanc = trovaListaPerNome(nome);
		
		if (listCanc == null)
			throw new GestioneListeException("Lista non trovata");
		
		listeArticoli.remove(listCanc);
	}
	
	/**
	 * Ricerca e restituisce una lista specifica tramite il suo nome
	 * 
	 * @param nome Il nome della lista da cercare
	 * 
	 * @return L'oggetto {@code ListaDiArticoli} corrispondente
	 * 
	 * @throws GestioneListeException Viene lanciata se il nome è vuoto o se la lista non esiste
	 */
	public static ListaDiArticoli matchLista(String nome) throws GestioneListeException {
		if(nome== null ||nome.isBlank()) 
			throw new GestioneListeException("Il nome della lista non può essere vuoto");
		
		nome = nome.trim();
		ListaDiArticoli listaTrovata = trovaListaPerNome(nome);
        if (listaTrovata == null)
             throw new GestioneListeException("Lista non trovata");
        
        return listaTrovata;
	}
	/**
	 * Metodo interno per la ricerca di una lista nell'elenco statico
	 * 
	 * @param nome Nome della lista da trovare
	 * 
	 * @return Il riferimento alla lista se trovata, {@code null} altrimenti
	 */
	private static ListaDiArticoli trovaListaPerNome(String nome) {
        if (nome == null) 
        		return null;
        
        for (ListaDiArticoli l : listeArticoli) {
            if (l.getNome().equalsIgnoreCase(nome)) {
                return l;
            }
        }
        return null; 
    }

	/**
	 * Registra una nuova categoria nel sistema
	 * 
	 * @param nome Il nome della categoria da aggiungere
	 * 
	 * @throws GestioneListeException Viene lanciata se il nome è vuoto o se la categoria esiste già
	 */
	public static void inserisciCategoria(String nome) throws GestioneListeException {
		if(nome== null ||nome.isBlank()) 
			throw new GestioneListeException("Il nome della categoria non può essere vuoto");
		
		if(categorie.contains(nome)) 
			throw new GestioneListeException("Categoria già presente");
		
		categorie.add(nome);
	}
	
	/**
	 * Rimuove una categoria dall'anagrafica
	 * Non è permesso rimuovere la categoria di default
	 * 
	 * @param nome Il nome della categoria da eliminare
	 * 
	 * @throws GestioneListeException Viene lanciata se la categoria è quella di default o se non esiste
	 */
	public static void cancellaCategoria(String nome) throws GestioneListeException {
		if(nome == null || nome.isBlank()) 
	        throw new GestioneListeException("Il nome della categoria non può essere vuoto");
	    
	    if(!categorie.contains(nome)) 
	        throw new GestioneListeException("Categoria non trovata");
	    
	    if(nome.equals(CATEGORIA_DEFAULT))
	        throw new GestioneListeException("Non è possibile cancellare la categoria di default");
	    
	    categorie.remove(nome);

	    for (Articolo a : articoli) {
	        if (a.getCategoria().equalsIgnoreCase(nome)) {
	            a.setCategoria(CATEGORIA_DEFAULT);
	        }
	    }
	}
	
	/**
	 * Verifica la presenza di una categoria nell'anagrafica di sistema
	 * 
	 * @param nome Il nome della categoria da controllare
	 * 
	 * @return true se la categoria esiste, false altrimenti
	 */
	public static boolean esisteCategoria(String nome) {
	    return categorie.contains(nome);
	}
	
	/**
	 * Inserisce un nuovo articolo nel registro globale
	 * Se la categoria dell'articolo non esiste, viene creata automaticamente
	 * 
	 * @param a L'oggetto {@code Articolo} da registrare
	 * 
	 * @throws GestioneListeException Viene lanciata se l'articolo è già presente nel registro
	 */
	public static void inserisciArticolo(Articolo a) throws GestioneListeException {
	    if (articoli.contains(a))
	        throw new GestioneListeException("Articolo già esistente");
	    
	    // È qui che il gestore controlla la categoria dell'articolo
	    String cat = a.getCategoria();
	    if (!esisteCategoria(cat)) {
	        inserisciCategoria(cat);
	    }
	    
	    articoli.add(a);
	}
	
	/**
	 * Rimuove un articolo dal registro globale del sistema e da tutte le liste in cui è presente.
	 * 
	 * @param a L'articolo da eliminare dal sistema
	 * 
	 * @throws GestioneListeException Viene lanciata se l'articolo non è presente nel registro globale
	 */
	public static void cancellaArticolo(Articolo a) throws GestioneListeException {
	    if(!articoli.contains(a)) throw new GestioneListeException("Articolo non trovato");
	    
	    articoli.remove(a);
	    
	    for (ListaDiArticoli lista : listeArticoli) {
	        lista.rimuoviCompletamente(a);
	    }
	}
	
	/**
	 * Restituisce una copia dell'elenco degli articoli presenti nel sistema
	 * 
	 * @return Tutti gli articoli
	 */
	public static List<Articolo> getArticoli() {
		return articoli;
	}
	
	/**
	 * Restituisce una copia dell'elenco delle categorie presenti nel sistema
	 * 
	 * @return Tutte le categorie
	 */
	public static List<String> getCategorie() {
		return categorie;
	}

	/**
	 * Restituisce una copia dell'elenco delle liste presenti nel sistema
	 * 
	 * @return Tutte le liste
	 */
	public static List<ListaDiArticoli> getListeArticoli() {
	    return new ArrayList<>(listeArticoli);
	}
	
	// Salvataggio e caricamento da file 
	
	/**
     * Salva l'intero stato del sistema (categorie, registro articoli e tutte le liste) in un file.
     * 
     * @param nomeFile Il nome del file di salvataggio.
     * @throws IOException In caso di errori di scrittura.
     */
    public static void salvaSistema(String nomeFile) throws IOException {
        PrintWriter out = new PrintWriter(new File(nomeFile));

        // salva categorie
        for (String cat : categorie) {
            out.println("CATEGORIA:" + cat);
        }

        // salva articoli
        for (Articolo a : articoli) {
            out.printf("ARTICOLO:%s:%s:%.2f:%s%n", 
                a.getNome(), a.getCategoria(), a.getPrezzo(), a.getNota());
        }

        // salva liste e articoli all'interno
        for (ListaDiArticoli l : listeArticoli) {
            out.println("LISTA:" + l.getNome());
            
            List<Articolo> cancellati = l.getArticoliCancellati();
            for (Articolo a : l) { 
                if (cancellati.contains(a)) {
                    out.println("CANCELLATO:" + a.getNome() + ":" + a.getCategoria());
                } else {
                    out.println("ATTIVO:" + a.getNome() + ":" + a.getCategoria());
                }
            }
        }
        out.close();
        modificato = false;
    }

    /**
     * Carica l'intero sistema da un file, ripristinando liste, articoli e categorie.
     * 
     * @param nomeFile Il file da cui caricare i dati.
     * @throws IOException In caso di file mancante o errori di lettura.
     * @throws Exception Per errori di validazione dei dati caricati.
     */
    public static void caricaSistema(String nomeFile) throws Exception {
        reset(); 
        
        BufferedReader in = new BufferedReader(new FileReader(nomeFile));
        String linea;
        ListaDiArticoli listaCorrente = null;

        while ((linea = in.readLine()) != null) {
            String[] dati = linea.split(":");
            if (dati.length < 2) continue;

            String tag = dati[0];

            switch (tag) {
            	case "CATEGORIA" -> {
            		// controllo
	                if (!esisteCategoria(dati[1])) {
	                    inserisciCategoria(dati[1]);
	                }
	            }
                
	            case "ARTICOLO" -> {
	                double prezzo = Double.parseDouble(dati[3].replace(",", "."));
	                String nota = (dati.length == 5) ? dati[4] : "";
	                Articolo nuovo = new Articolo(dati[1], dati[2], prezzo, nota);
	                
	                // controllo
	                if (!articoli.contains(nuovo)) {
	                    inserisciArticolo(nuovo);
	                }
	            }
                
                case "LISTA" -> {
                    listaCorrente = new ListaDiArticoli(dati[1]);
                    inserisciLista(listaCorrente);
                }
                
                case "ATTIVO" -> {
                    Articolo a = trovaArticoloGlobale(dati[1], dati[2]);
                    if (listaCorrente != null && a != null) listaCorrente.inserisciArticolo(a);
                }
                
                case "CANCELLATO" -> {
                    Articolo a = trovaArticoloGlobale(dati[1], dati[2]);
                    if (listaCorrente != null && a != null) {
                        listaCorrente.inserisciArticolo(a);
                        listaCorrente.cancellaArticolo(a);
                    }
                }
            }
        }
        in.close();
        modificato = false;
    }

    /**
     * Metodo di utilità per trovare un articolo nel registro globale durante il caricamento.
     */
    private static Articolo trovaArticoloGlobale(String nome, String categoria) {
        for (Articolo a : articoli) {
            if (a.getNome().equalsIgnoreCase(nome) && a.getCategoria().equalsIgnoreCase(categoria)) {
                return a;
            }
        }
        return null;
    }
}