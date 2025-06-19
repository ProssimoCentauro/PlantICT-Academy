package test.biblioteca;

import test.biblioteca.exceptions.MaterialNotAvilableException;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaTest {

    public static void tryLoan(Libro book) {
        try {
            book.loan();
        }
        catch (MaterialNotAvilableException e) {
            System.out.println(e.getMessage() + book.getTitolo());
        }
    }
    public static void tryLoan(Dvd dvd) {
        try {
            dvd.loan();
        }
        catch (MaterialNotAvilableException e) {
            System.out.println(e.getMessage() + dvd.getTitolo());
        }
    }


    public static void main(String[] args) {

        //CREATING ALL MATERIALS FROM SUPERCLASS MaterialeBiblioteca
        Dvd dvd1 = new Dvd("Pulp Fiction", 11, 1994, 154,"Quentin Tarantino");
        Libro libro1 = new Libro("Il Piccolo Principe", 21, 1943, "Antoine de Saint-Exupéry", 120, Genre.NOVEL);
        Rivista rivista1 = new Rivista("Play Boy", 31, 2015, 780, "gennaio");
        Dvd dvd2 = new Dvd("Avengers: End Game", 12, 2019, 180,"Anthony Russo");
        Libro libro2 = new Libro("Sistemi operativi", 22, 2019, "Abraham Silberschatz", 300, Genre.SCHOOL_BOOK);
        Rivista rivista2 = new Rivista("Focus", 32, 2025, 1030, "marzo");

        //GENERIC ARCHIVE
        Archive<MaterialeBiblioteca> archive =
                new Archive<>(dvd1, libro1, rivista1, dvd2, libro2, rivista2);

        //LAMBDA EXPRESSION
        archive.getList().forEach((mb) -> {
            mb.descrizione();
            mb.descrizione(true);
            mb.descrizione(false);
            System.out.println(mb.getTitolo());
        });

        //TRYING FUNCS...
        tryLoan(libro1);
        tryLoan(libro1);
        tryLoan(dvd1);
        tryLoan(dvd1);
        libro1.unloan();
        libro2.unloan();
        dvd1.unloan();
        dvd2.unloan();

        System.out.println();

        libro1.isAvailable();
        libro2.isAvailable();
        dvd1.isAvailable();
        dvd2.isAvailable();
    }
}
