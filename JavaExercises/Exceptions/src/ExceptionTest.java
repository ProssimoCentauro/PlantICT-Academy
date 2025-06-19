public class ExceptionTest {
    public static void main(String[] args) {
        final String RESET = "\u001B[0m";

        final String RED = "\u001B[1;31m";
        final String GREEN = "\u001B[1;32m";
        final String YELLOW = "\u001B[1;33m";
        final String BLUE = "\u001B[1;34m";

        int x = 5, y = 1, z;

        try {
            z = x / y;
            System.out.println(GREEN + "All good! \n" + RESET + "z: " + z);
        } catch (ArithmeticException e) {
            System.out.println(RED + "ArithmeticException, DIVISION BY ZERO!" + RESET);
        }
        try {
            z = x / --y;
            System.out.println(GREEN + "All good! \n" + RESET + "z: " + z);
        } catch (ArithmeticException e) {
            System.out.println(RED + "ArithmeticException, DIVIDED BY ZERO!" + RESET);
        }
    }
}
