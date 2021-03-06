package MNK;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 3, m = 3, k = 3;
        List<Player> players = List.of(new SequentialPlayer(), new SequentialPlayer(), new SequentialPlayer());
        int playerCount = players.length;
        for (int testCount = 0; testCount < 10; testCount++) {
            final Game game = new Game(true, players);
            int result = game.play(new NMKBoard(n, m, k, playerCount));
            System.out.println("Game number: " + testCount);
            if (result >= 0) {
                System.out.println("Player: " + (result + 1) + " won");
            } else {
                System.out.println("Draw!");
            }
            System.out.println("Result: " + (result + 1));
        }
    }
}
