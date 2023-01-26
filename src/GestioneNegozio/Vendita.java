package GestioneNegozio;

import java.io.Serializable;
import java.time.LocalDate;


public class Vendita implements Serializable {
    private LocalDate dataVendita;
    private int Qnt;
    private Prodotto prodottoVenduto;

    Vendita() {
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public LocalDate getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(String _dataVendita) {
        boolean stringMatch = _dataVendita.matches("\\d{4}\\-\\d{2}\\-\\d{2}");
        if (stringMatch)
            dataVendita = LocalDate.parse(_dataVendita);

    }

    public int getQnt() {
        return Qnt;
    }

    public void setQnt(String _qnt) {
        if (isNumeric(_qnt))
            Qnt = Integer.parseInt(_qnt);
    }

    public void setProdottoVenduto(Prodotto prodVenduto) {
        prodottoVenduto = prodVenduto;
    }

    public void setEmptyProdottoVenduto() {
        prodottoVenduto = new Prodotto();
    }

    public Prodotto getProdottoVenduto() {
        if (prodottoVenduto != null) {
            return prodottoVenduto;
        } else {
            return new Prodotto();
        }
    }

}
