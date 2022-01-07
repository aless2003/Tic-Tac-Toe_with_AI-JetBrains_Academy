package tictactoe.ai;

import tictactoe.game.Board;

import java.util.List;

public class HardAI extends AI {
    @Override
    public void makeMove(Board board, char symbol) {
        //make a move using the minimax algorithm
        System.out.println("Making move level \"hard\"");
        var moves = possibleMoves(board);
        int bestScore = Integer.MIN_VALUE;
        List<Integer> bestMove = null;

        Board newBoard = board.clone();
        for (List<Integer> move : moves) {
            newBoard.setSymbolAtPos(move.get(0), move.get(1), symbol);
            int score = minimax(newBoard, getOpponent(symbol), 0, symbol);
            newBoard.setSymbolAtPos(move.get(0), move.get(1), ' ');
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        assert bestMove != null;
        board.setSymbolAtPos(bestMove.get(0), bestMove.get(1), symbol);
    }

    private int minimax(Board board, char symbol, int depth, char startPlayer) {
        //minimax algorithm to find the best move
        Board newBoard = board.clone();

        if (newBoard.isWin(startPlayer)) {
            return 10 - depth;
        } else if (newBoard.isWin(getOpponent(startPlayer))) {
            return depth - 10;
        } else if (newBoard.isDraw()) {
            return 0;
        } else {
            int bestScore;
            var moves = possibleMoves(newBoard);
            if (depth % 2 == 1) {
                //maximizing player
                bestScore = Integer.MIN_VALUE;
                for (List<Integer> move : moves) {
                    newBoard.setSymbolAtPos(move.get(0), move.get(1), symbol);
                    int score = minimax(newBoard, getOpponent(symbol), depth + 1, startPlayer);
                    newBoard.setSymbolAtPos(move.get(0), move.get(1), ' ');
                    bestScore = Math.max(bestScore, score);
                }
            } else {
                //minimizing player
                bestScore = Integer.MAX_VALUE;
                for (List<Integer> move : moves) {
                    newBoard.setSymbolAtPos(move.get(0), move.get(1), symbol);
                    int score = minimax(newBoard, getOpponent(symbol), depth + 1, startPlayer);
                    newBoard.setSymbolAtPos(move.get(0), move.get(1), ' ');
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }

    private char getOpponent(char symbol) {
        return symbol == 'X' ? 'O' : 'X';
    }
}
