package GestioneNegozio;

import java.time.LocalDate;

public class Vendita {
    private LocalDate dataVendita;
    private int Qnt;
    private int ID_Prodotto;

    //TODO : Riferimento prodotto venduto
     Vendita(LocalDate _dataVendita, int _qnt, int _idProd) {
        dataVendita = _dataVendita;
        Qnt = _qnt;
        ID_Prodotto = _idProd;
    }

    Vendita() {

    }

    public LocalDate getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(LocalDate _dataVendita) {
        dataVendita = _dataVendita;
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
