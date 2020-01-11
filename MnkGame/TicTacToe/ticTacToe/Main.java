package ticTacToe;

import javax.sound.midi.Sequence;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */

public class Main {
    public static void main(String[] args) {

        final GameServer server = new GameServer(true, new SequencePlayer(), new SequencePlayer());
        server.play(new Board(5,5,5));
    }
}
