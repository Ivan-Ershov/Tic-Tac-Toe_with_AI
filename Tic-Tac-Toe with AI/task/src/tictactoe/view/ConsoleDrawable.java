package tictactoe.view;

import tictactoe.model.Field;

public class ConsoleDrawable implements Drawable{
    private static final String HORIZONTAL_LINE = "-";
    private static final String START_LINE = "| ";
    private static final String END_LINE = " |";
    private static final String WHITESPACE = " ";

    @Override
    public void draw(Field field) {
        int weight = field.getSize() * 2 + 3;

        printHorizontalLine(weight);
        printField(field);
        printHorizontalLine(weight);
    }

    private void printHorizontalLine(int length) {
        System.out.println(HORIZONTAL_LINE.repeat(length));
    }

    private void printField(Field field) {
        for (int vertical = 0; vertical < field.getSize(); vertical++) {
            printStartLine();
            printLine(field.getField()[vertical]);
            printEndLine();
        }
    }

    private void printStartLine() {
        System.out.print(START_LINE);
    }

    private void printEndLine() {
        System.out.println(END_LINE);
    }

    private void printLine(char[] input) {

        for (int i = 0; i < (input.length - 1); i++) {
            System.out.print(input[i] + WHITESPACE);
        }

        System.out.print(input[input.length - 1]);
    }
}
