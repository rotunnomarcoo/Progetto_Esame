package GestioneNegozio;

public class Prodotto {
    private String Codice;
    private String Denominazione;
    private String Produttore;
    private int ID_Prodotto;
    private Float Prezzo;

     Prodotto(String _codice, String _denominazione, String _produttore, int _idProd, Float _prezzo) {
        Codice = _codice;
        Denominazione = _denominazione;
        Produttore = _produttore;
        ID_Prodotto = _idProd;
        Prezzo = _prezzo;
    }

    Prodotto() {

    }

    public String getCodice() {
        return Codice;
    }

    public void setCodice(String _codice) {
        Codice = _codice;
    }

    public String getDenominazione() {
        return Denominazione;
    }

    public void setDenominazione(String _denominazione) {
        Denominazione = _denominazione;
    }

    public String getProduttore() {
        return Produttore;
    }

    public void setProduttore(String _produttore) {
        Produttore = _produttore;
    }

    public int getID_Prodotto() {
        return ID_Prodotto;
    }

    public void setID_Prodotto(int _ID_Prodotto) {
        ID_Prodotto = _ID_Prodotto;
    }

    public Float getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(Float _prezzo) {
        Prezzo = _prezzo;
    }
}
