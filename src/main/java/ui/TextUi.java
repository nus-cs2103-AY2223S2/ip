package ui;

import java.util.Objects;
import java.util.Scanner;

/**
 * The text UI interface that processes user input and prints outputs
 */
public class TextUi {
    private static final String BYE_STRING = "bye";
    private static final String WELCOME_STRING = "Hello! I'm %s\nWhat can I do for you?";
    private static final String BYE_REPLY_STRING = "Bye. Hope to see you again soon!\n";

    protected final String name;
    protected final Scanner scanner;

    /**
     * Constructor
     *
     * @param name string name of the robot duke
     */
    public TextUi(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message when starting up
     */
    public void showWelcome() {
        printStructuredString(String.format(WELCOME_STRING, name));
    }

    /**
     * Waits for and reads user input.
     *
     * @return the string input from the user
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Print the string in a pre-specified format.
     *
     * @param string the string content to print out
     */
    public void printStructuredString(String string) {
        String longLine = "____________________________________________________________";
        System.out.println(longLine + "\n" + string + "\n" + longLine);
    }

    /**
     * Returns the end message and do final clean-up.
     * Specifically history commands are saved to a local file.
     *
     */
    public void sayGoodbye() {
        printStructuredString(BYE_REPLY_STRING);
    }

    /**
     * Checks if the command marks the end.
     *
     * @param string User-input string
     * @return if the string marks the end of the conversation
     */
    public boolean isEnd(String string) {
        return Objects.equals(string.toLowerCase(), BYE_STRING);
    }
}
