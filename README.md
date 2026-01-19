# Gestore Liste di Articoli

Progetto realizzato per il corso di **Programmazione ad Oggetti** (A.A. 2025/2026).

L'applicazione permette la gestione centralizzata di diverse liste della spesa o inventari, mantenendo un registro globale dei prodotti e delle categorie merceologiche condiviso tra tutte le liste.

## 🚀 Funzionalità Principali

* **Gestione Multi-Lista**: Creazione, eliminazione e apertura di liste indipendenti con nomi univoci.
* **Registro Globale Articoli**: Catalogo centralizzato. Le modifiche a un articolo nel registro (prezzo, categoria, nota) si riflettono in tutte le liste.
* **Anagrafica Categorie**: Suddivisione degli articoli in categorie definite dall'utente.
* **Sistema di Cestino (Soft Delete)**: Gli articoli rimossi finiscono in una lista di "cancellati" specifica, da cui possono essere ripristinati o eliminati definitivamente.
* **Ricerca Avanzata**: Ricerca per prefisso eseguita simultaneamente tra articoli attivi e nel cestino.
* **Calcolo Totale**: Calcolo in tempo reale del prezzo totale degli articoli attivi in una lista.
* **Persistenza Dati**: Salvataggio e caricamento automatico dello stato del sistema su file `dati_sistema.txt`.

## 🏗️ Architettura e Pattern

Il software segue i requisiti accademici e i principi della programmazione a oggetti (OOP):

* **Pattern MVC (Model-View-Controller)**: Netta separazione tra la logica del dominio (`modello`), l'interfaccia utente (`vista`) e il coordinamento (`controllo`).
* **Pattern Iterator**: La classe `ListaDiArticoli` implementa `Iterable`, permettendo di scorrere uniformemente articoli attivi e cestino.
* **Gestione Eccezioni**: Utilizzo di eccezioni personalizzate (`ArticoloException`, `GestioneListeException`, `ListaDiArticoliException`) per garantire la robustezza e la validazione dei dati.
* **Validazione Dati**: Controllo dei formati tramite Regex e prevenzione di valori non validi (es. prezzi negativi).

## 🛠️ Tecnologie Utilizzate

* **Linguaggio**: Java 17+
* **Interfaccia Grafica**: Java Swing
* **Interfaccia Testuale**: Standard I/O (CLI) con menu interattivo
* **Testing**: JUnit 5
* **Documentazione**: Javadoc

## 📂 Struttura del Progetto

```text
src/
├── main/                       # Punto di ingresso (Main.java)
├── modello/                    # Logica di business e classi del dominio
│   ├── exception/              # Eccezioni personalizzate
│   └── test/                   # Suite di test JUnit 5
├── gui/                        # Interfacce Utente
│   ├── grafica/                # Vista e Controllo (Swing)
│   └── rigaComando/            # Interfaccia testuale (CLI)
└── jbook/util/                 # Utility per l'input da tastiera
```

## 🖥️ Istruzioni per l'Esecuzione

All'avvio, la classe `Main` permette di scegliere tra due modalità di interazione:

1.  Assicurati di avere il JDK installato.
2.  Compila i sorgenti del progetto.
3.  Esegui la classe `main.Main`.
4.  Scegli l'interfaccia dal menu:
    * `1`: Interfaccia Grafica (GUI)
    * `2`: Interfaccia da Riga di Comando (CLI)

### Note sul Salvataggio
Il sistema verifica la presenza di modifiche non salvate alla chiusura e propone il salvataggio automatico nel file `dati_sistema.txt`.

## 🧪 Test Unitari

Il modello è coperto da test unitari che verificano la logica di business:
* `ArticoloTest`: Validazione prezzi, nomi e uguaglianza.
* `ListaDiArticoliTest`: Inserimento, cestino, ricerca e calcolo totale.
* `GestioneListeTest`: Gestione categorie e registro globale.

## ✍️ Autore

* **Angie Albitres**

---
*Progetto realizzato per l'esame di Programmazione ad Oggetti.*
