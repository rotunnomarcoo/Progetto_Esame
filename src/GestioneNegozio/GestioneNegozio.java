package GestioneNegozio;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GestioneNegozio implements Serializable {
    private String nomeNegozio;
    private ArrayList<Impiegato> listaImpiegati;
    private ArrayList<Reparto> listaReparti;
    private ArrayList<Prodotto> listaProdotti;
    private ArrayList<Vendita> listaVendite;
    private ArrayList<Ordine> listaOrdini;

    GestioneNegozio(String _nomeNegozio) {
        nomeNegozio = _nomeNegozio;
        listaImpiegati = new ArrayList<>();
        listaReparti = new ArrayList<>();
        listaProdotti = new ArrayList<>();
        listaVendite = new ArrayList<>();
        listaOrdini = new ArrayList<>();
    }

    public void loadFromFile(ObjectInputStream objectInput) {
        try {
            this.setListaImpiegati((ArrayList<Impiegato>) objectInput.readObject());
            this.setListaReparti((ArrayList<Reparto>) objectInput.readObject());
            this.setListaProdotti((ArrayList<Prodotto>) objectInput.readObject());
            this.setListaVendite((ArrayList<Vendita>) objectInput.readObject());
            this.setListaOrdini((ArrayList<Ordine>) objectInput.readObject());
            this.ordinaImpiegatiByCognome(listaImpiegati);
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

    public void printUserOutput(ConsoleOutputManager consoleOut) {
        printImpiegato_userOutput(consoleOut);
        printReparto_userOutput(consoleOut);
        printProdotto_userOutput(consoleOut);
        printVendite_userOutput(consoleOut);
        printOrdini_userOutput(consoleOut);
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

    public void caricaImpiegato_userInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //impiegatoLoader per caricare
        Impiegato impiegatoLoader = new Impiegato();
        consoleOut.println("Codice Fiscale Impiegato : ");
        impiegatoLoader.setCF(consoleIn.readLine());
        consoleOut.println("Nome Impiegato : ");
        impiegatoLoader.setNome(consoleIn.readLine());
        consoleOut.println("Cognome Impiegato : ");
        impiegatoLoader.setCognome(consoleIn.readLine());
        consoleOut.println("Inserisci il denominativo del reparto oppure lascia vuoto : ");
        printListaReparti_userOutput(consoleOut);
        String repartoAssociato = consoleIn.readLine();
        if (repartoAssociato.equalsIgnoreCase("")) {
            listaImpiegati.add(impiegatoLoader);
            return;
        }
        int index = trovaRepartoByNome(repartoAssociato);
        if (index == -1) {
            while (index == -1) {
                consoleOut.println("Errore, Inserisci il denominativo del reparto oppure lascia vuoto : ");
                printListaReparti_userOutput(consoleOut);
                repartoAssociato = consoleIn.readLine();
                index = trovaRepartoByNome(repartoAssociato);
                if (!repartoAssociato.equalsIgnoreCase("") && index != -1) {
                    impiegatoLoader.getListaReparti().add(listaReparti.get(index));
                    listaImpiegati.add(impiegatoLoader);
                    listaReparti.get(index).getListaImpiegati().add(impiegatoLoader);
                } else if (repartoAssociato.equalsIgnoreCase("")) {
                    break;
                }
            }
        } else {
            impiegatoLoader.getListaReparti().add(listaReparti.get(index));
            listaImpiegati.add(impiegatoLoader);
            listaReparti.get(index).getListaImpiegati().add(impiegatoLoader);
        }
    }

    public void caricaReparto_userInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //repartoLoader per caricare
        Reparto repartoLoader = new Reparto();
        consoleOut.println("Denominazione Reparto : ");
        repartoLoader.setDenominazione(consoleIn.readLine());
        consoleOut.println("Nome Responsabile Reparto : ");
        repartoLoader.setResponsabileReparto(consoleIn.readLine());

        listaReparti.add(repartoLoader);
    }

    public void caricaProdotto_userInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //repartoLoader per caricare
        Prodotto prodottoLoader = new Prodotto();
        consoleOut.println("Codice Prodotto : ");
        prodottoLoader.setCodice(consoleIn.readLine());
        consoleOut.println("Denominazione Prodotto : ");
        prodottoLoader.setDenominazione(consoleIn.readLine());
        consoleOut.println("Produttore : ");
        prodottoLoader.setProduttore(consoleIn.readLine());
        consoleOut.println("Prezzo : ");
        prodottoLoader.setPrezzo(consoleIn.readLine());
        listaProdotti.add(prodottoLoader);
        consoleOut.println("Inserisci il denominativo del reparto a cui appartiene questo prodotto oppure lascia vuoto: ");
        printListaReparti_userOutput(consoleOut);
        String repartoAssociato = consoleIn.readLine();
        if (repartoAssociato.equalsIgnoreCase("")) {
            return;
        }
        int index = trovaRepartoByNome(repartoAssociato);
        if (index == -1) {
            while (index == -1) {
                consoleOut.println("Errore, Inserisci il denominativo del reparto oppure lascia vuoto : ");
                printListaReparti_userOutput(consoleOut);
                repartoAssociato = consoleIn.readLine();
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

    public void caricaVendita_userInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        //repartoLoader per caricare
        Vendita venditaLoader = new Vendita();
        consoleOut.println("Data Vendita in formato YYYY-MM-DD : ");
        venditaLoader.setDataVendita(consoleIn.readLine());
        consoleOut.println("Quantità vendute : ");
        venditaLoader.setQnt(consoleIn.readLine());
        consoleOut.println("Inserisci il denominativo del prodotto venduto oppure lascia vuoto: ");
        printListaProdotti_userOutput(consoleOut);
        String prodottoAssociato = consoleIn.readLine();
        int index = trovaProdottoByCodice(prodottoAssociato);
        if (prodottoAssociato.equalsIgnoreCase("")) {
            venditaLoader.setEmptyProdottoVenduto();
            listaVendite.add(venditaLoader);
            return;
        }
        if (index == -1) {
            while (index == -1) {
                consoleOut.println("Errore, Inserisci il codice del prodotto oppure lascia vuoto : ");
                printListaProdotti_userOutput(consoleOut);
                prodottoAssociato = consoleIn.readLine();
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

    public void caricaOrdine_userInput(ConsoleOutputManager consoleOut, ConsoleInputManager inln) {
        //repartoLoader per caricare
        Ordine ordineLoader = new Ordine();
        consoleOut.println("Data ordine in formato YYYY-MM-DD : ");
        ordineLoader.setDataOrdine(inln.readLine());
        consoleOut.println("Quantità ordinate : ");
        ordineLoader.setQnt(inln.readLine());
        consoleOut.println("Inserisci il denominativo del prodotto ordinato oppure lascia vuoto: ");
        printListaProdotti_userOutput(consoleOut);
        String prodottoAssociato = inln.readLine();
        int index = trovaProdottoByCodice(prodottoAssociato);
        if (prodottoAssociato.equalsIgnoreCase("")) {
            ordineLoader.setEmptyProdottoVenduto();
            listaOrdini.add(ordineLoader);
            return;
        }
        if (index == -1) {
            while (index == -1) {
                consoleOut.println("Errore, Inserisci il codice del prodotto oppure lascia vuoto : ");
                printListaProdotti_userOutput(consoleOut);
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

    public void printImpiegato_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Impiegati : ");
        for (Impiegato impiegato : listaImpiegati) {
            consoleOut.println("\n\tCodice Fiscale Impiegato : " + impiegato.getCF());
            consoleOut.println("\tNome Impiegato : " + impiegato.getNome());
            consoleOut.println("\tCognome Impiegato : " + impiegato.getCognome());
            consoleOut.println("\tReparti Assegnati : ");
            for (int i = 0; i < impiegato.getListaReparti().size(); i++) {
                consoleOut.println("\t\t" + impiegato.getListaReparti().get(i).getDenominazione());
            }
        }
    }

    public void printImpiegato_userOutput(ConsoleOutputManager consoleOut, int index) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Impiegato : ");
        consoleOut.println("\n\tCodice Fiscale Impiegato : " + listaImpiegati.get(index).getCF());
        consoleOut.println("\tNome Impiegato : " + listaImpiegati.get(index).getNome());
        consoleOut.println("\tCognome Impiegato : " + listaImpiegati.get(index).getCognome());
        consoleOut.println("\tReparti Assegnati : ");
        for (int i = 0; i < listaImpiegati.get(index).getListaReparti().size(); i++) {
            consoleOut.println("\t\t" + listaImpiegati.get(index).getListaReparti().get(i).getDenominazione());
        }
    }

    public void printProdotto_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Prodotti : ");
        for (Prodotto prodotto : listaProdotti) {
            consoleOut.println("\n\tCodice Prodotto : " + prodotto.getCodice());
            consoleOut.println("\tDenominazione Prodotto : " + prodotto.getDenominazione());
            consoleOut.println("\tProduttore : " + prodotto.getProduttore());
            consoleOut.println("\tPrezzo : " + prodotto.getPrezzo());
        }
    }

    public void printVendite_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Vendite : ");
        for (Vendita vendita : listaVendite) {
            consoleOut.println("\n\tProdotto : " + vendita.getProdottoVenduto().getCodice());
            consoleOut.println("\tData Vendita : " + vendita.getDataVendita());
            consoleOut.println("\tQuantità vendute : " + vendita.getQnt());
        }
    }

    public void printOrdini_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Ordini : ");
        for (Ordine ordine : listaOrdini) {
            consoleOut.println("\n\tProdotto : " + ordine.getProdottoVenduto().getCodice());
            consoleOut.println("\tData Ordine : " + ordine.getDataOrdine());
            consoleOut.println("\tQuantità ordinate : " + ordine.getQnt());
        }
    }

    public void printReparto_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        consoleOut.println("Reparti : ");
        for (Reparto reparto : listaReparti) {
            consoleOut.println("\n\tDenominazione Reparto : " + reparto.getDenominazione());
            consoleOut.println("\tNome Responsabile Reparto : " + reparto.getResponsabileReparto());
            consoleOut.println("\t\t----------------------------------------------------------");
            consoleOut.println("\t\tImpiegati che lavorano in " + reparto.getDenominazione());
            for (int j = 0; j < reparto.getListaImpiegati().size(); j++) {
                consoleOut.println("\n\t\t\tNome Cognome Impiegato : " + reparto.getListaImpiegati().get(j).getNome() + " " + reparto.getListaImpiegati().get(j).getCognome());
            }
            consoleOut.println("\t\t----------------------------------------------------------");
            consoleOut.println("\t\tProdotti in " + reparto.getDenominazione());
            for (int j = 0; j < reparto.getListaProdotti().size(); j++) {
                consoleOut.println("\n\t\t\tCodice Prodotto : " + reparto.getListaProdotti().get(j).getCodice());
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
        for (Reparto reparto : listaReparti) {
            consoleOut.println("\n\tDenominazione Reparto : " + reparto.getDenominazione());
            consoleOut.println("\tNome Responsabile Reparto : " + reparto.getResponsabileReparto());
        }
    }
    public void printListaRepartiByImpiegato_userOutput(ConsoleOutputManager consoleOut,int indexImpiegato) {
        consoleOut.println("----------------------------------------------------------");
        for (Reparto reparto : listaImpiegati.get(indexImpiegato).getListaReparti()) {
            consoleOut.println("\n\tDenominazione Reparto : " + reparto.getDenominazione());
            consoleOut.println("\tNome Responsabile Reparto : " + reparto.getResponsabileReparto());
        }
    }
    public void printListaProdotti_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        for (Prodotto prodotto : listaProdotti) {
            consoleOut.println("\n\tCodice  Prodotto : " + prodotto.getCodice());
        }
    }

    public void printListaImpiegati_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        for (Impiegato impiegato : listaImpiegati) {
            consoleOut.println("\n\tNome Impiegato: " + impiegato.getNome());
        }
    }

    public void printReparto_onFile(FileWriter fileWriter) {
        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Reparti in " + this.nomeNegozio);
            for (Reparto reparto : listaReparti) {
                fileWriter.write("\n\n\tDenominativo Reparto : " + reparto.getDenominazione());
                fileWriter.write("\n\tNome Responsabile Reparto : " + reparto.getResponsabileReparto());
                fileWriter.write("\n\t\tImpiegati che lavorano in " + reparto.getDenominazione());
                for (int j = 0; j < reparto.getListaImpiegati().size(); j++) {
                    fileWriter.write("\n\t\t\tCodice Fiscale Impiegato : " + reparto.getListaImpiegati().get(j).getCF());
                    fileWriter.write("\n\t\t\tNome Impiegato : " + reparto.getListaImpiegati().get(j).getNome());
                    fileWriter.write("\n\t\t\tCognome Impiegato : " + reparto.getListaImpiegati().get(j).getCognome());
                }
                fileWriter.write("\n\t\tProdotti in " + reparto.getDenominazione());
                for (int j = 0; j < reparto.getListaProdotti().size(); j++) {
                    fileWriter.write("\n\t\t\tCodice Prodotto : " + reparto.getListaProdotti().get(j).getCodice());
                    fileWriter.write("\n\t\t\tDenominativo Prodotto : " + reparto.getListaProdotti().get(j).getDenominazione());
                    fileWriter.write("\n\t\t\tProduttore : " + reparto.getListaProdotti().get(j).getProduttore());
                    fileWriter.write("\n\t\t\tPrezzo : " + reparto.getListaProdotti().get(j).getPrezzo());
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
            for (Impiegato impiegato : listaImpiegati) {
                fileWriter.write("\n\n\tCodice Fiscale Impiegato : " + impiegato.getCF());
                fileWriter.write("\n\tNome Impiegato : " + impiegato.getNome());
                fileWriter.write("\n\tCognome Impiegato : " + impiegato.getCognome());
                fileWriter.write("\n\tReparti Assegnati : ");
                for (int i = 0; i < impiegato.getListaReparti().size(); i++) {
                    fileWriter.write("\n\t\t" + impiegato.getListaReparti().get(i).getDenominazione());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printProdotto_onFile(FileWriter fileWriter) {

        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Prodotti in " + this.nomeNegozio);
            for (Prodotto prodotto : listaProdotti) {
                fileWriter.write("\n\n\tCodice Prodotto : " + prodotto.getCodice());
                fileWriter.write("\n\tDenominazione Prodotto : " + prodotto.getDenominazione());
                fileWriter.write("\n\tProduttore : " + prodotto.getProduttore());
                fileWriter.write("\n\tPrezzo : " + prodotto.getPrezzo());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printVendite_onFile(FileWriter fileWriter) {
        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Vendite in " + this.nomeNegozio);
            for (Vendita vendita : listaVendite) {
                fileWriter.write("\n\n\tProdotto : " + vendita.getProdottoVenduto().getCodice());
                fileWriter.write("\n\tData Vendita : " + vendita.getDataVendita());
                fileWriter.write("\n\tQuantità vendute : " + vendita.getQnt());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printOrdini_onFile(FileWriter fileWriter) {
        try {
            fileWriter.write("\n\n-----------------------------------------------------------");
            fileWriter.write("\nLista Ordini in " + this.nomeNegozio);
            for (Ordine ordine : listaOrdini) {
                fileWriter.write("\n\n\tProdotto : " + ordine.getProdottoVenduto().getCodice());
                fileWriter.write("\n\tData Ordine : " + ordine.getDataOrdine());
                fileWriter.write("\n\tQuantità ordinate : " + ordine.getQnt());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadReparto_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            caricaReparto_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un altro reparto? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void loadImpiegato_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            //listaImpiegati per caricare nel negozio
            caricaImpiegato_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un altro impiegato? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void loadProdotto_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
        String loadAnother = "s";
        while (loadAnother.equalsIgnoreCase("s")) {
            caricaProdotto_userInput(consoleOut, consoleIn);
            consoleOut.println("Vuoi caricare un altro prodotto? \n S / N");
            loadAnother = consoleIn.readLine();
        }
    }

    public void loadVendita_UserInput(ConsoleOutputManager consoleOut, ConsoleInputManager consoleIn) {
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

    public void ordinaImpiegatiByCognome(ArrayList<Impiegato> listaImpiegati) {
        Collections.sort(listaImpiegati);
    }

}