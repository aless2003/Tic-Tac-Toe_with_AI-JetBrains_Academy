package tictactoe.menu;

import tictactoe.game.Game;

import java.util.Scanner;

public class Menu {

    public void select() {
        Scanner scanner = new Scanner(System.in);

        String[] commandParts = {""};

        while (!commandParts[0].equals("exit")) {
            System.out.println("Input command: ");
            String command = scanner.nextLine();

            commandParts = command.split(" ");

            try {
                if (commandParts[0].equals("start")) {

                    if (commandParts.length == 3) {
                        String player1 = commandParts[1];
                        if (!player1.equals("user") && !player1.equals("easy") && !player1.equals("medium") && !player1.equals("hard")) {
                            throw new IllegalArgumentException();
                        }
                        String player2 = commandParts[2];
                        if (!player2.equals("user") && !player2.equals("easy") && !player2.equals("medium") && !player2.equals("hard")) {
                            throw new IllegalArgumentException();
                        }
                        Game game = new Game(player1, player2);
                        game.play();
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("Bad parameters!");
            }
        }

    }

}
