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
		listeArticoli = new ArrayList<ListaDiArticoli>();
		categorie = new ArrayList<String>();
		articoli = new ArrayList<Articolo>();
		
		categorie.add("Non categorizzato");
	}
	
	// METODI
	
	// - Lista
	// insersci lista
	public static boolean inserisciLista(ListaDiArticoli list) throws GestioneListeException{
		if (listeArticoli.contains(list))
			throw new GestioneListeException("Lista già presente");
		
		return listeArticoli.add(list);
	}	
		
	// cancella lista
	public static void cancellaLista(String nome) throws GestioneListeException {
		if(nome == null || nome.isEmpty()) 
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
		
		listeArticoli.remove(listCanc);
	}
	
	public static ListaDiArticoli matchLista(String nome) throws GestioneListeException {
		if(nome == null || nome.isEmpty()) 
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
	public static boolean inserisciCategoria(String nome) throws GestioneListeException {
		if(nome == null || nome.isEmpty()) 
		throw new GestioneListeException("Il nome della categoria non può essere vuoto");
		
		if(categorie.contains(nome)) 
			throw new GestioneListeException("Categoria già presente");
		
		return categorie.add(nome);
	}
	
	public boolean cancellaCategoria(String nome) throws GestioneListeException {
		if(nome == null || nome.isEmpty()) 
			throw new GestioneListeException("Il nome della categoria non può essere vuoto");
		
		if(!categorie.contains(nome)) 
			throw new GestioneListeException("Categoria non trovata");
		
		return categorie.remove(nome);
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
}
