package modello.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modello.Articolo;
import modello.exception.ArticoloException;

class ArticoloTest {
	
	private Articolo a1, a2, a3, a4;

	@BeforeEach
	void setUp() throws Exception {
		a1 = new Articolo("Spesa", 10.00 , "Urgente!");
	}
	
	@Test
	void testCostruttore() throws ArticoloException {
		assertEquals("Spesa", a1.getCategoria());
		assertEquals(10.00 , a1.getPrezzo(), 0.001); // 0.001 è la tolleranza d'errore
		assertEquals("Urgente!", a1.getNota());
		
		a2 = new Articolo("Casa", 10);
		assertEquals("Casa", a2.getCategoria());
		assertEquals(10.00 , a2.getPrezzo(), 0.001);
		assertEquals("", a2.getNota());
		
		a3 = new Articolo("Lavoro");
		assertEquals("Lavoro", a3.getCategoria());
		assertEquals(0.00 , a3.getPrezzo(), 0.001);
		assertEquals("", a3.getNota());
		
		a4 = new Articolo();
		assertEquals("Non categorizzato", a4.getCategoria());
		assertEquals(0.00 , a4.getPrezzo(), 0.001);
		assertEquals("", a4.getNota());
	}

	@Test
    public void testCostruttorePrezzoNegativo() {
        assertThrows(ArticoloException.class, () -> {
            new Articolo("Categoria", -10, "Errore");
        });
    }
	
	@Test
    public void testSetters() throws ArticoloException {
        a1.setCategoria("Elettronica");
        assertEquals("Elettronica", a1.getCategoria());
        
        a1.setNota("Urgente");
        assertEquals("Urgente", a1.getNota());
        
        a1.setPrezzo(99.99);
        assertEquals(99.99, a1.getPrezzo(), 0.001);
        
        assertDoesNotThrow(() -> {
            a1.setPrezzo(10.20);
        });
        
        assertThrows(ArticoloException.class, () -> {
            a1.setPrezzo(-1.0);
        });
    }
	
	@Test
    public void testEquals() throws ArticoloException {
        a2 = new Articolo("Spesa", 10.00 , "Urgente!"); 	// stessi dati
        a3 = new Articolo("Casa", 10.00, "Urgente!"); 		// diversa categoria
        a4 = new Articolo("Spesa", 15.00 , "Urgente!");  	// diverso prezzo
        
        assertTrue(a1.equals(a1));
        
        
        assertTrue(a1.equals(a2));
        assertTrue(a2.equals(a1));
        
        assertFalse(a1.equals(a3));
        assertFalse(a1.equals(a4));
        assertFalse(a1.equals("Una stringa"));
    }
	
	@Test
    public void testToString() throws ArticoloException {
        assertEquals("/n Articolo [categoria=Spesa, prezzo=10.0, nota=Urgente!]", a1.toString());
    }
}
