package tictactoe.model.enemy.ai;

import tictactoe.model.Field;
import tictactoe.model.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class HardEnemy extends Enemy {

    public HardEnemy(Field field, char who) {
        super(field, who);
    }

    @Override
    public void nextStep() {
        Coordinate nextStep = minimax();
        action.action(nextStep.getY() + 1 + " " + (nextStep.getX() + 1));
    }

    private Coordinate minimax() {

        int size = field.getSize();
        char[][] newField = field.getField();
        Coordinate coordinate = null;
        int max = Integer.MIN_VALUE;

        for (int vertical = 0; vertical < size; vertical++) {
            for (int horizontal = 0; horizontal < size; horizontal++) {
                if (newField[vertical][horizontal] == Field.WHITE_SPACE) {
                    newField[vertical][horizontal] = who;
                    int result = minimax(new Field(newField), whoEnemy(who));
                    newField[vertical][horizontal] = Field.WHITE_SPACE;

                    if (result > max) {
                        max = result;
                        coordinate = new Coordinate(vertical, horizontal);
                    }

                }
            }
        }

        return coordinate;
    }

    private int minimax(Field field, char who) {

        String situation = field.getSituation();

        if (situation.equals(getWinString(whoEnemy(this.who)))) {
            return -10;
        } else {
            if (situation.equals(getWinString(this.who))) {
                return 10;
            } else {
                if (situation.equals(Field.Situation.DRAW))
                    return 0;
            }
        }

        char[][] newField = field.getField();
        int size = field.getSize();
        List<Integer> results = new ArrayList<>();

        for (int vertical = 0; vertical < size; vertical++) {
            for (int horizontal = 0; horizontal < size; horizontal++) {
                if (newField[vertical][horizontal] == Field.WHITE_SPACE) {
                    newField[vertical][horizontal] = who;
                    results.add(minimax(new Field(newField), whoEnemy(who)));
                    newField[vertical][horizontal] = Field.WHITE_SPACE;
                }
            }
        }

        if (who == this.who) {

            int max = Integer.MIN_VALUE;

            for (int i : results) {
                if (i > max) {
                    max = i;
                }
            }

            return max;
        } else {

            int min = Integer.MAX_VALUE;

            for (int i : results) {
                if (i < min) {
                    min = i;
                }
            }

            return min;
        }
    }

    private String getWinString(char who) {
        return (who == Field.X) ? Field.Situation.X_WINS : Field.Situation.O_WINS;
    }

    private static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

}
