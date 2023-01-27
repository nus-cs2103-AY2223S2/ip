package src.main.java;

import java.util.Scanner;

public class Ui {
    Scanner inputGetter;

    public Ui() {
        this.inputGetter = new Scanner(System.in);
    }

    private static final String GREETING = "Welcome to Lavender Network!\n" +
            "I'm Iris, your favourite teenage chatbot.\n" +
            "I'm here to keep track of your tasks so you don't have to :)\n" +
            "Type \"help\" to see the commands.\n" +
            "What are you waiting for? Let's get started!";

    public static void output(String out) {
        System.out.println("\033[35m" + out);
    }

    public String readInput() {
        return inputGetter.nextLine();
    }

    public void greet() {
        output(GREETING);
    }
}
