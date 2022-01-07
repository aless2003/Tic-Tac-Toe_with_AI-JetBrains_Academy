package tictactoe.game;

public class Field implements Cloneable {
    char symbol = ' ';

    public void setSymbol(char charAt) {
        this.symbol = charAt;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public Field clone() {
        try {
            return (Field) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
