package GestioneNegozio;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reparto implements Serializable {
    private String Denominazione;
    private String ResponsabileReparto;
    private List<Impiegato> listaImpiegati;
    private List<Prodotto> listaProdotti;

    Reparto(String _denominazione, String _responsabileReparto) {
        Denominazione = _denominazione;
        ResponsabileReparto = _responsabileReparto;
        listaImpiegati = new ArrayList<Impiegato>();
        listaProdotti = new ArrayList<Prodotto>();
    }

    Reparto(String _denominazione, String _responsabileReparto, List<Impiegato> _listaImpiegati, List<Prodotto> _listaProdotti) {
        Denominazione = _denominazione;
        ResponsabileReparto = _responsabileReparto;
        listaImpiegati = _listaImpiegati;
        listaProdotti = _listaProdotti;
    }

    Reparto() {
        listaImpiegati = new ArrayList<Impiegato>();
        listaProdotti = new ArrayList<Prodotto>();
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

    public void setListaImpiegati(List<Impiegato> _listaImpiegati) {
        listaImpiegati = _listaImpiegati;
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<Prodotto> _listaProdotti) {
        listaProdotti = _listaProdotti;
    }
    public void printListaImpiegati_userOutput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        outln.println("----------------------------------------------------------");
        for (int i = 0; i < listaImpiegati.size(); i++) {
            outln.println("\n\tNome Impiegato: " + listaImpiegati.get(i).getNome());
        }
    }
    public void printListaProdotti_userOutput(ConsoleOutputManager outln, ConsoleInputManager inln) {
        outln.println("----------------------------------------------------------");
        for (int i = 0; i < listaProdotti.size(); i++) {
            outln.println("\n\tCodice Prodotto: " + listaProdotti.get(i).getCodice());
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
    public void eliminaImpiegato(int index){
        listaImpiegati.remove(index);
    }
}