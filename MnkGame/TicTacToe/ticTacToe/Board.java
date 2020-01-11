package ticTacToe;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Board implements PlayerBoard, ServerBoard {
    private static final Map<Cell, String> SYMBOLS = new EnumMap<>(Map.of(
            Cell.E, " .",
            Cell.X, " X",
            Cell.O, " O"
    ));

    private final Cell[][] board;
    private Cell turn;
    private final int k;

    public Board(final int m, final int n, final int k) {
        board = new Cell[m][n];
        this.k = k;
        for (final Cell[] row : board) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public int getWidth() {
        return board[0].length;
    }

    @Override
    public int getHeight() {
        return board.length;
    }

    @Override
    public Cell getCell(final int row, final int column) {
        return board[row][column];
    }

    @Override
    public boolean isValidMove(final Move move, final Cell cell) {
        return checkBounds(move) && getCell(move.getRow(), move.getColumn()) == Cell.E;
    }

    private boolean checkBounds(final Move move) {
        final int row = move.getRow();
        final int col = move.getColumn();
        return 0 <= row && row < board.length && 0 <= col && col < board[col].length;
    }

    @Override
    public PlayerBoard getPlayerBoard() {
        return this;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Result move(final Move move) {
        board[move.getRow()][move.getColumn()] = turn;
        int d1 = 0;
        int d2 = 0;
        int empty = 0;
        for (int u = 0; u < k; u++) {
            int c1 = 0;
            int c2 = 0;
            for (int v = 0; v < k; v++) {
                if (board[u][v] == turn) {
                    c1++;
                }
                if (board[v][u] == turn) {
                    c2++;
                }
                if (board[u][v] == Cell.E) {
                    empty++;
                }
            }
            if (c1 == k || c2 == k) {
                return Result.WIN;
            }
            if (board[u][u] == turn) {
                d1++;
            }
            if (board[u][2 - u] == turn) {
                d2++;
            }
        }
        if (d1 == 3 || d2 == 3) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNDEFINED;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < board[0].length; i++) {
            sb.append(i);
            sb.append(' ');
        }
        sb.append('\n');
        for (int r = 0; r < board.length; r++) {
            sb.append(r);
            for (int c = 0; c < board[0].length; c++) {
                sb.append(SYMBOLS.get(board[r][c]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
