package tictactoe.ai;

import tictactoe.game.Board;

import java.util.List;

public class MediumAI extends AI {

    @Override
    public void makeMove(Board board, char symbol) {
        List<List<Integer>> moves = possibleMoves(board);

        for (List<Integer> move : moves) {
            if (isWinningMove(board, symbol, move)) {
                board.setSymbolAtPos(move.get(0), move.get(1), symbol);
                return;
            }
        }

        char opponentSymbol = symbol == 'X' ? 'O' : 'X';
        for (List<Integer> move : moves) {
            if (isWinningMove(board, opponentSymbol, move)) {
                board.setSymbolAtPos(move.get(0), move.get(1), symbol);
                return;
            }
        }

        int randomIndex = (int) (Math.random() * moves.size());
        board.setSymbolAtPos(moves.get(randomIndex).get(0), moves.get(randomIndex).get(1), symbol);
    }

    private boolean isWinningMove(Board board, char symbol, List<Integer> move) {
        board.setSymbolAtPos(move.get(0), move.get(1), symbol);
        boolean result = board.isWin(symbol);
        board.setSymbolAtPos(move.get(0), move.get(1), ' ');
        return result;
    }
}
