<div align="center">
  <h1>🛒 Gestore Liste di Articoli</h1>
  <p>
    Un'applicazione Java completa per la gestione centralizzata di liste della spesa e inventari con doppia interfaccia (GUI e CLI).
    <br />
    <br />
    <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" alt="Java Version">
    <img src="https://img.shields.io/badge/GUI-Swing-red?style=for-the-badge&logo=java" alt="Swing">
    <img src="https://img.shields.io/badge/Architecture-MVC-blueviolet?style=for-the-badge" alt="MVC Pattern">
    <img src="https://img.shields.io/badge/Test-JUnit_5-25A162?style=for-the-badge&logo=junit5" alt="JUnit">
  </p>
</div>

---

## 🧐 Di cosa si tratta?

Questo progetto permette di gestire molteplici liste di articoli (es. "Spesa Casa", "Ufficio") che condividono un **unico registro globale** di prodotti e categorie. Qualsiasi modifica apportata a un articolo nel catalogo centrale (prezzo, categoria o note) viene aggiornata istantaneamente in tutte le liste in cui l'articolo è presente.

Il software è progettato seguendo il pattern architetturale **MVC (Model-View-Controller)**, garantendo una netta separazione tra la logica del dominio e le interfacce utente sviluppate in Swing e per riga di comando.

---

## ✨ Funzionalità Principali

### 📂 Gestione Liste (Modello Globale)
* **Multi-Lista:** Crea, visualizza ed elimina diverse liste indipendenti identificate da nomi univoci.
* **Registro Globale:** Gestione centralizzata di articoli e categorie merceologiche condivise tra tutte le liste.
* **Persistenza:** Salvataggio e caricamento automatico dello stato del sistema su file di testo (`dati_sistema.txt`).

### 👤 Gestione Articoli e Categorie
* **Sistema di Cestino:** Gli articoli rimossi da una lista vengono spostati in un'area "cancellati" specifica per quella lista, permettendone il ripristino o l'eliminazione definitiva.
* **Ricerca Avanzata:** Ricerca per prefisso che opera simultaneamente tra articoli attivi e cestino.
* **Validazione:** Controllo rigoroso sui nomi tramite espressioni regolari e prezzi che devono essere non negativi.
* **Calcolo Totale:** Calcolo in tempo reale del valore economico complessivo degli articoli attivi in ogni lista.

---

## 🏗️ Struttura del Progetto

L'organizzazione dei file segue rigorosamente la suddivisione tra logica (Model), interfaccia (View) e coordinamento (Controller):

```text
.
├── src/                # Codice sorgente Java
│   ├── main/           # Punto di ingresso (Main.java)
│   ├── modello/        # Logica di business e classi del dominio
│   ├── gui/            # Interfacce Utente (Grafica e CLI)
│   └── jbook/util/     # Utility per l'input
├── doc/                # Documentazione Javadoc generata
│   └── index.html      # Punto di accesso alla documentazione web
├── dati_sistema.txt    # File di persistenza dati
└── Relazione.pdf       # Documentazione tecnica del progetto
```

---

## 📖 Documentazione Javadoc

Il progetto è interamente documentato tramite Javadoc. Per consultare la documentazione tecnica completa in formato ipertestuale:

1. Navigare all'interno della cartella `doc/` del progetto.
2. Aprire il file **`index.html`** utilizzando un qualsiasi browser web (es. Chrome, Firefox, Edge).
3. All'interno del portale sarà possibile esplorare la gerarchia delle classi, i metodi e le eccezioni personalizzate utilizzate nello sviluppo.

---

## 🧪 Testing e Qualità

La stabilità del progetto è garantita da una suite di test unitari sviluppata con **JUnit 5**.
I test coprono:

* ✅ **Articolo:** Validazione dei nomi tramite Regex, gestione prezzi negativi e logica di uguaglianza basata su nome e categoria.
* ✅ **Lista:** Gestione corretta del cestino (inserimento/recupero), ripristino articoli e calcolo del totale.
* ✅ **Gestore:** Verifica dell'integrità dei registri globali e della corretta associazione tra articoli e categorie condivise.

---

## 💻 Importazione in Eclipse IDE

Per integrare il progetto nell'ambiente di sviluppo Eclipse, si prega di seguire la procedura rapida tramite File System:

1. **Apertura**: Selezionare il menu **File** e cliccare sulla voce **Open Projects from File System...**.
2. **Selezione della directory**: Nella finestra di dialogo, cliccare sul pulsante **Directory...** e individuare la cartella radice del progetto (quella contenente le cartelle `src` e `doc`).
3. **Conferma**: Verificare che la casella corrispondente al progetto sia selezionata e premere **Finish**. Eclipse configurerà automaticamente l'ambiente e il *Build Path*.

---

## 🚀 Modalità di Esecuzione (da Eclipse)

Una volta completata l'importazione, l'applicazione può essere avviata dal *Package Explorer* seguendo uno di questi percorsi:

* **Main Entry Point**: Cliccare con il tasto destro su `src/main/Main.java` e selezionare **Run As > Java Application**. Questa opzione permette di scegliere tra interfaccia testuale e grafica.
* **Direct GUI**: Cliccare con il tasto destro su `src/gui/GestoreGui.java` e selezionare **Run As > Java Application** per lanciare direttamente l'ambiente grafico.

---

## ⚙️ Installazione e Setup

Segui questi passaggi per configurare il progetto in locale:

1. **Clona la repository:**
   ```bash
   git clone https://github.com/angie-albi/progetto_paradigmi.git
   ```
2. **Entra nella cartella:**
   ```bash
   cd progetto_paradigmi
   ```
3. **Compilazione:**
   ```bash
   mkdir -p bin
   javac -d bin -sourcepath src src/main/Main.java
   ```
4. **Esecuzione:**
   ```bash
   java -cp bin main.Main
   ```

---

### 👤 Autore
Sviluppato da **Angie Albitres**