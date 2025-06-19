package test.biblioteca;

import test.biblioteca.exceptions.MaterialNotAvilableException;

public class Libro extends MaterialeBiblioteca {
    private String autore;
    private int numeroPagine;
    private boolean inStock = true;
    private Genre genre;

    Libro() {};
    Libro(String titolo, int codice, int annoPubblicazione, String autore, int numeroPagine, Genre genre) {
        super(titolo, codice, annoPubblicazione);
        this.autore = autore;
        this.numeroPagine = numeroPagine;
        this.genre = genre;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public String getAutore() {
        return autore;
    }
    public int getNumeroPagine() {
        return numeroPagine;
    }
    public boolean isInStock() { return inStock; }
    public Genre getGenre() { return genre; }

    @Override
    public void descrizione() {
        System.out.println(YELLOW + "INFO LIBRO" + RESET);
        super.descrizione();
        System.out.println("Autore: " + autore);
        System.out.println("Numero Pagine: " + numeroPagine);
        System.out.println("Genre: " + genre);
        System.out.println("Top libro 10 su 10!");
    }

    public void descrizione(boolean professionalDetails) {
        if (professionalDetails) {
            System.out.println(GREEN + "PROFESSIONAL DETAILS" + RESET);
            System.out.println(YELLOW + "COPYRIGHT: " + RESET + "2025 Best Books Authority");
            System.out.println(YELLOW + "IN STOCK: " + RESET + this.inStock);
            System.out.println(YELLOW + "PREVENDITA: " + RESET + "Non dispinibile");
        }
        else {
            System.out.print(YELLOW + "PUOI TROVARCI IN: " + RESET);
            System.out.println("Via tal dei tali 42");
        }
    }

    public void isAvailable() {
        if (this.inStock)
            System.out.println(GREEN + "LIBRO DISPONIBILE: " + RESET + getTitolo());
        else
            System.out.println(RED + "LIBRO NON DISPONIBILE: " + RESET + getTitolo());
    }

    public void loan() throws MaterialNotAvilableException {
        if (inStock) {
            inStock = false;
            System.out.println(GREEN + "HAI PRESO QUESTO LIBRO: " + RESET + getTitolo());
        }
        else {
            //inStock = false;
            throw new MaterialNotAvilableException(RED + "EXCEPTION LIBRO NON DISPONIBILE: " + RESET);
            //System.out.println(RED + "DVD NON DISPONIBILE! " + RESET + getTitolo());
        }
    }

    public void unloan() {
        if (!inStock) {
            System.out.println(GREEN + "HAI RESTITUITO QUESTO LIBRO: " + RESET + getTitolo());
            inStock = true;
        }
        else {
            System.out.println(RED + "NON PUOI RESTITUIRE QUESTO LIBRO: " + RESET + getTitolo());
        }
    }

}
