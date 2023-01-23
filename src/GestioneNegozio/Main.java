package GestioneNegozio;

import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Output console
        ConsoleOutputManager outln = new ConsoleOutputManager();
        //Input console utente
        ConsoleInputManager inln = new ConsoleInputManager();
        //Creazione txt
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter fileWriter = new FileWriter("negozio.txt");
        //Creazione negozio
        GestioneNegozio managerNegozio = new GestioneNegozio("Asus Rog Inc.");
        Reparto repartoLoader = new Reparto("Schede Video", "Rossi Franco");
        managerNegozio.caricaRepartoInList(repartoLoader);

        String loadAnother="s";
        while(loadAnother.equalsIgnoreCase("s")){
            managerNegozio.caricaReparto_userInput(outln, inln);
            outln.println("Vuoi caricare un altro reparto? \n S / N");
            loadAnother= inln.readLine();
        }
        loadAnother="s";
        Impiegato impiegatoLoader = new Impiegato("RTNMRC01P12I452D", "Marco", "Rotunno");
        managerNegozio.caricaImpiegatoInList(impiegatoLoader);
        while(loadAnother.equalsIgnoreCase("s")) {
            //listaImpiegati per caricare nel negozio
            managerNegozio.caricaImpiegato_userInput(outln, inln);
            outln.println("Vuoi caricare un altro impiegato? \n S / N");
            loadAnother= inln.readLine();
        }


        managerNegozio.printOnFile(fileWriter);
        managerNegozio.printUserOutput(outln,inln);
/*        Prodotto prodottoLoader = new Prodotto("21", "gpu", "ASUS", 1, 300f);
        managerNegozio.caricaProdotto_userInput(outln,inln);
        managerNegozio.caricaProdottoInList(prodottoLoader);*/
        fileWriter.close();
    }
}