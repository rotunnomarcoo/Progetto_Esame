package GestioneNegozio;

import java.io.Serializable;
import java.time.LocalDate;

public class Ordine implements Serializable {
    private LocalDate dataOrdine;
    private int Qnt;
    private Prodotto prodottoVenduto;

    Ordine() {
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(String _dataOrdine) {
        boolean stringMatch = _dataOrdine.matches("\\d{4}\\-\\d{2}\\-\\d{2}");
        if (stringMatch)
            dataOrdine = LocalDate.parse(_dataOrdine);

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
