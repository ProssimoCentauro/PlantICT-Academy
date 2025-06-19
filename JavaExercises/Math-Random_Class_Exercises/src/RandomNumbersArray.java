import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumbersArray {
    public static void main(String[] args) {
        Random rand = new Random();
        List<Integer> arr = new ArrayList<>();
        System.out.println("ARRAY INITIALIZED!");
        System.out.println("Setting up random numbers...");
        for (int i = 0; i < 10; i++) {
            arr.add(rand.nextInt(41) + (-20));
        }
        System.out.println("PRINTING ARRAY...");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
