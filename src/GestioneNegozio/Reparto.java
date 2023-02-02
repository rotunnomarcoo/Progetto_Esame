package GestioneNegozio;

import prog.io.ConsoleOutputManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Reparto implements Serializable {
    private String Denominazione;
    private String ResponsabileReparto;
    private final List<Impiegato> listaImpiegati;
    private final List<Prodotto> listaProdotti;

    Reparto() {
        listaImpiegati = new ArrayList<>();
        listaProdotti = new ArrayList<>();
    }

    public String getDenominazione() {
        return Denominazione;
    }

    public void setDenominazione(String _denominazione) {
        Denominazione = _denominazione;
    }

    public String getResponsabileReparto() {
        return ResponsabileReparto;
    }

    public void setResponsabileReparto(String _responsabileReparto) {
        ResponsabileReparto = _responsabileReparto;
    }

    public List<Impiegato> getListaImpiegati() {
        return listaImpiegati;
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void printListaImpiegati_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        for (Impiegato impiegato : listaImpiegati) {
            consoleOut.println("\n\tNome Impiegato: " + impiegato.getNome());
        }
    }

    public void printListaProdotti_userOutput(ConsoleOutputManager consoleOut) {
        consoleOut.println("----------------------------------------------------------");
        for (Prodotto prodotto : listaProdotti) {
            consoleOut.println("\n\tCodice Prodotto: " + prodotto.getCodice());
        }
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
        for (int i = 0; i < listaImpiegati.size(); i++) {
            if (listaProdotti.get(i).getCodice().equalsIgnoreCase(prodottoCercato)) {
                return i;
            }
        }
        return -1;
    }

}