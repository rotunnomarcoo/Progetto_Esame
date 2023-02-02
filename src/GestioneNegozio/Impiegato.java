package GestioneNegozio;

import java.io.Serializable;
import java.util.ArrayList;

public class Impiegato implements Comparable<Impiegato>, Serializable {
    private String Nome;
    private String CF;
    private String Cognome;
    private ArrayList<Reparto> listaReparti;

    Impiegato() {
        listaReparti = new ArrayList<>();
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String _nome) {
        Nome = _nome;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String _cf) {
        CF = _cf;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String _cognome) {
        Cognome = _cognome;
    }

    public ArrayList<Reparto> getListaReparti() {
        return listaReparti;
    }

    public void setListaReparti(ArrayList<Reparto> listaReparti) {
        this.listaReparti = listaReparti;
    }

    @Override
    public int compareTo(Impiegato impiegato) {
        int lastCmp = getCognome().compareTo(impiegato.getCognome());
        return (lastCmp != 0 ? lastCmp : getCognome().compareTo(impiegato.getCognome()));
    }

}