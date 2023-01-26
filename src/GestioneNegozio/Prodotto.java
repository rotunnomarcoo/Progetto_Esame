package GestioneNegozio;

import java.io.Serializable;

public class Prodotto implements Serializable {
    private String Codice;
    private String Denominazione;
    private String Produttore;
    private Float Prezzo;

    Prodotto() {
        Codice = "";
        Denominazione = "";
        Produttore = "";
        Prezzo = 0f;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

    public Float getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(String _prezzo) {
        if (isNumeric(_prezzo))
            Prezzo = Float.parseFloat(_prezzo);
    }
}
