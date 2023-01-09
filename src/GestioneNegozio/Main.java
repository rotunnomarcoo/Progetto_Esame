package GestioneNegozio;

import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Output console
        ConsoleOutputManager outln = new ConsoleOutputManager();
        //Input console utente
        ConsoleInputManager inln = new ConsoleInputManager();
        //Creazione negozio
        GestioneNegozio MSI = new GestioneNegozio("MSI");
        //listaImpiegati per caricare nel negozio
        List<Impiegato> listaImpiegati = new ArrayList<Impiegato>();
        Impiegato impiegatoLoader = new Impiegato("RTNMRC01P12I452D", "Marco", "Rotunno");
        listaImpiegati.add(impiegatoLoader);
        for (int i = 0; i < 2; i++) {
            outln.println("\nCodice Fiscale Impiegato : ");
            impiegatoLoader.setCF(inln.readLine());
            outln.println("Nome Impiegato : ");
            impiegatoLoader.setNome(inln.readLine());
            outln.println("Cognome Impiegato : ");
            impiegatoLoader.setCognome(inln.readLine());
            listaImpiegati.add(impiegatoLoader);
            MSI.setListaImpiegati(listaImpiegati);
        }
        for (int i = 0; i < MSI.getListaImpiegati().size(); i++) {
            outln.println("\nCodice Fiscale Impiegato : " + MSI.getListaImpiegati().get(i).getCF());
            outln.println("Nome Impiegato : " + MSI.getListaImpiegati().get(i).getNome());
            outln.println("Cognome Impiegato : " + MSI.getListaImpiegati().get(i).getCognome());
        }
        List<Prodotto> listaProdotto= new ArrayList<Prodotto>();
        Prodotto prodottoLoader = new Prodotto("21","gpu","ASUS",1,300f);
        listaProdotto.add(prodottoLoader);
        List<Reparto> listaReparto= new ArrayList<Reparto>();


    }
}