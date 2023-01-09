package GestioneNegozio;

import java.util.List;

public class GestioneNegozio {
    private String nomeNegozio;
    private List<Impiegato> listaImpiegati;
    private List<Reparto> listaReparti;
    private List<Prodotto> listaProdotti;
    private List<Vendita> listaVendite;
    private List<Ordine> listaOrdini;

    GestioneNegozio(String _nomeNegozio, List<Impiegato> _listaImpiegati, List<Reparto> _listaReparti,
                    List<Prodotto> _listaProdotti, List<Vendita> _listaVendite, List<Ordine> _listaOrdini) {
        nomeNegozio = _nomeNegozio;
        listaImpiegati = _listaImpiegati;
        listaReparti = _listaReparti;
        listaProdotti = _listaProdotti;
        listaVendite = _listaVendite;
        listaOrdini = _listaOrdini;
    }

    GestioneNegozio(String _nomeNegozio) {
        nomeNegozio = _nomeNegozio;
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
}