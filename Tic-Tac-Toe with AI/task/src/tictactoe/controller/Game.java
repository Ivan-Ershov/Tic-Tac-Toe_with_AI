package tictactoe.controller;

import tictactoe.model.Field;
import tictactoe.model.enemy.Enemy;
import tictactoe.view.Drawable;

public class Game {

    private final Field field;
    private final Enemy first;
    private final Enemy second;
    private final Drawable drawable;

    public Game(Field field, Enemy first, Enemy second, Drawable drawable) {
        this.field = field;
        this.first = first;
        this.second = second;
        this.drawable = drawable;
    }

    public void start() {
        boolean firstIsMove = false;
        drawable.draw(field);

        while (field.getSituation().equals(Field.Situation.GAME_NOT_FINISHED)) {
            try {
                if (firstIsMove) {
                    second.nextStep();
                    drawable.draw(field);
                    firstIsMove = false;
                } else {
                    first.nextStep();
                    drawable.draw(field);
                    firstIsMove = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("You should enter numbers!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.println(field.getSituation());

    }

}
