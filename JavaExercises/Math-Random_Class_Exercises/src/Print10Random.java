import java.util.Random;

public class Print10Random {
    public static void main(String[] args) {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(rand.nextInt(10) + 1);
        }
    }
}
