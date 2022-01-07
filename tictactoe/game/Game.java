package tictactoe.game;

import tictactoe.ai.AI;
import tictactoe.ai.EasyAI;
import tictactoe.ai.HardAI;
import tictactoe.ai.MediumAI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private static final Pattern COORDINATES_PATTERN = Pattern.compile("^([1-3]) ([1-3])$");
    private final Board board;
    private final Scanner scanner = new Scanner(System.in);
    private final String player1;
    private final String player2;
    private EasyAI easy = null;
    private MediumAI medium = null;
    private HardAI hard = null;
    public Game(String player1, String player2) {
        board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        if (player1.equals("easy") || player2.equals("easy")) {
            easy = new EasyAI();
        }
        if (player1.equals("medium") || player2.equals("medium")) {
            medium = new MediumAI();
        }
        if (player1.equals("hard") || player2.equals("hard")) {
            hard = new HardAI();
        }
    }

    public void play() {
        System.out.println(board);
        while (true) {
            char symbol = getCurrentSymbol();

            if (symbol == 'X') {
                if (playTurn(player1, symbol)) continue;
            } else {
                if (playTurn(player2, symbol)) continue;
            }

            System.out.println(board);
            if (board.isWin(symbol)) {
                System.out.println(symbol + " wins");
                break;
            }

            if (board.isDraw()) {
                System.out.println("Draw");
                break;
            }
        }
    }

    private boolean playTurn(String player, char symbol) {
        switch (player) {
            case "user":
                return !playSymbol(symbol);
            case "easy":
                easy.makeMove(board, symbol);
                break;
            case "medium":
                medium.makeMove(board, symbol);
                break;
            case "hard":
                hard.makeMove(board, symbol);
                break;
        }
        return false;
    }

    private boolean playSymbol(char symbol) {
        System.out.println("Enter the coordinates: ");
        String coordinates = scanner.nextLine();
        Matcher matcher = COORDINATES_PATTERN.matcher(coordinates);
        if (matcher.matches()) {
            int y = Integer.parseInt(matcher.group(1)) - 1;
            int x = Integer.parseInt(matcher.group(2)) - 1;
            try {
                board.setSymbolAtPos(y, x, symbol);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            }
        } else {
            if (coordinates.matches("\\d \\d")) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                System.out.println("You should enter numbers!");
            }
        }
        return false;
    }

    public char getCurrentSymbol() {
        int x = 0;
        int o = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbolOnPos(i, j) == 'X') {
                    x++;
                } else if (board.getSymbolOnPos(i, j) == 'O') {
                    o++;
                }
            }
        }

        return x == o ? 'X' : 'O';
    }
}
