package tictactoe.model.enemy.ai;

import tictactoe.model.Field;

public class MediumEnemy extends EasyEnemy {

    public MediumEnemy(Field field, char who) {
        super(field, who);
    }

    @Override
    public void nextStep() {
        Coordinate coordinate;

        coordinate = isWine(who);
        if (coordinate != null) {
            action.action(coordinate.getY() + 1 + " " + (coordinate.getX() + 1));
            return;
        }

        coordinate = isWine(whoEnemy(who));
        if (coordinate != null) {
            action.action(coordinate.getY() + 1 + " " + (coordinate.getX() + 1));
            return;
        }

        super.nextStep();
    }

    private Coordinate isWine(char symbol) {
        Coordinate coordinate;

        coordinate = isHorizontalWin(symbol);
        if (coordinate != null) {
            return coordinate;
        }

        coordinate = isVerticalWin(symbol);
        if (coordinate != null) {
            return coordinate;
        }

        return isDiagonalWine(symbol);
    }

    private Coordinate isHorizontalWin(char symbol) {
        for (int vertical = 0; vertical < field.getSize(); vertical++) {
            int horizontal = winePlace(symbol, String.valueOf(field.getField()[vertical]));
            if (horizontal != -1) {
                return new Coordinate(vertical, horizontal);
            }
        }

        return null;
    }

    private Coordinate isVerticalWin(char symbol) {
        for (int horizontal = 0; horizontal < field.getSize(); horizontal++) {
            int vertical = winePlace(symbol, getHorizontalLine(horizontal));
            if (vertical != -1) {
                return new Coordinate(vertical, horizontal);
            }
        }

        return null;
    }



    private Coordinate isDiagonalWine(char symbol) {

        int coordinate = winePlace(symbol, getRightDiagonal());
        if (coordinate != -1) {
            return new Coordinate(coordinate, coordinate);
        }

        coordinate = winePlace(symbol, getLeftDiagonal());
        if (coordinate != -1) {
            return new Coordinate(coordinate, field.getSize() - coordinate - 1);
        }


        return null;
    }

    private int winePlace(char symbol, String input) {
        int whiteSpace = 0;
        int coordinate = -1;
        int countSymbol = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == symbol) {
                countSymbol++;
            }

            if (input.charAt(i) == Field.WHITE_SPACE) {
                coordinate = i;
                whiteSpace++;
            }

            if (whiteSpace > 1) {
                break;
            }

        }

        if ((whiteSpace == 1) && (countSymbol == field.getSize() - 1)) {
            return coordinate;
        }

        return -1;
    }

    private String getHorizontalLine(int number) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < field.getSize(); i++) {
            output.append(field.getField()[i][number]);
        }

        return output.toString();
    }

    private String getRightDiagonal() {
        StringBuilder output = new StringBuilder();
        int size = field.getSize();

        for (int i = 0; i < size; i++) {
            output.append(field.getField()[i][i]);
        }

        return output.toString();
    }

    private String getLeftDiagonal() {
        StringBuilder output = new StringBuilder();
        int size = field.getSize();

        for (int i = 0; i < size; i++) {
            output.append(field.getField()[i][size - i - 1]);
        }

        return output.toString();
    }

    private static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

}
