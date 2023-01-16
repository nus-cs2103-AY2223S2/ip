import java.util.Scanner;

/**
 * A baby chatbot that can only repeat the input back to the user
 *
 * @author lavanya
 * @version 1.0
 */
public class Duke {
    private static final Scanner inputGetter = new Scanner(System.in);

    private static String input = "";

    private static final String greeting = "Welcome to Lavender Network! \n" +
            "I'm Iris, your baby chatbot. Right now, I'm learning how to talk! \n" +
            "To teach me, type a word or phrase and press enter. \n" +
            "I'll repeat it back to you.\n" +
            "To stop teaching, type \"bye\"." +
            "\nTeach me a lot so I can grow quickly!";

    private static final String exitGreeting = "Bye teacher! Hope to see you soon!";

    /**
     * This method gets input from the user and stores it in the input field
     */
    private static void getInput() {
        input = inputGetter.nextLine();
    }

    /**
     * This method prints out the string out in the terminal
     * @param out the string to be displayed
     */
    private static void output(String out) {
        System.out.println("\033[35m" + out);
    }

    public static void main(String[] args) {
        output(greeting);
        getInput();
        while (!input.equals("bye")) {
            output(input);
            getInput();
        }
        output(exitGreeting);
    }
}
