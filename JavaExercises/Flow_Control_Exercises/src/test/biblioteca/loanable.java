package test.biblioteca;

import test.biblioteca.exceptions.MaterialNotAvilableException;

public interface loanable {
    void loan() throws MaterialNotAvilableException;
    void unloan();
    void isAvailable();
}
