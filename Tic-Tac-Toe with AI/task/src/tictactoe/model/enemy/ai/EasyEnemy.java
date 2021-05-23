package tictactoe.model.enemy.ai;

import tictactoe.model.Field;
import tictactoe.model.enemy.Enemy;

import java.util.Random;

public class EasyEnemy extends Enemy {

    protected final int bound;

    public EasyEnemy(Field field, char who) {
        super(field, who);
        bound = field.getSize() * field.getSize();
    }

    @Override
    public void nextStep() {
        int x;
        int y;

        while (true) {
            try {
                Random random = new Random();
                x = random.nextInt(bound);
                y = random.nextInt(bound);

                action.action(y + " " + x);
                System.out.println("Making move level \"easy\"");
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }

    }
}
