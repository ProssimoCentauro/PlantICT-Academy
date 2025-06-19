package test.biblioteca;

public class Rivista extends MaterialeBiblioteca{
    int numero;
    String mesePubblicazione;

    Rivista() {};
    Rivista(String titolo, int codice, int annoPubblicazione, int numero, String mesePubblicazione) {
        super(titolo, codice, annoPubblicazione);
        this.numero = numero;
        this.mesePubblicazione = mesePubblicazione;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setMesePubblicazione(String mesePubblicazione) {
        this.mesePubblicazione = mesePubblicazione;
    }

    public String getNome() {
        return mesePubblicazione;
    }
    public int getNumero() {
        return numero;
    }

    @Override
    public void descrizione() {
        System.out.println(YELLOW + "INFO RIVISTA" + RESET);
        super.descrizione();
        System.out.println("Rivista numero: " + numero);
        System.out.println("mese di pubblicazione: " + mesePubblicazione);
        System.out.println("Una bella rivista di carta. Io odio i pdf!");
    }

    public void descrizione(boolean professionalDetails) {
        if (professionalDetails) {
            System.out.println(GREEN + "PROFESSIONAL DETAILS" + RESET);
            System.out.println(YELLOW + "COPYRIGHT: " + RESET + "2024 Feltrinelli Authority");
           // System.out.println(YELLOW + "IN STOCK: " + RESET + "60 pezzi");
            System.out.println(YELLOW + "PREVENDITA: " + RESET + "Non dispinibile");
        }
        else {
            System.out.print(YELLOW + "PUOI TROVARCI IN: " + RESET);
            System.out.println("Via tal dei tali 42");
        }
    }
}
