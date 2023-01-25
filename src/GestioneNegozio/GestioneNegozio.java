package GestioneNegozio;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestioneNegozio implements Serializable {
    private String nomeNegozio;
    private ArrayList<Impiegato> listaImpiegati;
    private ArrayList<Reparto> listaReparti;
    private ArrayList<Prodotto> listaProdotti;
    private ArrayList<Vendita> listaVendite;
    private ArrayList<Ordine> listaOrdini;

    GestioneNegozio(String _nomeNegozio, ArrayList<Impiegato> _listaImpiegati, ArrayList<Reparto> _listaReparti, ArrayList<Prodotto> _listaProdotti, ArrayList<Vendita> _listaVendite, ArrayList<Ordine> _listaOrdini) {
        nomeNegozio = _nomeNegozio;
        listaImpiegati = _listaImpiegati;
        listaReparti = _listaReparti;
        listaProdotti = _listaProdotti;
        listaVendite = _listaVendite;
        listaOrdini = _listaOrdini;
    }

    GestioneNegozio(String _nomeNegozio) {
        nomeNegozio = _nomeNegozio;
        listaImpiegati = new ArrayList<Impiegato>();
        listaReparti = new ArrayList<Reparto>();
        listaProdotti = new ArrayList<Prodotto>();
        listaVendite = new ArrayList<Vendita>();
        listaOrdini = new ArrayList<Ordine>();
    }

    GestioneNegozio() {
    }

    public void loadFromFile(ObjectInputStream objectInput) {

        try {
            this.setListaImpiegati((ArrayList<Impiegato>) objectInput.readObject());
            this.setListaReparti((ArrayList<Reparto>) objectInput.readObject());
            this.setListaProdotti((ArrayList<Prodotto>) objectInput.readObject());
            this.setListaVendite((ArrayList<Vendita>) objectInput.readObject());
            this.setListaOrdini((ArrayList<Ordine>) objectInput.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNomeNegozio() {
        return nomeNegozio;
    }

    public void setNomeNegozio(String _nomeNegozio) {
        nomeNegozio = _nomeNegozio;
    }

    public ArrayList<Impiegato> getListaImpiegati() {
        return listaImpiegati;
    }

    public void setListaImpiegati(ArrayList<Impiegato> _listaImpiegati) {
        listaImpiegati = _listaImpiegati;
    }

    public ArrayList<Reparto> getListaReparti() {
        return listaReparti;
    }

    public void setListaReparti(ArrayList<Reparto> _listaReparti) {
        listaReparti = _listaReparti;
    }

    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(ArrayList<Prodotto> _listaProdotti) {
        listaProdotti = _listaProdotti;
    }

    public ArrayList<Vendita> getListaVendite() {
        return listaVendite;
    }

    public void setListaVendite(ArrayList<Vendita> _listaVendite) {
        listaVendite = _listaVendite;
    }

    public ArrayList<Ordine> getListaOrdini() {
        return listaOrdini;
    }

    public void setListaOrdini(ArrayList<Ordine> _listaOrdini) {
        listaOrdini = _listaOrdini;
    }

    public void printOnFile(FileWriter fw) {
        printImpiegato_onFile(fw);
        printReparto_onFile(fw);
        printProdotto_onFile(fw);
        printVendite_onFile(fw);
        printOrdini_onFile(fw);
    }

    public void printUserOutput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        printImpiegato_userOutput(outln);
        printReparto_userOutput(outln);
        printProdotto_userOutput(outln);
        printVendite_userOutput(outln);
        printOrdini_userOutput(outln);
    }

    public void saveOnFile(ObjectOutputStream objectOutput) {
        try {
            objectOutput.writeObject(listaImpiegati);
            objectOutput.writeObject(listaReparti);
            objectOutput.writeObject(listaProdotti);
            objectOutput.writeObject(listaVendite);
            objectOutput.writeObject(listaOrdini);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void caricaImpiegato_userInput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        //impiegatoLoader per caricare
        Impiegato impiegatoLoader = new Impiegato();
        outln.println("Codice Fiscale Impiegato : ");
        impiegatoLoader.setCF(inln.readLine());
        outln.println("Nome Impiegato : ");
        impiegatoLoader.setNome(inln.readLine());
        outln.println("Cognome Impiegato : ");
        impiegatoLoader.setCognome(inln.readLine());
        listaImpiegati.add(impiegatoLoader);
        outln.println("Inserisci il denominativo del reparto oppure lascia vuoto : ");
        printListaReparti_userOutput(outln);
        String repartoAssociato = inln.readLine();
        if (repartoAssociato.equalsIgnoreCase("")) {
            return;
        }
        int index = trovaRepartoByNome(repartoAssociato);
        if (index == -1) {
            while (index == -1) {
                outln.println("Errore, Inserisci il denominativo del reparto oppure lascia vuoto : ");
                printListaReparti_userOutput(outln);
                repartoAssociato = inln.readLine();
                index = trovaRepartoByNome(repartoAssociato);
                if (!repartoAssociato.equalsIgnoreCase("") && index != -1) {
                    listaReparti.get(index).getListaImpiegati().add(impiegatoLoader);
                } else if (repartoAssociato.equalsIgnoreCase("")) {
                    break;
                }
            }
        } else {
            listaReparti.get(index).getListaImpiegati().add(impiegatoLoader);
        }
    }

    public void caricaReparto_userInput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        //repartoLoader per caricare
        Reparto repartoLoader = new Reparto();
        outln.println("Denominazione Reparto : ");
        repartoLoader.setDenominazione(inln.readLine());
        outln.println("Nome Responsabile Reparto : ");
        repartoLoader.setResponsabileReparto(inln.readLine());

        listaReparti.add(repartoLoader);
    }

    public void caricaProdotto_userInput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        //repartoLoader per caricare
        Prodotto prodottoLoader = new Prodotto();
        outln.println("Codice Prodotto : ");
        prodottoLoader.setCodice(inln.readLine());
        outln.println("Denominazione Prodotto : ");
        prodottoLoader.setDenominazione(inln.readLine());
        outln.println("Produttore : ");
        prodottoLoader.setProduttore(inln.readLine());
        outln.println("Prezzo : ");
        prodottoLoader.setPrezzo(inln.readLine());
        listaProdotti.add(prodottoLoader);
        outln.println("Inserisci il denominativo del reparto a cui appartiene questo prodotto oppure lascia vuoto: ");
        printListaReparti_userOutput(outln);
        String repartoAssociato = inln.readLine();
        if (repartoAssociato.equalsIgnoreCase("")) {
            return;
        }
        int index = trovaRepartoByNome(repartoAssociato);
        if (index == -1) {
            while (index == -1) {
                outln.println("Errore, Inserisci il denominativo del reparto oppure lascia vuoto : ");
                printListaReparti_userOutput(outln);
                repartoAssociato = inln.readLine();
                index = trovaRepartoByNome(repartoAssociato);
                if (!repartoAssociato.equalsIgnoreCase("") && index != -1) {
                    listaReparti.get(index).getListaProdotti().add(prodottoLoader);
                } else if (repartoAssociato.equalsIgnoreCase("")) {
                    break;
                }
            }
        } else {
            listaReparti.get(index).getListaProdotti().add(prodottoLoader);
        }
    }

    public void caricaVendita_userInput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        //repartoLoader per caricare
        Vendita venditaLoader = new Vendita();
        outln.println("Data Vendita in formato YYYY-MM-DD : ");
        venditaLoader.setDataVendita(inln.readLine());
        outln.println("Quantità vendute : ");
        venditaLoader.setQnt(inln.readLine());
        outln.println("Inserisci il denominativo del prodotto venduto oppure lascia vuoto: ");
        printListaProdotti_userOutput(outln);
        String prodottoAssociato = inln.readLine();
        int index = trovaProdottoByCodice(prodottoAssociato);
        if (prodottoAssociato.equalsIgnoreCase("")) {
            venditaLoader.setEmptyProdottoVenduto();
            listaVendite.add(venditaLoader);
            return;
        }
        if (index == -1) {
            while (index == -1) {
                outln.println("Errore, Inserisci il codice del prodotto oppure lascia vuoto : ");
                printListaProdotti_userOutput(outln);
                prodottoAssociato = inln.readLine();
                index = trovaProdottoByCodice(prodottoAssociato);
                if (!prodottoAssociato.equalsIgnoreCase("") && index != -1) {
                    venditaLoader.setProdottoVenduto(listaProdotti.get(index));
                } else if (prodottoAssociato.equalsIgnoreCase("")) {
                    break;
                }
            }
        } else {
            venditaLoader.setProdottoVenduto(listaProdotti.get(index));
            listaVendite.add(venditaLoader);
        }
    }

    public void caricaOrdine_userInput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        //repartoLoader per caricare
        Ordine ordineLoader = new Ordine();
        outln.println("Data ordine in formato YYYY-MM-DD : ");
        ordineLoader.setDataOrdine(inln.readLine());
        outln.println("Quantità ordinate : ");
        ordineLoader.setQnt(inln.readLine());
        outln.println("Inserisci il denominativo del prodotto ordinato oppure lascia vuoto: ");
        printListaProdotti_userOutput(outln);
        String prodottoAssociato = inln.readLine();
        int index = trovaProdottoByCodice(prodottoAssociato);
        if (prodottoAssociato.equalsIgnoreCase("")) {
            ordineLoader.setEmptyProdottoVenduto();
            listaOrdini.add(ordineLoader);
            return;
        }
        if (index == -1) {
            while (index == -1) {
                outln.println("Errore, Inserisci il codice del prodotto oppure lascia vuoto : ");
                printListaProdotti_userOutput(outln);
                prodottoAssociato = inln.readLine();
                index = trovaProdottoByCodice(prodottoAssociato);
                if (!prodottoAssociato.equalsIgnoreCase("") && index != -1) {
                    ordineLoader.setProdottoVenduto(listaProdotti.get(index));
                } else if (prodottoAssociato.equalsIgnoreCase("")) {
                    break;
                }
            }
        } else {
            ordineLoader.setProdottoVenduto(listaProdotti.get(index));
            listaOrdini.add(ordineLoader);
        }
    }

    public int trovaRepartoByNome(String repartoCercato) {
        for (int i = 0; i < listaReparti.size(); i++) {
            if (listaReparti.get(i).getDenominazione().equalsIgnoreCase(repartoCercato)) {
                return i;
            }
        }
        return -1;
    }

    public int trovaImpiegatoByNome(String impiegatoCercato) {
        for (int i = 0; i < listaImpiegati.size(); i++) {
            if (listaImpiegati.get(i).getNome().equalsIgnoreCase(impiegatoCercato)) {
                return i;
            }
        }
        return -1;
    }

    public int trovaProdottoByCodice(String prodottoCercato) {
        for (int i = 0; i < listaProdotti.size(); i++) {
            if (listaProdotti.get(i).getCodice().equalsIgnoreCase(prodottoCercato)) {
                return i;
            }
        }
        return -1;
    }

    public int trovaVenditaByProdotto(String venditaCercata) {
        for (int i = 0; i < listaVendite.size(); i++) {
            if (listaVendite.get(i).getProdottoVenduto().getCodice().equalsIgnoreCase(venditaCercata)) {
                return i;
            }
        }
        return -1;
    }

    public int trovaOrdineByProdotto(String venditaCercata) {
        for (int i = 0; i < listaOrdini.size(); i++) {
            if (listaOrdini.get(i).getProdottoVenduto().getCodice().equalsIgnoreCase(venditaCercata)) {
                return i;
            }
        }
        return -1;
    }

    public void caricaVenditaInList(Vendita venditaLoader) {
        listaVendite.add(venditaLoader);
    }

    public void caricaImpiegatoInList(Impiegato impiegatoLoader) {
        listaImpiegati.add(impiegatoLoader);
    }

    public void caricaRepartoInList(Reparto repartoLoader) {
        listaReparti.add(repartoLoader);
    }

    public void caricaProdottoInList(Prodotto prodottoLoader) {
        listaProdotti.add(prodottoLoader);
    }

    public void printImpiegato_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Impiegati : ");
        for (int i = 0; i < listaImpiegati.size(); i++) {
            consoleOut.println("\n\tCodice Fiscale Impiegato : " + listaImpiegati.get(i).getCF());
            consoleOut.println("\tNome Impiegato : " + listaImpiegati.get(i).getNome());
            consoleOut.println("\tCognome Impiegato : " + listaImpiegati.get(i).getCognome());
        }
    }

    public void printProdotto_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Prodotti : ");
        for (int i = 0; i < listaProdotti.size(); i++) {
            consoleOut.println("\n\tCodice Prodotto : " + listaProdotti.get(i).getCodice());
            consoleOut.println("\tDenominazione Prodotto : " + listaProdotti.get(i).getDenominazione());
            consoleOut.println("\tProduttore : " + listaProdotti.get(i).getProduttore());
            consoleOut.println("\tPrezzo : " + listaProdotti.get(i).getPrezzo());
        }
    }

    public void printVendite_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Vendite : ");
        for (int i = 0; i < listaVendite.size(); i++) {
            consoleOut.println("\n\tProdotto : " + listaVendite.get(i).getProdottoVenduto().getCodice());
            consoleOut.println("\tData Vendita : " + listaVendite.get(i).getDataVendita());
            consoleOut.println("\tQuantità vendute : " + listaVendite.get(i).getQnt());
        }
    }

    public void printOrdini_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Ordini : ");
        for (int i = 0; i < listaOrdini.size(); i++) {
            consoleOut.println("\n\tProdotto : " + listaOrdini.get(i).getProdottoVenduto().getCodice());
            consoleOut.println("\tData Ordine : " + listaOrdini.get(i).getDataOrdine());
            consoleOut.println("\tQuantità ordinate : " + listaOrdini.get(i).getQnt());
        }
    }

    public void printReparto_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Reparti : ");
        for (int i = 0; i < listaReparti.size(); i++) {
            consoleOut.println("\n\tDenominazione Reparto : " + listaReparti.get(i).getDenominazione());
            consoleOut.println("\tNome Responsabile Reparto : " + listaReparti.get(i).getResponsabileReparto());
            consoleOut.println("\t\t----------------------------------------------------------");
            consoleOut.println("\t\tImpiegati che lavorano in " + listaReparti.get(i).getDenominazione());
            for (int j = 0; j < listaReparti.get(i).getListaImpiegati().size(); j++) {
                consoleOut.println("\n\t\t\tNome Cognome Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getNome() + " " + listaReparti.get(i).getListaImpiegati().get(j).getCognome());
            }
            consoleOut.println("\t\t----------------------------------------------------------");
            consoleOut.println("\t\tProdotti in " + listaReparti.get(i).getDenominazione());
            for (int j = 0; j < listaReparti.get(i).getListaProdotti().size(); j++) {
                consoleOut.println("\n\t\t\tCodice Prodotto : " + listaReparti.get(i).getListaProdotti().get(j).getCodice());
            }
        }
    }

    public void printRepartoByIndex_userOutput(ConsoleOutputManager consoleOut, int index) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Reparto " + listaReparti.get(index).getDenominazione());
        consoleOut.println("\n\tDenominazione Reparto : " + listaReparti.get(index).getDenominazione());
        consoleOut.println("\tNome Responsabile Reparto : " + listaReparti.get(index).getResponsabileReparto());
        consoleOut.println("\t\t----------------------------------------------------------");
        consoleOut.println("\t\tImpiegati che lavorano in " + listaReparti.get(index).getDenominazione());
        for (int j = 0; j < listaReparti.get(index).getListaImpiegati().size(); j++) {
            consoleOut.println("\n\t\t\tNome Cognome Impiegato : " + listaReparti.get(index).getListaImpiegati().get(j).getNome() + " " + listaReparti.get(index).getListaImpiegati().get(j).getCognome());
        }
        consoleOut.println("\t\t----------------------------------------------------------");
        consoleOut.println("\t\tProdotti in " + listaReparti.get(index).getDenominazione());
        for (int j = 0; j < listaReparti.get(index).getListaProdotti().size(); j++) {
            consoleOut.println("\n\t\t\tCodice Prodotto : " + listaReparti.get(index).getListaProdotti().get(j).getCodice());

        }
    }

    public void printListaReparti_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        for (int i = 0; i < listaReparti.size(); i++) {
            consoleOut.println("\n\tDenominazione Reparto : " + listaReparti.get(i).getDenominazione());
            consoleOut.println("\tNome Responsabile Reparto : " + listaReparti.get(i).getResponsabileReparto());
        }
    }

    public void printListaProdotti_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        for (int i = 0; i < listaProdotti.size(); i++) {
            consoleOut.println("\n\tCodice  Prodotto : " + listaProdotti.get(i).getCodice());
        }
    }

    public void printListaImpiegati_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        for (int i = 0; i < listaImpiegati.size(); i++) {
            consoleOut.println("\n\tNome Impiegato: " + listaImpiegati.get(i).getNome());
        }
    }

    public void printReparto_onFile(FileWriter fileWriter) {
        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Reparti in " + this.nomeNegozio);
            for (int i = 0; i < listaReparti.size(); i++) {
                fileWriter.write("\n\n\tDenominativo Reparto : " + listaReparti.get(i).getDenominazione());
                fileWriter.write("\n\tNome Responsabile Reparto : " + listaReparti.get(i).getResponsabileReparto());
                fileWriter.write("\n\t\tImpiegati che lavorano in " + listaReparti.get(i).getDenominazione());
                for (int j = 0; j < listaReparti.get(i).getListaImpiegati().size(); j++) {
                    fileWriter.write("\n\t\t\tCodice Fiscale Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getCF());
                    fileWriter.write("\n\t\t\tNome Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getNome());
                    fileWriter.write("\n\t\t\tCognome Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getCognome());
                }
                fileWriter.write("\n\t\tProdotti in " + listaReparti.get(i).getDenominazione());
                for (int j = 0; j < listaReparti.get(i).getListaImpiegati().size(); j++) {
                    fileWriter.write("\n\t\t\tCodice Prodotto : " + listaReparti.get(i).getListaProdotti().get(j).getCodice());
                    fileWriter.write("\n\t\t\tDenominativo Prodotto : " + listaReparti.get(i).getListaProdotti().get(j).getDenominazione());
                    fileWriter.write("\n\t\t\tProduttore : " + listaReparti.get(i).getListaProdotti().get(j).getProduttore());
                    fileWriter.write("\n\t\t\tPrezzo : " + listaReparti.get(i).getListaProdotti().get(j).getPrezzo());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printImpiegato_onFile(FileWriter fileWriter) {
        try {
            fileWriter.write("----------------------------------------------------------");
            fileWriter.write("\nLista Impiegati che lavorano in " + this.nomeNegozio);
            for (int i = 0; i < listaImpiegati.size(); i++) {
                fileWriter.write("\n\n\tCodice Fiscale Impiegato : " + listaImpiegati.get(i).getCF());
                fileWriter.write("\n\tNome Impiegato : " + listaImpiegati.get(i).getNome());
                fileWriter.write("\n\tCognome Impiegato : " + listaImpiegati.get(i).getCognome());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printProdotto_onFile(FileWriter fileWriter) {

        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Prodotti in " + this.nomeNegozio);
            for (int i = 0; i < listaProdotti.size(); i++) {
                fileWriter.write("\n\n\tCodice Prodotto : " + listaProdotti.get(i).getCodice());
                fileWriter.write("\n\tDenominazione Prodotto : " + listaProdotti.get(i).getDenominazione());
                fileWriter.write("\n\tProduttore : " + listaProdotti.get(i).getProduttore());
                fileWriter.write("\n\tPrezzo : " + listaProdotti.get(i).getPrezzo());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printVendite_onFile(FileWriter fileWriter) {
        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Vendite in " + this.nomeNegozio);
            for (int i = 0; i < listaVendite.size(); i++) {
                fileWriter.write("\n\n\tProdotto : " + listaVendite.get(i).getProdottoVenduto().getCodice());
                fileWriter.write("\n\tData Vendita : " + listaVendite.get(i).getDataVendita());
                fileWriter.write("\n\tQuantità vendute : " + listaVendite.get(i).getQnt());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printOrdini_onFile(FileWriter fileWriter) {
        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Ordini in " + this.nomeNegozio);
            for (int i = 0; i < listaOrdini.size(); i++) {
                fileWriter.write("\n\n\tProdotto : " + listaOrdini.get(i).getProdottoVenduto().getCodice());
                fileWriter.write("\n\tData Ordine : " + listaOrdini.get(i).getDataOrdine());
                fileWriter.write("\n\tQuantità ordinate : " + listaOrdini.get(i).getQnt());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadReparto_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //Reparto repartoLoader = new Reparto("Schede Video", "Rossi Franco");
        //caricaRepartoInList(repartoLoader);
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            caricaReparto_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un altro reparto? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void loadImpiegato_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //Impiegato impiegatoLoader = new Impiegato("RTNMRC01P12I452D", "Marco", "Rotunno");
        //caricaImpiegatoInList(impiegatoLoader);
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            //listaImpiegati per caricare nel negozio
            caricaImpiegato_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un altro impiegato? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void loadProdotto_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //Prodotto prodottoLoader = new Prodotto("RTX3080OC", "GPU RTX 3080 Overclocked", "Asus Rog", 699f);
        //caricaProdottoInList(prodottoLoader);
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            caricaProdotto_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un altro prodotto? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void loadVendita_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //Vendita vendita Loader = new Prodotto("RTX3080OC","GPU RTX 3080 Overclocked","Asus Rog",699f);
        //managerNegozio.caricaProdottoInList(prodottoLoader);
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            caricaVendita_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un'altra vendita? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void loadOrdine_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            caricaOrdine_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un altro ordine? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void eliminaReparto(int index) {
        listaReparti.remove(index);
    }

    public void eliminaImpiegato(int index) {
        listaImpiegati.remove(index);
    }

    public void eliminaProdotto(int index) {
        listaProdotti.remove(index);
    }

    public void eliminaVendita(int index) {
        listaVendite.remove(index);
    }

    public void eliminaOrdine(int index) {
        listaOrdini.remove(index);
    }
}