package modello.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modello.Articolo;
import modello.ListaDiArticoli;
import modello.exception.ArticoloException;
import modello.exception.ListaDiArticoliException;

class ListaDiArticoliTest {

	ListaDiArticoli l1, l2, l3;
	
	@BeforeEach
	void setUp() throws ListaDiArticoliException {
		l1 = new ListaDiArticoli("Spesa");
		l2 = new ListaDiArticoli("Regali");
		l3 = new ListaDiArticoli("Lavoro");
	}
	
	@Test
	void testCostruttore() {
		assertEquals("Spesa", l1.getNome());
		assertEquals("Regali", l2.getNome());
		assertEquals("Lavoro", l3.getNome());
		
		assertThrows(ListaDiArticoliException.class, () -> {
			new ListaDiArticoli("");
		});
		
		assertThrows(ListaDiArticoliException.class, () -> {
			new ListaDiArticoli(null);
		});
	}
	
	@Test
	void testInserisciArticoli() throws ArticoloException, ListaDiArticoliException {
		assertTrue(l1.inserisciArticolo(new Articolo("Latte")));
		assertTrue(l1.inserisciArticolo("Pane", "Cibo"));
		assertTrue(l1.inserisciArticolo("Vino", "Bevande", 10));
		assertTrue(l1.inserisciArticolo("Acqua", "Bevande", 1, "Naturale"));
		
		assertEquals(4, l1.numEl());
		
		assertThrows(ListaDiArticoliException.class, () -> {
			l1.inserisciArticolo("Latte");
		});
	}

	@Test
	void testCancellaArticolo() throws ArticoloException, ListaDiArticoliException {
		riempiLista(l1);
		Articolo a = l1.ricercaArticolo("Latte").get(0);
		
		l1.cancellaArticolo(a);
		assertEquals(12.00, l1.calcoloPrezzoTotale(), 0.001);
		
		assertThrows(ListaDiArticoliException.class, () -> {
			l1.cancellaArticolo(a);
		});
		
		assertThrows(ListaDiArticoliException.class, () -> {
			l1.cancellaArticolo(new Articolo("NonEsiste"));
		});
	}
	
	@Test
	void testRecuperaArticolo() throws ArticoloException, ListaDiArticoliException {
		riempiLista(l1);
		Articolo a = l1.ricercaArticolo("Latte").get(0);
		
		l1.cancellaArticolo(a);
		assertEquals(12.00, l1.calcoloPrezzoTotale(), 0.001);
		
		l1.recuperaArticolo(a);
		assertEquals(13.50, l1.calcoloPrezzoTotale(), 0.001);
		
		assertThrows(ListaDiArticoliException.class, () -> {
			l1.recuperaArticolo(a);
		});
		
		assertThrows(ListaDiArticoliException.class, () -> {
			l1.recuperaArticolo(new Articolo("MaiEsistito"));
		});
	}
	
	@Test
	void testRicercaArticolo() throws ArticoloException, ListaDiArticoliException {
		riempiLista(l1);
		
		ArrayList<Articolo> ris = l1.ricercaArticolo("la");
		assertEquals(1, ris.size());
		assertEquals("Latte", ris.get(0).getNome());
		
		ris = l1.ricercaArticolo("PANE");
		assertEquals(1, ris.size());
		
		ris = l1.ricercaArticolo("z");
		assertEquals(0, ris.size());
		
		ris = l1.ricercaArticolo("");
		assertEquals(3, ris.size());
		
		ris = l1.ricercaArticolo(null);
		assertEquals(0, ris.size());
	}
	
	@Test
	void testCalcoloPrezzoTotale() throws ArticoloException, ListaDiArticoliException {
		assertEquals(0.0, l1.calcoloPrezzoTotale(), 0.001);
		
		riempiLista(l1);
		assertEquals(13.50, l1.calcoloPrezzoTotale(), 0.001);
		
		l1.cancellaArticolo(l1.ricercaArticolo("Vino").get(0));
		assertEquals(3.50, l1.calcoloPrezzoTotale(), 0.001);
	}
	
	@Test
	void testSvuotaCancellati() throws ArticoloException, ListaDiArticoliException {
		riempiLista(l1);
		Articolo a = l1.ricercaArticolo("Latte").get(0);
		l1.cancellaArticolo(a);
		
		l1.svuotaCancellati();
		
		assertThrows(ListaDiArticoliException.class, () -> {
			l1.recuperaArticolo(a);
		});
	}
	
	@Test
	void testIteratore() throws ArticoloException, ListaDiArticoliException {
		riempiLista(l1);
		Articolo a = l1.ricercaArticolo("Latte").get(0);
		l1.cancellaArticolo(a);
		
		int count = 0;
		for(Articolo art : l1) {
			count++;
		}
		assertEquals(3, count);
	}

	void riempiLista(ListaDiArticoli l) throws ArticoloException, ListaDiArticoliException{
		l.inserisciArticolo("Latte", "Alimentari", 1.50);
		l.inserisciArticolo("Pane", "Alimentari", 2.00);
		l.inserisciArticolo("Vino", "Bevande", 10.00);
	}
}