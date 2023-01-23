package GestioneNegozio;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestioneNegozio {
    private String nomeNegozio;
    private List<Impiegato> listaImpiegati;
    private List<Reparto> listaReparti;
    private List<Prodotto> listaProdotti;
    private List<Vendita> listaVendite;
    private List<Ordine> listaOrdini;

    GestioneNegozio(String _nomeNegozio, List<Impiegato> _listaImpiegati, List<Reparto> _listaReparti, List<Prodotto> _listaProdotti, List<Vendita> _listaVendite, List<Ordine> _listaOrdini) {
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

    public String getNomeNegozio() {
        return nomeNegozio;
    }

    public void setNomeNegozio(String _nomeNegozio) {
        nomeNegozio = _nomeNegozio;
    }

    public List<Impiegato> getListaImpiegati() {
        return listaImpiegati;
    }

    public void setListaImpiegati(List<Impiegato> _listaImpiegati) {
        listaImpiegati = _listaImpiegati;
    }

    public List<Reparto> getListaReparti() {
        return listaReparti;
    }

    public void setListaReparti(List<Reparto> _listaReparti) {
        listaReparti = _listaReparti;
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<Prodotto> _listaProdotti) {
        listaProdotti = _listaProdotti;
    }

    public List<Vendita> getListaVendite() {
        return listaVendite;
    }

    public void setListaVendite(List<Vendita> _listaVendite) {
        listaVendite = _listaVendite;
    }

    public List<Ordine> getListaOrdini() {
        return listaOrdini;
    }

    public void setListaOrdini(List<Ordine> _listaOrdini) {
        listaOrdini = _listaOrdini;
    }

    public void printOnFile(FileWriter fw) {
        try {
            printImpiegato_onFile(fw);
            printReparto_onFile(fw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printUserOutput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        printImpiegato_userOutput(outln, inln);
        printReparto_userOutput(outln, inln);
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

        outln.println("Inserisci il denominativo del reparto oppure NULL: ");
        printListaReparti_userOutput(outln, inln);
        String repartoAssociato = inln.readLine();
        int index = trovaRepartoByNome(repartoAssociato);
        if (index == -1) {
            while (index == -1) {
                outln.println("Errore, Inserisci il denominativo del reparto oppure NULL : ");
                printListaReparti_userOutput(outln, inln);
                repartoAssociato = inln.readLine();
                index = trovaRepartoByNome(repartoAssociato);
                if (!repartoAssociato.equalsIgnoreCase("null") && index != -1) {
                    listaReparti.get(index).getListaImpiegati().add(impiegatoLoader);
                } else if (repartoAssociato.equalsIgnoreCase("null")) {
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




    public int trovaRepartoByNome(String repartoCercato) {
        for (int i = 0; i < listaReparti.size(); i++) {
            if (listaReparti.get(i).getDenominazione().equalsIgnoreCase(repartoCercato)) {
                return i;
            }
        }
        return -1;
    }

    public void caricaImpiegatoInList(Impiegato impiegatoLoader) {
        listaImpiegati.add(impiegatoLoader);
    }

    public void caricaRepartoInList(Reparto repartoLoader) {
        listaReparti.add(repartoLoader);
    }


    public void printImpiegato_userOutput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        for (int i = 0; i < listaImpiegati.size(); i++) {
            outln.println("\nCodice Fiscale Impiegato : " + listaImpiegati.get(i).getCF());
            outln.println("Nome Impiegato : " + listaImpiegati.get(i).getNome());
            outln.println("Cognome Impiegato : " + listaImpiegati.get(i).getCognome());
        }
    }

    public void printReparto_userOutput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        for (int i = 0; i < listaReparti.size(); i++) {
            outln.println("\nDenominazione Reparto : " + listaReparti.get(i).getDenominazione());
            outln.println("Nome Responsabile Reparto : " + listaReparti.get(i).getResponsabileReparto());
            for (int j = 0; j < listaReparti.get(i).getListaImpiegati().size(); j++) {
                outln.println("\n\tImpiegati che lavorano in " + listaReparti.get(i).getDenominazione());
                outln.println("\n\tCodice Fiscale Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getCF());
                outln.println("\n\tNome Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getNome());
                outln.println("\n\tCognome Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getCognome());
            }
        }
    }

    public void printListaReparti_userOutput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        for (int i = 0; i < listaReparti.size(); i++) {
            outln.println("\nDenominazione Reparto : " + listaReparti.get(i).getDenominazione());
            outln.println("Nome Responsabile Reparto : " + listaReparti.get(i).getResponsabileReparto());
        }
    }

    public void printReparto_onFile(FileWriter fileWriter) throws IOException {
        fileWriter.write("\n\n-----------------------------------------------------------");
        fileWriter.write("\nLista Reparti in " + this.nomeNegozio);
        for (int i = 0; i < listaReparti.size(); i++) {
            fileWriter.write("\n\n\tDenominazione Reparto : " + listaReparti.get(i).getDenominazione());
            fileWriter.write("\n\tNome Responsabile Reparto : " + listaReparti.get(i).getResponsabileReparto());
            for (int j = 0; j < listaReparti.get(i).getListaImpiegati().size(); j++) {
                fileWriter.write("\n\t\tImpiegati che lavorano in " + listaReparti.get(i).getDenominazione());
                fileWriter.write("\n\t\tCodice Fiscale Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getCF());
                fileWriter.write("\n\t\tNome Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getNome());
                fileWriter.write("\n\t\tCognome Impiegato : " + listaReparti.get(i).getListaImpiegati().get(j).getCognome());
            }
        }
    }

    public void printImpiegato_onFile(FileWriter fileWriter) throws IOException {
        fileWriter.write("-----------------------------------------------------------");
        fileWriter.write("\nLista Impiegati che lavorano in " + this.nomeNegozio);
        for (int i = 0; i < listaImpiegati.size(); i++) {
            fileWriter.write("\n\n\tCodice Fiscale Impiegato : " + listaImpiegati.get(i).getCF());
            fileWriter.write("\n\tNome Impiegato : " + listaImpiegati.get(i).getNome());
            fileWriter.write("\n\tCognome Impiegato : " + listaImpiegati.get(i).getCognome());
        }
    }
}