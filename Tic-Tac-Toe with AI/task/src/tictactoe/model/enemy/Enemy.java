package tictactoe.model.enemy;

import tictactoe.controller.Action;
import tictactoe.model.Field;

public abstract class Enemy {

    protected final char who;
    protected final Field field;
    protected final Action action;

    public Enemy(Field field, char who) {
        this.who = who;
        this.field = field;
        this.action = getAction(who);
    }

    private Action getAction(char who) {

        if (Field.X == who) {
            return field::moveX;
        }

        if (Field.O == who) {
            return field::moveO;
        }

        throw new IllegalArgumentException("Incorrect param who, who is Field.X or Field.O ");
    }

    protected char whoEnemy(char who) {
        return (who == Field.X) ? Field.O : Field.X;
    }

    public abstract void nextStep();
}
