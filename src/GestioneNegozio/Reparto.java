package GestioneNegozio;

import java.util.ArrayList;
import java.util.List;

public class Reparto {
    private String Denominazione;
    private String ResponsabileReparto;
    private List<Impiegato> listaImpiegati;
    private List<Prodotto> listaProdotti;

    Reparto(String _denominazione, String _responsabileReparto) {
        Denominazione = _denominazione;
        ResponsabileReparto = _responsabileReparto;
        listaImpiegati = new ArrayList<>();
        listaProdotti = new ArrayList<>();
    }

    Reparto(String _denominazione, String _responsabileReparto, List<Impiegato> _listaImpiegati, List<Prodotto> _listaProdotti) {
        Denominazione = _denominazione;
        ResponsabileReparto = _responsabileReparto;
        listaImpiegati = _listaImpiegati;
        listaProdotti = _listaProdotti;
    }

    Reparto() {

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
}