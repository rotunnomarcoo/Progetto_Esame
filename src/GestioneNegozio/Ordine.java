package GestioneNegozio;

import java.time.LocalDate;

public class Ordine {
    private LocalDate dataOrdine;
    private int Qnt;
    private int ID_Prodotto;

    //TODO : Riferimento prodotto ordinato
    Ordine(LocalDate _dataOrdine, int _qnt, int _idProd) {
        dataOrdine = _dataOrdine;
        Qnt = _qnt;
        ID_Prodotto = _idProd;
    }

    Ordine() {
    }

    public LocalDate getdataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate _dataOrdine) {
        dataOrdine = _dataOrdine;
    }

    public int getQnt() {
        return Qnt;
    }

    public void setQnt(int _qnt) {
        Qnt = _qnt;
    }

    public int getID_Prodotto() {
        return ID_Prodotto;
    }

    public void setID_Prodotto(int _idprodotto) {
        ID_Prodotto = _idprodotto;
    }

}
