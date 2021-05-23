package tictactoe.model;

public class Field {
    private static final String WHITESPACE = " ";

    public static final char WHITE_SPACE = '_';
    public static final char X = 'X';
    public static final char O = 'O';

    private final int size;
    private final char[][] field;
    private final String xWinLine;
    private final String oWinLine;

    public Field (char[][] field) {
        size = field.length;
        xWinLine = repeat(X, size);
        oWinLine = repeat(O, size);
        this.field = field;
    }

    public Field (int size) {
        this.size = size;
        this.xWinLine = repeat(X, size);
        this.oWinLine = repeat(O, size);
        this.field = new char[size][size];

        for (int vertical = 0; vertical < size; vertical++) {
            for (int horizontal = 0; horizontal < size; horizontal++) {
                field[vertical][horizontal] = WHITE_SPACE;
            }
        }

    }

    public int getSize() {
        return size;
    }

    public char[][] getField() {
        return field;
    }

    public void moveX(String input) {
        move(X, input);
    }

    public void moveO(String input) {
        move(O, input);
    }

    private void move(char symbol, String input) {
        String[] coordinates = input.split(WHITESPACE);

        if (coordinates.length != 2) {
            throw  new IllegalArgumentException("You should enter two numbers!");
        }

        int y = Integer.parseInt(coordinates[0]);
        int x = Integer.parseInt(coordinates[1]);

        if ((x < 1) || (size < x)) {
            throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
        }

        if ((y < 1) || (size < y)) {
            throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
        }

        replace(symbol,y - 1, x - 1);
    }

    private void replace(char symbol, int y, int x) {
        if (field[y][x] != WHITE_SPACE) {
            throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        }

        field[y][x] = symbol;
    }

    private String repeat(char symbol, int count) {
        return String.valueOf(symbol).repeat(count);
    }

    public String getSituation() {

        if (isWin(xWinLine)) {
            return Situation.X_WINS;
        }

        if (isWin(oWinLine)) {
            return Situation.O_WINS;
        }

        if (isNoFinish()) {
            return Situation.GAME_NOT_FINISHED;
        }

        return Situation.DRAW;
    }

    private boolean isWin(String winLine) {

        if (isHorizontalWin(winLine)) {
            return true;
        }

        if (isVerticalWin(winLine)) {
            return true;
        }

        return isDiagonalWine(winLine);
    }

    private boolean isHorizontalWin(String winLine) {
        for (int vertical = 0; vertical < size; vertical++) {
            if (String.valueOf(field[vertical]).equals(winLine)) {
                return true;
            }
        }

        return false;
    }

    private boolean isVerticalWin(String winLine) {
        for (int horizontal = 0; horizontal < size; horizontal++) {
            if (getHorizontalLine(horizontal).equals(winLine)) {
                return true;
            }
        }

        return false;
    }

    private String getHorizontalLine(int number) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size; i++) {
            output.append(field[i][number]);
        }

        return output.toString();
    }

    private boolean isDiagonalWine(String winLine) {

        if (getRightDiagonal().equals(winLine)) {
            return true;
        }

        return getLeftDiagonal().equals(winLine);
    }

    private String getRightDiagonal() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size; i++) {
            output.append(field[i][i]);
        }

        return output.toString();
    }

    private String getLeftDiagonal() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size; i++) {
            output.append(field[i][size - i - 1]);
        }

        return output.toString();
    }

    private boolean isNoFinish() {
        for (int vertical = 0; vertical < size; vertical++) {
            for (int horizontal = 0; horizontal < size; horizontal++) {
                if (field[vertical][horizontal] == WHITE_SPACE) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Situation {
        public static final String X_WINS = "X wins";
        public static final String O_WINS = "O wins";
        public static final String GAME_NOT_FINISHED = "Game not finished";
        public static final String DRAW = "Draw";
    }

}
