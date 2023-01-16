import java.util.ArrayList;
import java.util.Scanner;

/**
 * A teenage chatbot that can store text entered by the user and
 * display the stored text when requested in the form of a list
 *
 * @author lavanya
 * @version 1.0
 */
public class Duke {
    private static final Scanner inputGetter = new Scanner(System.in);

    private static String input = "";

    private static final ArrayList<String> items= new ArrayList<>();

    private static final String greeting = "Welcome to Lavender Network! \n" +
            "I'm Iris, your teenage chatbot. I've learnt from my seniors how to store stuff. (Yay!)\n" +
            "To store, type a word or phrase and press enter.\n" +
            "To see an inventory of everything you've stored, type \"list\".\n" +
            "To close me, type \"bye\". \n" +
            "Have fun making your inventory!";

    private static final String exitGreeting = "Bye! Hope to see you soon!";

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
        loop:
        while (!input.equals("bye")) {
            getInput();
            switch (input) {
                case "bye":
                    output(exitGreeting);
                    break loop;
                case "list":
                    for (int i = 0; i < items.size(); i++) {
                        output((i + 1) + ". " + items.get(i));
                    }
                    break;
                default:
                    items.add(input);
                    output("added: " + input);
            }
        }
    }
}
