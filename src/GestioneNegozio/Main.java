package GestioneNegozio;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.*;
import java.time.LocalDate;
import java.util.Formatter;

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
    static String filename = "negozio.txt";
    //nome file dati serializzati
    static String serializedFilename = "dati.txt";
    //provo a caricare da file
    static Formatter formatData = new Formatter();

    public static void main(String[] args) throws IOException {
        formatData.format("YYYY-DD-MM");
        consoleOut.println("Benvenuto nel negozio " + managerNegozio.getNomeNegozio() + " !");
        //Runtime programma in interfaccia CMD
        while (runtime != 0) {
            consoleOut.println("Cosa vuoi fare?" +
                    "\n\t1 : Caricare" +
                    "\n\t2 : Visualizzare" +
                    "\n\t3 : Modificare" +
                    "\n\t4 : Salvare file" +
                    "\n\t5 : Caricare dati da file" +
                    "\n\t0 : Chiudere programma");
            runtime = Integer.parseInt(consoleIn.readLine());
            //Controllo errori
            while (!(0 <= runtime && runtime <= 5)) {
                consoleOut.println("Errore - Cosa vuoi fare?" +
                        "\n\t1 : Caricare" +
                        "\n\t2 : Visualizzare" +
                        "\n\t3 : Modificare" +
                        "\n\t4 : Salvare file" +
                        "\n\t5 : Caricare dati da file");
                runtime = Integer.parseInt(consoleIn.readLine());
            }
            switchLoad = -1;
            switch (runtime) {
                //Scelta Carica
                case 1 -> {
                    while (switchLoad != 0) {
                        consoleOut.println("Cosa vuoi caricare? :" +
                                "\n\t1 : Reparto" +
                                "\n\t2 : Impiegato" +
                                "\n\t3 : Prodotto" +
                                "\n\t4 : Vendita" +
                                "\n\t5 : Ordine" +
                                "\n0 : Torno indietro");
                        switchLoad = Integer.parseInt(consoleIn.readLine());
                        //Controllo errori
                        while (!(0 <= switchLoad && switchLoad <= 5)) {
                            consoleOut.println("Errore - Cosa vuoi caricare? :" +
                                    "\n\t1 : Reparto" +
                                    "\n\t2 : Impiegato" +
                                    "\n\t3 : Prodotto" +
                                    "\n\t4 : Vendita" +
                                    "\n\t5 : Ordine" +
                                    "\n0 : Torno indietro");
                            switchLoad = Integer.parseInt(consoleIn.readLine());
                        }
                        switch (switchLoad) {
                            //Carico Reparto
                            case 1 -> {
                                managerNegozio.loadReparto_UserInput(consoleOut, consoleIn);
                            }
                            //Carico Impiegato
                            case 2 -> {
                                managerNegozio.loadImpiegato_UserInput(consoleOut, consoleIn);
                            }
                            //Carico Prodotto
                            case 3 -> {
                                managerNegozio.loadProdotto_UserInput(consoleOut, consoleIn);
                            }
                            //Carico Vendita
                            case 4 -> {
                                managerNegozio.loadVendita_UserInput(consoleOut, consoleIn);
                            }
                            //Carica Ordine
                            case 5 -> {
                                managerNegozio.loadOrdine_UserInput(consoleOut, consoleIn);
                            }
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
                        consoleOut.println("Cosa vuoi visualizzare? :" +
                                "\n\t1 : Reparto" +
                                "\n\t2 : Impiegato" +
                                "\n\t3 : Prodotto" +
                                "\n\t4 : Vendita" +
                                "\n\t5 : Ordine" +
                                "\n\t6 : Nome Negozio" +
                                "\n0 : Torno indietro");
                        switchLoad = Integer.parseInt(consoleIn.readLine());
                        //Controllo errori
                        while (!(0 <= switchLoad && switchLoad <= 6)) {
                            consoleOut.println("Errore - Cosa vuoi visualizzare? :" +
                                    "\n\t1 : Reparto" +
                                    "\n\t2 : Impiegato" +
                                    "\n\t3 : Prodotto" +
                                    "\n\t4 : Vendita" +
                                    "\n\t5 : Ordine" +
                                    "\n0 : Torno indietro");
                            switchLoad = Integer.parseInt(consoleIn.readLine());
                        }
                        //Scelta visualizzazione
                        switch (switchLoad) {
                            //Visualizzo Reparti
                            case 1 -> {
                                managerNegozio.printReparto_userOutput(consoleOut);
                            }
                            //Visualizzo Impiegati
                            case 2 -> {
                                managerNegozio.printImpiegato_userOutput(consoleOut);
                            }
                            //Visualizzo Prodotti
                            case 3 -> {
                                managerNegozio.printProdotto_userOutput(consoleOut);
                            }
                            //Visualizzo Vendite
                            case 4 -> {
                                managerNegozio.printVendite_userOutput(consoleOut);
                            }
                            //Visualizzo Ordini
                            case 5 -> {
                                managerNegozio.printOrdini_userOutput(consoleOut);
                            }
                            //Visualizzo nome negozio
                            case 6 -> {
                                consoleOut.println(managerNegozio.getNomeNegozio());
                            }
                            //Torno indietro
                            case 0 -> {
                            }
                        }
                    }
                }
                //Scelta modifica
                case 3 -> {
                    while (switchLoad != 0) {
                        consoleOut.println("Cosa vuoi modificare? :" +
                                "\n\t1 : Reparto" +
                                "\n\t2 : Impiegato" +
                                "\n\t3 : Prodotto" +
                                "\n\t4 : Vendita" +
                                "\n\t5 : Ordine" +
                                "\n\t6 : Nome Negozio" +
                                "\n0 : Torno indietro");
                        switchLoad = Integer.parseInt(consoleIn.readLine());
                        //Controllo errori
                        while (!(0 <= switchLoad && switchLoad <= 6)) {
                            consoleOut.println("Errore - Cosa vuoi modificare? :" +
                                    "\n\t1 : Reparto" +
                                    "\n\t2 : Impiegato" +
                                    "\n\t3 : Prodotto" +
                                    "\n\t4 : Vendita" +
                                    "\n\t5 : Ordine" +
                                    "\n0 : Torno indietro");
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
                                consoleOut.println("Cosa vuoi modificare? :" +
                                        "\n\t1 : Denominativo" +
                                        "\n\t2 : Nome Responsabile" +
                                        "\n\t3 : Impiegati" +
                                        "\n\t4 : Prodotti" +
                                        "\n\t5 : Elimina" +
                                        "\n0 : Torno indietro");
                                switchModifica = Integer.parseInt(consoleIn.readLine());
                                //Controllo errore
                                while (!(0 <= switchModifica && switchModifica <= 5)) {
                                    consoleOut.println("Errore - Cosa vuoi modificare? :" +
                                            "\n\t1 : Denominativo" +
                                            "\n\t2 : Nome Responsabile" +
                                            "\n\t3 : Impiegati" +
                                            "\n\t4 : Prodotti" +
                                            "\n\t5 : Elimina" +
                                            "\n0 : Torno indietro");
                                    switchModifica = Integer.parseInt(consoleIn.readLine());
                                }
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
                                        managerNegozio.getListaReparti().get(index).printListaImpiegati_userOutput(consoleOut, consoleIn);
                                        consoleOut.println("Inserisci nome impiegato da modificare");
                                        int indexImpiegato = managerNegozio.getListaReparti().get(index).trovaImpiegatoByNome(consoleIn.readLine());
                                        if (indexImpiegato == -1) {
                                            consoleOut.println("errore");
                                            break;
                                        }
                                        consoleOut.println("Cosa vuoi modificare? :" +
                                                "\n\t1 : Nome" +
                                                "\n\t2 : Cognome" +
                                                "\n\t3 : Codice Fiscale" +
                                                "\n\t4 : Elimina" +
                                                "\n0 : Torna indietro");
                                        switchModifica = Integer.parseInt(consoleIn.readLine());
                                        //Controllo errori
                                        while (!(0 <= switchModifica && switchModifica <= 4)) {
                                            consoleOut.println("Errore - Cosa vuoi modificare? :" +
                                                    "\n\t1 : Nome" +
                                                    "\n\t2 : Cognome" +
                                                    "\n\t3 : Codice Fiscale" +
                                                    "\n\t4 : Elimina" +
                                                    "\n0 : Torna indietro");
                                            switchModifica = Integer.parseInt(consoleIn.readLine());
                                        }
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
                                            case 4 -> {
                                                managerNegozio.getListaReparti().get(index).getListaImpiegati().remove(indexImpiegato);
                                            }
                                            case 0 -> {
                                            }
                                        }
                                    }
                                    //modifica lista prodotti del reparto
                                    case 4 -> {
                                        consoleOut.println("Lista Prodotti del reparto " + managerNegozio.getListaReparti().get(index).getDenominazione());
                                        managerNegozio.getListaReparti().get(index).printListaProdotti_userOutput(consoleOut, consoleIn);
                                        consoleOut.println("Inserisci codice prodotto da modificare");
                                        int indexProdotto = managerNegozio.getListaReparti().get(index).trovaProdottoByCodice(consoleIn.readLine());
                                        if (indexProdotto == -1) {
                                            consoleOut.println("errore");
                                            break;
                                        }
                                        consoleOut.println("Cosa vuoi modificare? :" +
                                                "\n\t1 : Codice" +
                                                "\n\t2 : Denominazione" +
                                                "\n\t3 : Produttore" +
                                                "\n\t4 : Prezzo" +
                                                "\n0 : Torna indietro");
                                        switchModifica = Integer.parseInt(consoleIn.readLine());
                                        switch (switchModifica) {
                                            case 1 -> {
                                                consoleOut.println("Stai modificando il codice : " + managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).getCodice());
                                                String codiceModificato = consoleIn.readLine();
                                                if (!codiceModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).setCodice(codiceModificato);
                                            }
                                            case 2 -> {
                                                consoleOut.println("Stai modificando il denominativo : " + managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).getDenominazione());
                                                String denominativoModificato = consoleIn.readLine();
                                                if (!denominativoModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).setDenominazione(denominativoModificato);
                                            }
                                            case 3 -> {
                                                consoleOut.println("Stai modificando il produttore : " + managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).getProduttore());
                                                String produttoreModificato = consoleIn.readLine();
                                                if (!produttoreModificato.equalsIgnoreCase("null"))
                                                    managerNegozio.getListaReparti().get(index).getListaProdotti().get(indexProdotto).setProduttore(produttoreModificato);
                                            }
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
                                    case 5 -> {
                                        managerNegozio.eliminaReparto(index);
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
                                }
                                consoleOut.println("Cosa vuoi modificare? :" +
                                        "\n\t1 : Nome" +
                                        "\n\t2 : Cognome" +
                                        "\n\t3 : Codice Fiscale" +
                                        "\n\t4 : Elimina" +
                                        "\n0 : Torna indietro");
                                switchModifica = Integer.parseInt(consoleIn.readLine());
                                //Controllo errore
                                while (!(0 <= switchModifica && switchModifica <= 5)) {
                                    consoleOut.println("Errore - Cosa vuoi modificare? :" +
                                            "\n\t1 : Nome" +
                                            "\n\t2 : Cognome" +
                                            "\n\t3 : Codice Fiscale" +
                                            "\n\t4 : Elimina" +
                                            "\n0 : Torna indietro");
                                    switchModifica = Integer.parseInt(consoleIn.readLine());
                                }
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
                                    case 4 -> {
                                        managerNegozio.eliminaImpiegato(indexImpiegato);
                                    }
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
                                    case 5 -> {
                                        managerNegozio.eliminaProdotto(indexProdotto);
                                    }
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
                                    case 3 -> {
                                        managerNegozio.eliminaVendita(indexVendita);
                                    }
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
                                    case 3 -> {
                                        managerNegozio.eliminaOrdine(indexOrdine);
                                    }
                                    //Torno indietro
                                    case 0 -> {
                                    }
                                }
                                break;
                            }
                            case 6 -> {
                                consoleOut.println("Modifica nome Negozio : " + managerNegozio.getNomeNegozio());
                                managerNegozio.setNomeNegozio(consoleIn.readLine());
                            }
                            //Torno indietro
                            case 0 -> {
                            }
                        }
                    }
                }
                //Salvo nel file
                case 4 -> {
                    FileOutputStream fileOutput = new FileOutputStream(serializedFilename);
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                    managerNegozio.saveOnFile(objectOutput);
                    File file = new File(filename);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();

                    FileWriter fileWriter = new FileWriter(filename);
                    managerNegozio.printOnFile(fileWriter);

                    objectOutput.close();
                    fileOutput.close();
                    fileWriter.close();
                }
                //Leggo da file
                case 5 -> {
                    FileInputStream fileInput = new FileInputStream(serializedFilename);
                    ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                    managerNegozio.loadFromFile(objectInput);
                    fileInput.close();
                    objectInput.close();
                }
                case 0 ->{
                    consoleIn.close();
                    consoleOut.close();
                }
            }
        }
    }
}