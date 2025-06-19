import java.util.Random;

public class ContinueUntil {
    public static void main(String[] args) {
        Random rand = new Random();
        while (true) {
            int num = rand.nextInt(15) + 1;
            System.out.println(num);
            if (num % 3 == 0)
                break;
        }
    }
}
