package test.biblioteca;

public abstract class MaterialeBiblioteca {
    final String RESET = "\u001B[0m";

    final String RED = "\u001B[1;31m";
    final String GREEN = "\u001B[1;32m";
    final String YELLOW = "\u001B[1;33m";
    final String BLUE = "\u001B[1;34m";

    private String titolo;
    private int codice;
    private int annoPubblicazione;


    MaterialeBiblioteca() {};
    MaterialeBiblioteca(String titolo, int codice, int annoPubblicazione) {
        this.titolo = titolo;
        this.codice = codice;
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public void setCodice(int codice) {
        this.codice = codice;
    }
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getTitolo() {
        return titolo;
    }
    public int getCodice() {
        return codice;
    }
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void descrizione() {
        //System.out.println("|INFO MATERIALE BIBLIOTECA|");
        System.out.println("titolo: " + this.titolo);
        System.out.println("codice: " + this.codice);
        System.out.println("annoPubblicazione: " + this.annoPubblicazione);
    }



    public abstract void descrizione(boolean professionalDetails);
}
