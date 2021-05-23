package tictactoe;

import tictactoe.controller.Game;
import tictactoe.model.Field;
import tictactoe.model.enemy.Enemy;
import tictactoe.model.enemy.User;
import tictactoe.model.enemy.ai.EasyEnemy;
import tictactoe.model.enemy.ai.HardEnemy;
import tictactoe.model.enemy.ai.MediumEnemy;
import tictactoe.view.ConsoleDrawable;
import tictactoe.view.Drawable;

import java.util.Scanner;

public class Main {
    public static final int SIZE = 3;
    private static final String WHITESPACE = " ";
    private static final String EXIT = "exit";
    private static final String START = "start";

    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        String command;
        String[] param;
        boolean isNotExit = true;

        while (isNotExit) {
            System.out.print("Input command: ");
            command = in.nextLine();
            param = command.split(WHITESPACE);

            switch (param[0]) {
                case START:

                    if (param.length != 3) {
                        System.out.println("Bad parameters!");
                        break;
                    }

                    try {
                        Field field = new Field(SIZE);
                        Enemy first = getEnemy(param[1], field, Field.X);
                        Enemy second = getEnemy(param[2], field, Field.O);
                        Drawable drawable = new ConsoleDrawable();
                        Game game = new Game(field, first, second, drawable);
                        game.start();
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;

                case EXIT:
                    isNotExit = false;
                    break;

                default:
                    System.out.println("Bad parameters!");
            }

        }

    }

    private static Enemy getEnemy(String input, Field field, char who) {

        switch (input) {
            case "easy":
                return new EasyEnemy(field, who);

            case "medium":
                return new MediumEnemy(field, who);

            case "hard":
                return new HardEnemy(field, who);

            case "user":
                return new User(in, field, who);

            default:
                throw new IllegalArgumentException("Bad parameters!");
        }
    }

}
