package ui;

import java.util.Objects;
import java.util.Scanner;

/**
 * The text UI interface that processes user input and prints outputs
 */
public class TextUi {
    protected final String name;
    protected final Scanner scanner;

    public TextUi(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        printStructuredString(String.format("Hello! I'm %s\nWhat can I do for you?", name));
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Print the string in a pre-specified format
     *
     * @param string: the string content to print out
     */
    public void printStructuredString(String string) {
        String longLine = "____________________________________________________________";
        System.out.println(longLine + "\n" + string + "\n" + longLine);
    }

    /**
     * Returns the end message and do final clean-up
     *
     */
    public void sayGoodbye() {
        printStructuredString("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if the command marks the end
     *
     * @param string: User-input string
     * @return if the string marks the end of the conversation
     */
    public boolean isEnd(String string) {
        return Objects.equals(string.toLowerCase(), "bye");
    }
}
