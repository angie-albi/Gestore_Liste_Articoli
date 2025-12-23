package modello;

import java.util.ArrayList;
import java.util.List;

import modello.exception.GestioneListeException;

public class GestioneListe {
	
	// VARIABILI
	private static List<ListaDiArticoli> listeArticoli; 	//tutte le liste
	private static List<String> categorie; 				//tutte le categorie
	private static List<Articolo> articoli; 				//tutti gli articoli
	
	static {
		reset();	
	}
	
	// METODI
	
	// - Lista
	// insersci lista
	public static boolean inserisciLista(ListaDiArticoli list) throws GestioneListeException{
		if (list == null)
			throw new GestioneListeException("La lista non può essere nulla");
			
		for (ListaDiArticoli l : listeArticoli) {
			if (l.getNome().equals(list.getNome())) {
				throw new GestioneListeException("Lista già presente");
			}
		}
		
		return listeArticoli.add(list);
	}	
		
	// cancella lista
	public static boolean cancellaLista(String nome) throws GestioneListeException {
		if(nome== null ||nome.trim().isEmpty()) 
			throw new GestioneListeException("Il nome della lista non può essere vuoto");
		
		ListaDiArticoli listCanc =  null;
		
		for (ListaDiArticoli list: listeArticoli) {
			if(list.getNome().equals(nome)) {
				listCanc = list;
				break;
			}
		}
		
		if(listCanc == null) 
			throw new GestioneListeException("La lista non è presente");
		
		return listeArticoli.remove(listCanc);
	}
	
	public static ListaDiArticoli matchLista(String nome) throws GestioneListeException {
		if(nome== null ||nome.trim().isEmpty()) 
			throw new GestioneListeException("Il nome dellalista non può essere vuoto");
		
		for (ListaDiArticoli list: listeArticoli) {
			if(list.getNome().equals(nome)) {
				return list;
			}
		}
		
		throw new GestioneListeException("Lista non trovata");
	}
	
	
	// - Categoria
	// aggiunta categoria
	public static void inserisciCategoria(String nome) throws GestioneListeException {
		if(nome== null ||nome.trim().isEmpty()) 
			throw new GestioneListeException("Il nome della categoria non può essere vuoto");
		
		if(categorie.contains(nome)) 
			throw new GestioneListeException("Categoria già presente");
		
		categorie.add(nome);
	}
	
	public static boolean cancellaCategoria(String nome) throws GestioneListeException {
		if(nome== null ||nome.trim().isEmpty()) 
			throw new GestioneListeException("Il nome della categoria non può essere vuoto");
		
		if(!categorie.contains(nome)) 
			throw new GestioneListeException("Categoria non trovata");
		
		if(nome.equals("Non categorizzato"))
			throw new GestioneListeException("Non è possibile cancellare la categoria di default");
		
		return categorie.remove(nome);
	}
	
	// esiste categoria
	public static boolean esisteCategoria(String nome) {
	    return categorie.contains(nome);
	}
	
	// - Articoli
	// aggiunta di un articolo
	public static boolean inserisciArticolo(Articolo a) throws GestioneListeException {
		if(articoli.contains(a))
			throw new GestioneListeException("Articolo già esistente");
		
		return articoli.add(a);
	}
	
	public static boolean cancellaArticolo(Articolo a) throws GestioneListeException {
		if(!articoli.contains(a))
			throw new GestioneListeException("Articolo non trovato");
		
		return articoli.remove(a);
	}
	
	// - Reset
	public static void reset() {
		listeArticoli = new ArrayList<ListaDiArticoli>();
		categorie = new ArrayList<String>();
		articoli = new ArrayList<Articolo>();
		
		categorie.add("Non categorizzato");
	}
}
