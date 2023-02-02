package GestioneNegozio;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.*;

public class Main implements Serializable {
    //Creazione Negozio
    static GestioneNegozio managerNegozio = new GestioneNegozio("Asus Rog Inc.");
    //Output console
    static ConsoleOutputManager consoleOut = new ConsoleOutputManager();
    //Input console utente
    static ConsoleInputManager consoleIn = new ConsoleInputManager();
    static int runtime = -1;
    static int switchLoad = -1;
    static int switchModifica = -1;
    //nome file dati stampati
    static String printFile = "negozio.txt";
    //nome file dati serializzati
    static String dataFile = "dati.txt";

    public static void main(String[] args) throws IOException {
        consoleOut.println("Benvenuto nel negozio " + managerNegozio.getNomeNegozio() + " !");
        //Runtime programma in interfaccia CMD
        while (runtime != 0) {
            consoleOut.println("""
                    Cosa vuoi fare?
                    \t1 : Caricare
                    \t2 : Visualizzare
                    \t3 : Modificare
                    \t4 : Salva in file
                    \t5 : Caricare dati da file
                    \t6 : Cancellare file stampato
                    \t7 : Cancellare dati in file
                    \t0 : Chiudere programma""");
            runtime = Integer.parseInt(consoleIn.readLine());
            //Controllo errori
            while (!(0 <= runtime && runtime <= 7)) {
                consoleOut.println("""
                    Errore - Cosa vuoi fare?
                    \t1 : Caricare
                    \t2 : Visualizzare
                    \t3 : Modificare
                    \t4 : Salva in file
                    \t5 : Caricare dati da file
                    \t6 : Cancellare file stampato
                    \t7 : Cancellare dati in file
                    \t0 : Chiudere programma""");
                runtime = Integer.parseInt(consoleIn.readLine());
            }
            switchLoad = -1;
            switch (runtime) {
                //Scelta Carica
                case 1 -> {
                    while (switchLoad != 0) {
                        consoleOut.println("""
                                Cosa vuoi caricare? :
                                \t1 : Reparto
                                \t2 : Impiegato
                                \t3 : Prodotto
                                \t4 : Vendita
                                \t5 : Ordine
                                0 : Torno indietro""");
                        switchLoad = Integer.parseInt(consoleIn.readLine());
                        //Controllo errori
                        while (!(0 <= switchLoad && switchLoad <= 5)) {
                            consoleOut.println("""
                                    Errore - Cosa vuoi caricare? :
                                    \t1 : Reparto
                                    \t2 : Impiegato
                                    \t3 : Prodotto
                                    \t4 : Vendita
                                    \t5 : Ordine
                                    0 : Torno indietro""");
                            switchLoad = Integer.parseInt(consoleIn.readLine());
                        }
                        switch (switchLoad) {
                            //Carico Reparto
                            case 1 -> managerNegozio.loadReparto_UserInput(consoleOut, consoleIn);
                            //Carico Impiegato
                            case 2 -> managerNegozio.loadImpiegato_UserInput(consoleOut, consoleIn);
                            //Carico Prodotto
                            case 3 -> managerNegozio.loadProdotto_UserInput(consoleOut, consoleIn);
                            //Carico Vendita
                            case 4 -> managerNegozio.loadVendita_UserInput(consoleOut, consoleIn);
                            //Carica Ordine
                            case 5 -> managerNegozio.loadOrdine_UserInput(consoleOut, consoleIn);
                            //Torno indietro
                            case 0 -> {
                            }
                        }
                    }
                }
                //Scelta visualizzazione
                case 2 -> {
                    //loop visualizzazione
                    while (switchLoad != 0) {
                        consoleOut.println("""
                                Cosa vuoi visualizzare? :
                                \t1 : Reparto
                                \t2 : Impiegato
                                \t3 : Prodotto
                                \t4 : Vendita
                                \t5 : Ordine
                                \t6 : Negozio
                                \t7 : Nome Negozio
                                0 : Torno indietro""");
                        switchLoad = Integer.parseInt(consoleIn.readLine());
                        //Controllo errori
                        while (!(0 <= switchLoad && switchLoad <= 7)) {
                            consoleOut.println("""
                                    Errore - Cosa vuoi visualizzare? :
                                    \t1 : Reparto
                                    \t2 : Impiegato
                                    \t3 : Prodotto
                                    \t4 : Vendita
                                    \t5 : Ordine
                                    \t6 : Negozio
                                    \t7 : Nome Negozio
                                    0 : Torno indietro""");
                            switchLoad = Integer.parseInt(consoleIn.readLine());
                        }
                        //Scelta visualizzazione
                        switch (switchLoad) {
                            //Visualizzo Reparti
                            case 1 -> managerNegozio.printReparto_userOutput(consoleOut);
                            //Visualizzo Impiegati
                            case 2 -> managerNegozio.printImpiegato_userOutput(consoleOut);
                            //Visualizzo Prodotti
                            case 3 -> managerNegozio.printProdotto_userOutput(consoleOut);
                            //Visualizzo Vendite
                            case 4 -> managerNegozio.printVendite_userOutput(consoleOut);
                            //Visualizzo Ordini
                            case 5 -> managerNegozio.printOrdini_userOutput(consoleOut);
                            //Visualizzo negozio
                            case 6 -> managerNegozio.printUserOutput(consoleOut);
                            //Visualizzo nome negozio
                            case 7 -> consoleOut.println(managerNegozio.getNomeNegozio());
                            //Torno indietro
                            case 0 -> {
                            }
                        }
                    }
                }
                //Scelta modifica
                case 3 -> {
                    while (switchLoad != 0) {
                        consoleOut.println("""
                                Cosa vuoi modificare? :
                                \t1 : Reparto
                                \t2 : Impiegato
                                \t3 : Prodotto
                                \t4 : Vendita
                                \t5 : Ordine
                                \t6 : Nome Negozio
                                \t7 : Ordinamento Impiegati per Cognome
                                0 : Torno indietro""");
                        switchLoad = Integer.parseInt(consoleIn.readLine());
                        //Controllo errori
                        while (!(0 <= switchLoad && switchLoad <= 7)) {
                            consoleOut.println("""
                                    Errore - Cosa vuoi modificare? :
                                    \t1 : Reparto
                                    \t2 : Impiegato
                                    \t3 : Prodotto
                                    \t4 : Vendita
                                    \t5 : Ordine
                                    \t6 : Nome Negozio
                                    \t7 : Ordinamento Impiegati per Cognome
                                    0 : Torno indietro""");
                            switchLoad = Integer.parseInt(consoleIn.readLine());
                        }
                        //scelta modifica
                        switch (switchLoad) {
                            //modifica reparto
                            case 1 -> {
                                managerNegozio.printListaReparti_userOutput(consoleOut);
                                consoleOut.println("Inserisci Denominazione reparto :");
                                int index = managerNegozio.trovaRepartoByNome(consoleIn.readLine());
                                //Controllo errori
                                if (index == -1) {
                                    consoleOut.println("errore");
                                    break;
                                }
                                managerNegozio.printRepartoByIndex_userOutput(consoleOut, index);
                                consoleOut.println("""
                                        Cosa vuoi modificare? :
                                        \t1 : Denominativo
                                        \t2 : Nome Responsabile
                                        \t3 : Impiegati
                                        \t4 : Prodotti
                                        \t5 : Elimina
                                        \t6 : Aggiungi Impiegato
                                        0 : Torno indietro""");
                                switchModifica = Integer.parseInt(consoleIn.readLine());
                                //Controllo errore
                                while (!(0 <= switchModifica && switchModifica <= 6)) {
                                    consoleOut.println("""
                                            Errore - Cosa vuoi modificare? :
                                            \t1 : Denominativo
                                            \t2 : Nome Responsabile
                                            \t3 : Impiegati
                                            \t4 : Prodotti
                                            \t5 : Elimina
                                            \t6 : Aggiungi Impiegato
                                            0 : Torno indietro""");
                                    switchModifica = Integer.parseInt(consoleIn.readLine());
                                }
                                //Switch controllo modifica impiegato
                                switch (switchModifica) {
                                    //modifica denominativo
                                    case 1 -> {
                                        consoleOut.println("Stai Modificando il denominativo : " + managerNegozio.getListaReparti().get(index).getDenominazione());
                                        managerNegozio.getListaReparti().get(index).setDenominazione(consoleIn.readLine());
                                        consoleOut.println("Modificato!");
                                    }
                                    //modifica responsabile reparto
                                    case 2 -> {
                                        consoleOut.println("Stai Modificando il Responsabile del reparto : " + managerNegozio.getListaReparti().get(index).getResponsabileReparto());
                                        managerNegozio.getListaReparti().get(index).setResponsabileReparto(consoleIn.readLine());
                                        consoleOut.println("Modificato!");
                                    }
                                    //modifica lista impiegati del reparto
                                    case 3 -> {
                                        consoleOut.println("Lista Impiegati del reparto " + managerNegozio.getListaReparti().get(index).getDenominazione());
                                        managerNegozio.getListaReparti().get(index).printListaImpiegati_userOutput(consoleOut);
                                        consoleOut.println("Inserisci nome impiegato da modificare");
                                        int indexImpiegato = managerNegozio.getListaReparti().get(index).trovaImpiegatoByNome(consoleIn.readLine());
                                        if (indexImpiegato == -1) {
                                            consoleOut.println("errore");
                                            break;
                                        }
                                        consoleOut.println("""
                                                Cosa vuoi modificare? :
                                                \t1 : Nome
                                                \t2 : Cognome
                                                \t3 : Codice Fiscale
                                                \t4 : Elimina
                                                0 : Torna indietro""");
                                        switchModifica = Integer.parseInt(consoleIn.readLine());
                                        //Controllo errori
                                        while (!(0 <= switchModifica && switchModifica <= 4)) {
                                            consoleOut.println("""
                                                    Errore - Cosa vuoi modificare? :
                                                    \t1 : Nome
                                                    \t2 : Cognome
                                                    \t3 : Codice Fiscale
                                                    \t4 : Elimina
                                                    0 : Torna indietro""");
                                            switchModifica = Integer.parseInt(consoleIn.readLine());
                                        }
                                        //Switch controllo modifica impiegato del reparto
                                        switch (switchModifica) {
                                            //Modifico il nome
                                            case 1 -> {
                                                consoleOut.println("Stai modificando il nome : " + managerNegozio.getListaReparti().get(index).getListaImpiegati().get(indexImpiegato).getNome());
                                                String nomeModificato = consoleIn.readLine();
                                                if (!nomeModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaImpiegati().get(indexImpiegato).setNome(nomeModificato);
                                            }
                                            //Modifico il cognome
                                            case 2 -> {
                                                consoleOut.println("Stai modificando il cognome : " + managerNegozio.getListaReparti().get(index).getListaImpiegati().get(indexImpiegato).getCognome());
                                                String cognomeModificato = consoleIn.readLine();
                                                if (!cognomeModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaImpiegati().get(indexImpiegato).setCognome(cognomeModificato);
                                            }
                                            //Modifico codice fiscale
                                            case 3 -> {
                                                consoleOut.println("Stai modificando il codice fiscale : " + managerNegozio.getListaReparti().get(index).getListaImpiegati().get(indexImpiegato).getCF());
                                                String codiceFiscaleModificato = consoleIn.readLine();
                                                if (!codiceFiscaleModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaImpiegati().get(indexImpiegato).setCF(codiceFiscaleModificato);
                                            }
                                            //Elimino impiegato dal reparto
                                            case 4 ->
                                                    managerNegozio.getListaReparti().get(index).getListaImpiegati().remove(indexImpiegato);
                                            case 0 -> {
                                            }
                                        }
                                    }
                                    //modifica lista prodotti del reparto
                                    case 4 -> {
                                        consoleOut.println("Lista Prodotti del reparto " + managerNegozio.getListaReparti().get(index).getDenominazione());
                                        managerNegozio.getListaReparti().get(index).printListaProdotti_userOutput(consoleOut);
                                        consoleOut.println("Inserisci codice prodotto da modificare");
                                        int indexProdotto = managerNegozio.getListaReparti().get(index).trovaProdottoByCodice(consoleIn.readLine());
                                        if (indexProdotto == -1) {
                                            consoleOut.println("errore");
                                            break;
                                        }
                                        consoleOut.println("""
                                                Cosa vuoi modificare? :
                                                \t1 : Codice
                                                \t2 : Denominazione
                                                \t3 : Produttore
                                                \t4 : Prezzo
                                                0 : Torna indietro""");
                                        switchModifica = Integer.parseInt(consoleIn.readLine());
                                        //Switch controllo modifica prodotti del reparto
                                        switch (switchModifica) {
                                            //Modifico codice prodotto del reparto
                                            case 1 -> {
                                                consoleOut.println("Stai modificando il codice : " + managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).getCodice());
                                                String codiceModificato = consoleIn.readLine();
                                                if (!codiceModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).setCodice(codiceModificato);
                                            }
                                            //modifico denominativo prodotto del reparto
                                            case 2 -> {
                                                consoleOut.println("Stai modificando il denominativo : " + managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).getDenominazione());
                                                String denominativoModificato = consoleIn.readLine();
                                                if (!denominativoModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).setDenominazione(denominativoModificato);
                                            }
                                            //Modifico produttore prodotto del reparto
                                            case 3 -> {
                                                consoleOut.println("Stai modificando il produttore : " + managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).getProduttore());
                                                String produttoreModificato = consoleIn.readLine();
                                                if (!produttoreModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).setProduttore(produttoreModificato);
                                            }
                                            //Modifico prezzo prodotto del reparto
                                            case 4 -> {
                                                consoleOut.println("Stai modificando il prezzo : " + managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).getPrezzo());
                                                String prezzoModificato = consoleIn.readLine();
                                                if (!prezzoModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).setPrezzo(prezzoModificato);
                                            }
                                            case 0 -> {
                                            }
                                        }
                                    }
                                    //Elimina Reparto
                                    case 5 -> managerNegozio.eliminaReparto(index);
                                    //Aggiungi impiegato al reparto
                                    case 6 -> {
                                        consoleOut.println("Chi vuoi aggiungere?");
                                        managerNegozio.printListaImpiegati_userOutput(consoleOut);
                                        int indexImpiegato = managerNegozio.trovaImpiegatoByNome(consoleIn.readLine());
                                        if (indexImpiegato == -1) {
                                            consoleOut.println("Errore");
                                            break;
                                        }
                                        managerNegozio.getListaReparti().get(index).getListaImpiegati().add(managerNegozio.getListaImpiegati().get(indexImpiegato));
                                        managerNegozio.getListaImpiegati().get(indexImpiegato).getListaReparti().add(managerNegozio.getListaReparti().get(index));
                                    }
                                    //Torno indietro
                                    case 0 -> {
                                    }
                                }
                            }
                            //modifica impiegati negozio
                            case 2 -> {
                                consoleOut.println("Lista Impiegati del negozio " + managerNegozio.getNomeNegozio());
                                managerNegozio.printListaImpiegati_userOutput(consoleOut);
                                consoleOut.println("Inserisci nome impiegato da modificare");
                                int indexImpiegato = managerNegozio.trovaImpiegatoByNome(consoleIn.readLine());
                                //Controllo errori
                                if (indexImpiegato == -1) {
                                    consoleOut.println("errore");
                                    break;
                                }
                                managerNegozio.printImpiegato_userOutput(consoleOut, indexImpiegato);
                                consoleOut.println("""
                                        Cosa vuoi modificare? :
                                        \t1 : Nome
                                        \t2 : Cognome
                                        \t3 : Codice Fiscale
                                        \t4 : Elimina
                                        \t5 : Reparto Associato
                                        0 : Torna indietro""");
                                switchModifica = Integer.parseInt(consoleIn.readLine());
                                //Controllo errore
                                while (!(0 <= switchModifica && switchModifica <= 5)) {
                                    consoleOut.println("""
                                            Errore - Cosa vuoi modificare? :
                                            \t1 : Nome
                                            \t2 : Cognome
                                            \t3 : Codice Fiscale
                                            \t4 : Elimina
                                            \t5 : Reparto Associato
                                            0 : Torna indietro""");
                                    switchModifica = Integer.parseInt(consoleIn.readLine());
                                }
                                //Switch controllo modifica impiegato
                                switch (switchModifica) {
                                    //modifica nome
                                    case 1 -> {
                                        consoleOut.println("Stai modificando il nome : " + managerNegozio.getListaImpiegati().get(indexImpiegato).getNome());
                                        String nomeModificato = consoleIn.readLine();
                                        if (!nomeModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaImpiegati().get(indexImpiegato).setNome(nomeModificato);
                                    }
                                    //modifica cognome
                                    case 2 -> {
                                        consoleOut.println("Stai modificando il cognome : " + managerNegozio.getListaImpiegati().get(indexImpiegato).getCognome());
                                        String cognomeModificato = consoleIn.readLine();
                                        if (!cognomeModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaImpiegati().get(indexImpiegato).setCognome(cognomeModificato);
                                    }
                                    //modifica codice fiscale
                                    case 3 -> {
                                        consoleOut.println("Stai modificando il codice fiscale : " + managerNegozio.getListaImpiegati().get(indexImpiegato).getCF());
                                        String codiceFiscaleModificato = consoleIn.readLine();
                                        if (!codiceFiscaleModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaImpiegati().get(indexImpiegato).setCF(codiceFiscaleModificato);
                                    }
                                    //elimina impiegato
                                    case 4 -> managerNegozio.eliminaImpiegato(indexImpiegato);
                                    //Modifico reparto associato
                                    case 5 -> {
                                        managerNegozio.printListaRepartiByImpiegato_userOutput(consoleOut, indexImpiegato);
                                        consoleOut.println("""
                                                Cosa vuoi fare? :
                                                \t1 : Aggiungere Reparto
                                                \t2 : Eliminare Reparto
                                                0 : Torna indietro""");
                                        switchModifica = Integer.parseInt(consoleIn.readLine());
                                        //Controllo errore
                                        while (!(0 <= switchModifica && switchModifica <= 2)) {
                                            consoleOut.println("""
                                                    Errore - Cosa vuoi modificare? :
                                                    \t1 : Aggiungere Reparto
                                                    \t2 : Eliminare Reparto
                                                    0 : Torna indietro""");
                                            switchModifica = Integer.parseInt(consoleIn.readLine());
                                        }
                                        switch (switchModifica) {
                                            case 1 -> {
                                                consoleOut.println("In che reparto opera " + managerNegozio.getListaImpiegati().get(indexImpiegato).getNome());
                                                managerNegozio.printListaReparti_userOutput(consoleOut);
                                                int indexReparto = managerNegozio.trovaRepartoByNome(consoleIn.readLine());
                                                if (indexReparto == -1) {
                                                    consoleOut.println("errore");
                                                    break;
                                                }
                                                managerNegozio.getListaImpiegati().get(indexImpiegato).getListaReparti().add(managerNegozio.getListaReparti().get(indexReparto));
                                                managerNegozio.getListaReparti().get(indexReparto).getListaImpiegati().add(managerNegozio.getListaImpiegati().get(indexImpiegato));
                                            }
                                            case 2 -> {
                                                consoleOut.println("Che reparto vuoi eliminare? ");
                                                managerNegozio.printListaReparti_userOutput(consoleOut);
                                                int indexReparto = managerNegozio.trovaRepartoByNome(consoleIn.readLine());
                                                if (indexReparto == -1) {
                                                    consoleOut.println("errore");
                                                    break;
                                                }
                                                managerNegozio.getListaImpiegati().get(indexImpiegato).getListaReparti().remove(managerNegozio.getListaReparti().get(indexReparto));
                                                managerNegozio.getListaReparti().get(indexReparto).getListaImpiegati().remove(managerNegozio.getListaImpiegati().get(indexImpiegato));
                                            }
                                            case 0 -> {
                                            }
                                        }

                                    }
                                    //Torno indietro
                                    case 0 -> {
                                    }
                                }
                            }
                            //modifica prodotto negozio
                            case 3 -> {
                                consoleOut.println("Lista Prodotti del negozio " + managerNegozio.getNomeNegozio());
                                managerNegozio.printListaProdotti_userOutput(consoleOut);
                                consoleOut.println("Inserisci codice prodotto da modificare");
                                int indexProdotto = managerNegozio.trovaProdottoByCodice(consoleIn.readLine());
                                //controllo errori
                                if (indexProdotto == -1) {
                                    consoleOut.println("errore");
                                    break;
                                }
                                consoleOut.println("Cosa vuoi modificare di : " + managerNegozio.getListaProdotti().get(indexProdotto).getCodice() +
                                        "\n\t1 : Codice" +
                                        "\n\t2 : Denominazione" +
                                        "\n\t3 : Produttore" +
                                        "\n\t4 : Prezzo" +
                                        "\n\t5 : Elimina" +
                                        "\n0 : Torna indietro");
                                switchModifica = Integer.parseInt(consoleIn.readLine());
                                while (!(0 <= switchModifica && switchModifica <= 5)) {
                                    consoleOut.println("Errore - Cosa vuoi modificare di : " + managerNegozio.getListaProdotti().get(indexProdotto).getCodice() +
                                            "\n\t1 : Codice" +
                                            "\n\t2 : Denominazione" +
                                            "\n\t3 : Produttore" +
                                            "\n\t4 : Prezzo" +
                                            "\n\t5 : Elimina" +
                                            "\n0 : Torna indietro");
                                    switchModifica = Integer.parseInt(consoleIn.readLine());
                                }
                                //Switch controllo modifica Prodotto
                                switch (switchModifica) {
                                    //Modifica Codice Prodotto
                                    case 1 -> {
                                        consoleOut.println("Stai modificando il codice : " + managerNegozio.getListaProdotti().get(indexProdotto).getCodice());
                                        String codiceModificato = consoleIn.readLine();
                                        if (!codiceModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaProdotti().get(indexProdotto).setCodice(codiceModificato);
                                    }
                                    //Modifica denominativo prodotto
                                    case 2 -> {
                                        consoleOut.println("Stai modificando il denominativo : " + managerNegozio.getListaProdotti().get(indexProdotto).getDenominazione());
                                        String denominativoModificato = consoleIn.readLine();
                                        if (!denominativoModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaProdotti().get(indexProdotto).setDenominazione(denominativoModificato);
                                    }
                                    //Modifica produttore prodotto
                                    case 3 -> {
                                        consoleOut.println("Stai modificando il produttore : " + managerNegozio.getListaProdotti().get(indexProdotto).getProduttore());
                                        String produttoreModificato = consoleIn.readLine();
                                        if (!produttoreModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaProdotti().get(indexProdotto).setProduttore(produttoreModificato);
                                    }
                                    //Modifica prezzo prodotto
                                    case 4 -> {
                                        consoleOut.println("Stai modificando il prezzo : " + managerNegozio.getListaProdotti().get(indexProdotto).getPrezzo());
                                        String prezzoModificato = consoleIn.readLine();
                                        if (!prezzoModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaProdotti().get(indexProdotto).setPrezzo(prezzoModificato);
                                    }
                                    //Elimina prodotto
                                    case 5 -> managerNegozio.eliminaProdotto(indexProdotto);
                                    //Torno indietro
                                    case 0 -> {
                                    }
                                }
                            }
                            //modifica vendita negozio
                            case 4 -> {
                                consoleOut.println("Lista Vendita del negozio " + managerNegozio.getNomeNegozio());
                                managerNegozio.printVendite_userOutput(consoleOut);
                                consoleOut.println("Inserisci codice prodotto da modificare");
                                int indexVendita = managerNegozio.trovaVenditaByProdotto(consoleIn.readLine());
                                //Controllo errori
                                if (indexVendita == -1) {
                                    consoleOut.println("errore");
                                    break;
                                }
                                consoleOut.println("Cosa vuoi modificare di : " + managerNegozio.getListaVendite().get(indexVendita).getProdottoVenduto() +
                                        "\n\t1 : Data Vendita" +
                                        "\n\t2 : Quantità" +
                                        "\n\t3 : Elimina" +
                                        "\n0 : Torna indietro");
                                switchModifica = Integer.parseInt(consoleIn.readLine());
                                //Controllo Errore
                                while (!(0 <= switchModifica && switchModifica <= 3)) {
                                    consoleOut.println("Errore - Cosa vuoi modificare di : " + managerNegozio.getListaVendite().get(indexVendita).getProdottoVenduto() +
                                            "\n\t1 : Data Vendita" +
                                            "\n\t2 : Quantità" +
                                            "\n\t3 : Elimina" +
                                            "\n0 : Torna indietro");
                                    switchModifica = Integer.parseInt(consoleIn.readLine());
                                }
                                //Switch controllo modifica vendita
                                switch (switchModifica) {
                                    //Modifica data vendita
                                    case 1 -> {
                                        consoleOut.println("Stai modificando la Data della vendita in formato YYYY-MM-DD : " + managerNegozio.getListaVendite().get(indexVendita).getDataVendita());
                                        String dataModificato = consoleIn.readLine();
                                        if (!dataModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaVendite().get(indexVendita).setDataVendita(dataModificato);
                                    }
                                    //Modifica quantita vendita
                                    case 2 -> {
                                        consoleOut.println("Stai modificando la quantità venduta di : " + managerNegozio.getListaVendite().get(indexVendita).getQnt());
                                        String qntModificato = consoleIn.readLine();
                                        if (!qntModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaVendite().get(indexVendita).setQnt(qntModificato);
                                    }
                                    //Elimina vendita
                                    case 3 -> managerNegozio.eliminaVendita(indexVendita);
                                    case 0 -> {
                                    }
                                }
                            }
                            //modifica ordini negozio
                            case 5 -> {
                                consoleOut.println("Lista Ordini del negozio " + managerNegozio.getNomeNegozio());
                                managerNegozio.printOrdini_userOutput(consoleOut);
                                consoleOut.println("Inserisci codice prodotto da modificare");
                                int indexOrdine = managerNegozio.trovaOrdineByProdotto(consoleIn.readLine());
                                //Controllo errore
                                if (indexOrdine == -1) {
                                    consoleOut.println("errore");
                                    break;
                                }
                                consoleOut.println("Cosa vuoi modificare di : " + managerNegozio.getListaOrdini().get(indexOrdine).getProdottoVenduto() +
                                        "\n\t1 : Data Vendita" +
                                        "\n\t2 : Quantità" +
                                        "\n0 : Torna indietro");
                                switchModifica = Integer.parseInt(consoleIn.readLine());
                                //Switch controllo modifica ordine
                                switch (switchModifica) {
                                    //Modifica data ordine
                                    case 1 -> {
                                        consoleOut.println("Stai modificando la Data dell'ordine in formato YYYY-MM-DD : " + managerNegozio.getListaOrdini().get(indexOrdine).getDataOrdine());
                                        String dataModificato = consoleIn.readLine();
                                        if (!dataModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaOrdini().get(indexOrdine).setDataOrdine(dataModificato);
                                    }
                                    //Modifica quantita ordine
                                    case 2 -> {
                                        consoleOut.println("Stai modificando la quantità dell'ordine di : " + managerNegozio.getListaVendite().get(indexOrdine).getQnt());
                                        String qntModificato = consoleIn.readLine();
                                        if (!qntModificato.equalsIgnoreCase("null"))
                                            managerNegozio.getListaOrdini().get(indexOrdine).setQnt(qntModificato);
                                    }
                                    //Elimina ordine
                                    case 3 -> managerNegozio.eliminaOrdine(indexOrdine);
                                    //Torno indietro
                                    case 0 -> {
                                    }
                                }
                            }
                            //Modifica nome negozio
                            case 6 -> {
                                consoleOut.println("Modifica nome Negozio : " + managerNegozio.getNomeNegozio());
                                managerNegozio.setNomeNegozio(consoleIn.readLine());
                            }
                            //Ordinare impiegati per cognome
                            case 7 -> managerNegozio.ordinaImpiegatiByCognome(managerNegozio.getListaImpiegati());
                            //Torno indietro
                            case 0 -> {
                            }
                        }
                    }
                }
                //Salvo nel file
                case 4 -> {
                    FileOutputStream fileOutput = new FileOutputStream(dataFile);
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                    managerNegozio.saveOnFile(objectOutput);
                    File file = new File(printFile);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();

                    FileWriter fileWriter = new FileWriter(printFile);
                    managerNegozio.printOnFile(fileWriter);

                    objectOutput.close();
                    fileOutput.close();
                    fileWriter.close();
                }
                //Leggo da file
                case 5 -> {
                    try {
                        FileInputStream fileInput = new FileInputStream(dataFile);
                        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                        managerNegozio.loadFromFile(objectInput);
                        fileInput.close();
                        objectInput.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                //Cancello print file
                case 6 -> {
                    File file = new File(printFile);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                //Cancello data file
                case 7 -> {
                    File file = new File(dataFile);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                //Chiudo Programma
                case 0 -> {
                    consoleIn.close();
                    consoleOut.close();
                }
            }
        }
    }
}