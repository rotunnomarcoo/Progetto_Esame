package GestioneNegozio;

import java.io.Serializable;

public class Impiegato implements Serializable {
    private String Nome;
    private String CF;
    private String Cognome;

    Impiegato(String _cf, String _nome, String _cognome) {
        CF = _cf;
        Nome = _nome;
        Cognome = _cognome;
    }

    Impiegato() {
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
}