import java.util.Scanner;

public class Radix {
    public static void main(String[] args) {
        final String RESET = "\u001B[0m";

        final String GREEN = "\u001B[1;32m";
        final String YELLOW = "\u001B[1;33m";

        Scanner input = new Scanner(System.in);
        System.out.print(YELLOW + "INSERT A NUMBER: " + RESET);
        double num = input.nextDouble();
        System.out.println(YELLOW + "Square root of "
                    + GREEN + num + YELLOW + ": " + RESET + Math.sqrt(num));
    }
}