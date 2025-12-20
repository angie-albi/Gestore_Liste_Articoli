package modello;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import modello.exception.ArticoloException;
import modello.exception.ListaDiArticoliException;

public class ListaDiArticoli implements Iterable<Articolo>{
	
	// VARIABILI
	private String nome;
	private ArrayList<Articolo> articoli;
	private ArrayList<Articolo> articoliCancellati;
	
	// ITERATOR
	@Override
	public Iterator<Articolo> iterator() {
		return new IteratoreArticoli();
	}
	
	private class IteratoreArticoli implements Iterator<Articolo>{
		private Iterator<Articolo> it1 = articoli.iterator();
        private Iterator<Articolo> it2 = articoliCancellati.iterator();
        
		@Override
		public boolean hasNext() {
			return it1.hasNext() || it2.hasNext();
		}
		
		@Override
		public Articolo next() {
			if(it1.hasNext())
				return it1.next();
			
			if(it2.hasNext())
				return it2.next();
			
			throw new NoSuchElementException();
		}	
	}
	
	// COSTRUTTORE
	public ListaDiArticoli(String nome) throws ListaDiArticoliException {
		if (nome == null || nome.trim().isEmpty()) {
            throw new ListaDiArticoliException("Il nome della lista non può essere vuoto");
        }
		
		this.nome = nome;
		this.articoli = new ArrayList<Articolo>();
		this.articoliCancellati = new ArrayList<Articolo>();
	}
	
	// METODI
	// Getter di Nome
	public String getNome() {
		return nome;
	}
	
	// Inserisci Articolo
	public boolean inserisciArticolo(Articolo a) {
		if(articoli.contains(a))
			return false;
		return articoli.add(a);
	}
	
	public boolean inserisciArticolo(String nome) throws ArticoloException {
		return inserisciArticolo(new Articolo(nome));
	}
	
	public boolean inserisciArticolo(String nome, String categoria) throws ArticoloException {
		return inserisciArticolo(new Articolo(nome, categoria));
	}
	
	public boolean inserisciArticolo(String nome, String categoria, int prezzo) throws ArticoloException {
		return inserisciArticolo(new Articolo(nome, categoria, prezzo));
	}
	
	public boolean inserisciArticolo(String nome, String categoria, int prezzo, String nota) throws ArticoloException {
		return inserisciArticolo(new Articolo(nome, categoria, prezzo, nota));
	}
	
	// Ricerca Articolo: sia nella lista che nei cancellati
	public ArrayList<Articolo> ricercaArticolo(String prefisso) {
		ArrayList<Articolo> ris = new ArrayList<Articolo>();
		
		for(Articolo a: this) 
			if(a.getNome().startsWith(prefisso))
				ris.add(a);
		return ris;
	}
}
