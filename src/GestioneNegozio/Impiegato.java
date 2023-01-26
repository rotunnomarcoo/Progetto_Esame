package GestioneNegozio;

import java.io.Serializable;

public class Impiegato implements Serializable {
    private String Nome;
    private String CF;
    private String Cognome;

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