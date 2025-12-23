package modello;

import modello.exception.ArticoloException;
import modello.exception.GestioneListeException;

public class Articolo{

	// VARIABILI
	private String nome;
	private String categoria;
	private double prezzo;
	private String nota;
	
	
	// COSTRUTTORI
	public Articolo(String nome, String categoria, double prezzo, String nota)throws ArticoloException, GestioneListeException {
	    this.setNome(nome);
		this.setCategoria(categoria);
		this.setPrezzo(prezzo);
		this.setNota(nota);
	}
	
	public Articolo(String nome, String categoria, double prezzo) throws ArticoloException, GestioneListeException {
		this(nome, categoria, prezzo, "");
	}
	
	public Articolo(String nome, String categoria) throws ArticoloException, GestioneListeException {
		this(nome, categoria, 0, "");
	}
	
	public Articolo(String nome) throws ArticoloException, GestioneListeException {
		this(nome, "Non categorizzato", 0, "");
	}
	

	// GETTERS e SETTERS
	// Nome
	public void setNome(String nome) throws ArticoloException {
		if (nome == null || nome.trim().isEmpty()) {
	        throw new ArticoloException("Il nome dell'articolo non può essere vuoto");
	    }
		this.nome=nome;
	}
	
	public String getNome() {
		return nome;
	}
	// Categoria
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) throws GestioneListeException {
		if(categoria==null || categoria.isEmpty()) {
			categoria = "Non categorizzato";
		}
		
		// se non esiste la categoria la inserisco
		if(!GestioneListe.esisteCategoria(categoria)){
			GestioneListe.inserisciCategoria(categoria);
		}
		
		this.categoria = categoria;
	}

	// Prezzo
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) throws ArticoloException {
		this.prezzo = this.validaPrezzo(prezzo);
	}

	// Nota
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		if(nota==null || nota.isEmpty()) {
			nota = "";
		}
		
		this.nota = nota;
	}
	
	// Valida prezzo
	private double validaPrezzo(double prezzo) throws ArticoloException {
		if (prezzo < 0) {
			throw new ArticoloException("Il prezzo non può essere negativo");
        }
        return prezzo;
    }

	// equals
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) 
	    	return true;
	    
	    if (obj == null || getClass() != obj.getClass()) 
	    	return false;
	    
	    Articolo articolo = (Articolo) obj;
	    
	    return nome.equals(articolo.getNome()) && Double.compare(prezzo, articolo.prezzo) == 0 && categoria.equals(articolo.categoria) && nota.equals(articolo.nota);
	}
	
	// toString
	@Override
	public String toString() {
		return "\n Articolo [nome=" + nome +", categoria=" + categoria + ", prezzo=" + prezzo + ", nota=" + nota + "]";
	}
}
