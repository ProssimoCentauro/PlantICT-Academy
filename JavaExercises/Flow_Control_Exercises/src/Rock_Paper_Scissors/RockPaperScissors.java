import java.util.Scanner;
import java.util.HashMap;

public class RockPaperScissors {
    public static void main(String[] args) {
        final String RESET = "\u001B[0m";

        final String RED = "\u001B[1;31m";
        final String GREEN = "\u001B[1;32m";
        final String YELLOW = "\u001B[1;33m";
        final String BLUE = "\u001B[1;34m";

		String p1Str;
		String p2Str;
		int p1Choice;
		int p2Choice;

        Scanner input = new Scanner(System.in);
		
		HashMap<String, Integer> choices = new HashMap<>();
		choices.put("rock", 0);
        choices.put("paper", 1);
        choices.put("scissors", 2);
        
		int[][] results = {
            //     R  P  S
            /*R*/ {0, 2, 1},
            /*P*/ {1, 0, 2},
            /*S*/ {2, 1, 0}
        };
	
		while (true) {
            System.out.println(YELLOW + "Player 1 type one of the following:" + RESET);
            System.out.println("rock, paper, scissors");
            p1Str = input.nextLine().toLowerCase();
            if (!choices.containsKey(p1Str)) {
				System.out.println(RED + "\nINVALID INPUT!\n" + RESET);
				continue;
            }
			else {
				p1Choice = choices.get(p1Str);
			}
			break;
        }
		while (true) {
            System.out.println(YELLOW + "Player 2 type one of the following:" + RESET);
            System.out.println("rock, paper, scissors");
            p2Str = input.nextLine().toLowerCase();
            if (!choices.containsKey(p2Str)) {
				System.out.println(RED + "\nINVALID INPUT!\n" + RESET);
				continue;
			}
			else {
				p2Choice = choices.get(p2Str);
			}
			break;
        }

		int	result = results[p1Choice][p2Choice];
		if (result == 1)
			System.out.println(GREEN + "Player1 win!" + RESET + " p1: " + p1Str + " p2: " + p2Str);
		else if (result == 2)
			System.out.println(GREEN + "Player2 win!" + RESET + " p1: " + p1Str + " p2: " + p2Str);
		else	
			System.out.println(YELLOW + "It's a tie!" + RESET + " p1: " + p1Str + " p2: " + p2Str);
    }
}
