package test.biblioteca;

import test.biblioteca.exceptions.MaterialNotAvilableException;

public class Dvd extends MaterialeBiblioteca implements loanable {
    int durata;
    String regista;
    boolean inStock = true;

    Dvd() {};
    Dvd(String titolo, int codice, int annoPubblicazione, int durata, String regista) {
        super(titolo, codice, annoPubblicazione);
        this.durata = durata;
        this.regista = regista;
    }


    public void setDurata(int durata) {
        this.durata = durata;
    }
    public void setRegista(String regista) {
        this.regista = regista;
    }
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getDurata() {
        return durata;
    }
    public String getRegista() {
        return regista;
    }
    public boolean getStocks() {
        return inStock;
    }

    @Override
    public void descrizione() {
        System.out.println(YELLOW + "INFO DVD" + RESET);
        super.descrizione();
        System.out.println("Durata: " + durata + "minuti");
        System.out.println("Regista: " + regista);
        System.out.println("è il film più bello di sempre compralo ora!");
    }

    public void descrizione(boolean professionalDetails) {
        if (professionalDetails) {
            System.out.println(GREEN + "PROFESSIONAL DETAILS" + RESET);
            System.out.println(YELLOW + "COPYRIGHT: " + RESET + "2025 Epic Movies Authority");
            System.out.println(YELLOW + "IN STOCK: " + RESET + this.inStock);
            System.out.println(YELLOW + "PREVENDITA: " + RESET + "dispinibile");
        }
        else {
            System.out.print(YELLOW + "PUOI TROVARCI IN: " + RESET);
            System.out.println("Via tal dei tali 42");
        }
    }

    public void isAvailable() {
        if (this.inStock)
            System.out.println(GREEN + "DVD DISPONIBILE: " + RESET + getTitolo());
        else
            System.out.println(RED + "DVD NON DISPONIBILE: " + RESET + getTitolo());
    }

    public void loan() throws MaterialNotAvilableException {
        if (inStock) {
            inStock = false;
            System.out.println(GREEN + "HAI PRESO QUESTO DVD: " + RESET + getTitolo());
        }
        else {
            //inStock = false;
            throw new MaterialNotAvilableException(RED + "EXCEPTION DVD NON DISPONIBILE: " + RESET);
            //System.out.println(RED + "DVD NON DISPONIBILE! " + RESET + getTitolo());
        }
    }

    public void unloan() {
        if (!inStock) {
            System.out.println(GREEN + "HAI RESTITUITO QUESTO DVD: " + RESET + getTitolo());
            inStock = true;
        }
        else {
            System.out.println(RED + "NON PUOI RESTITUIRE QUESTO DVD: " + RESET + getTitolo());
        }
    }



}
