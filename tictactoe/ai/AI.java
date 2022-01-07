package tictactoe.ai;

import tictactoe.game.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class AI {

    public abstract void makeMove(Board board, char symbol);
    public List<List<Integer>> possibleMoves(Board board) {
        List<List<Integer>> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbolOnPos(i, j) == ' ') {
                    List<Integer> move = new ArrayList<>();
                    move.add(i);
                    move.add(j);
                    moves.add(move);
                }
            }
        }
        return moves;
    }
}
