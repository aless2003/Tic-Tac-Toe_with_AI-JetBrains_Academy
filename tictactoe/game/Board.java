package tictactoe.game;

public class Board implements Cloneable{
    private Field[][] fields;

    public Board() {
        fields = new Field[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    private void setFields(Field[][] fields) {
        this.fields = new Field[fields.length][fields[0].length];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.fields[i][j] = fields[i][j].clone();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (int i = 0; i < 3; i++) {
            sb.append("| ");
            for (int j = 0; j < 3; j++) {
                sb.append(fields[i][j].getSymbol()).append(" ");
            }
            sb.append("|\n");
        }
        sb.append("---------");
        return sb.toString();
    }

    public void setSymbolAtPos(int y, int x, char symbol) throws IllegalArgumentException {
        if (fields[y][x].getSymbol() != ' ' && symbol != ' ') {
            throw new IllegalArgumentException("Field is already taken");
        }

        fields[y][x].setSymbol(symbol);
    }

    public boolean isWin(char symbol) {
        return isWinHorizontal(symbol) || isWinVertical(symbol) || isWinDiagonal(symbol);
    }

    private boolean isWinHorizontal(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (fields[i][0].getSymbol() == symbol && fields[i][1].getSymbol() == symbol && fields[i][2].getSymbol() == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinVertical(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (fields[0][i].getSymbol() == symbol && fields[1][i].getSymbol() == symbol && fields[2][i].getSymbol() == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinDiagonal(char symbol) {
        if (fields[0][0].getSymbol() == symbol && fields[1][1].getSymbol() == symbol && fields[2][2].getSymbol() == symbol) {
            return true;
        }
        return fields[0][2].getSymbol() == symbol && fields[1][1].getSymbol() == symbol && fields[2][0].getSymbol() == symbol;
    }

    public boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fields[i][j].getSymbol() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getSymbolOnPos(int y, int x) {
        return fields[y][x].getSymbol();
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            clone.setFields(fields);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
