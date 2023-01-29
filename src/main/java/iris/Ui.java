package iris;

import java.util.Scanner;

/**
 * The user interface: takes care og getting input and showing output to the user
 */
public class Ui {
    private static final String GREETING = "Welcome to Lavender Network!\n"
            + "I'm Iris, your favourite teenage chatbot.\n"
            + "I'm here to keep track of your tasks so you don't have to :)\n"
            + "Type \"help\" to see the commands.\n"
            + "What are you waiting for? Let's get started!";
    private final Scanner inputGetter;

    public Ui() {
        this.inputGetter = new Scanner(System.in);
    }


    /**
     * outputs a String to the user
     * @param out the String to be shown
     */
    public static void output(String out) {
        System.out.println("\033[35m" + out);
    }

    /**
     * gets input from the user
     * @return the input
     */
    public String readInput() {
        return inputGetter.nextLine();
    }

    /**
     * initial greeting to the user
     */
    public void greet() {
        output(GREETING);
    }
}
