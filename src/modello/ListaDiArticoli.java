package modello;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
	public ListaDiArticoli(String nome) {
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
		return articoli.add(a);
	}
	
	
	// Ricerca Articolo
	
	// Cancella Articolo
	public boolean calcellaArticolo(Articolo a) {
		if()
		return articoliCancellati.add(a) || ;
	}
	
	
}
