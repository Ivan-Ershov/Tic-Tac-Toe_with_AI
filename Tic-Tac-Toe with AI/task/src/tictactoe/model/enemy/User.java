package tictactoe.model.enemy;

import tictactoe.model.Field;

import java.util.Scanner;

public class User extends Enemy{

    private final Scanner scanner;

    public User(Scanner scanner, Field field, char who) {
        super(field, who);
        this.scanner = scanner;
    }

    @Override
    public void nextStep() {
        System.out.print("Enter the coordinates: ");
        String input = scanner.nextLine();
        action.action(input);
    }
}
