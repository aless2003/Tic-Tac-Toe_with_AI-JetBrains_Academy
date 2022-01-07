package tictactoe.ai;

import tictactoe.game.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyAI extends AI {
    Random random = new Random();

    @Override
    public void makeMove(Board board, char symbol) {
        System.out.println("Making move level \"easy\"");

        List<List<Integer>> moves = possibleMoves(board);
        int turnIndex = random.nextInt(moves.size());

        List<Integer> move = moves.get(turnIndex);

        board.setSymbolAtPos(move.get(0), move.get(1), symbol);
    }

}
