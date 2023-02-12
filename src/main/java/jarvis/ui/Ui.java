package jarvis.ui;

import java.util.LinkedList;
import java.util.List;

/**
 * Ui class to present a text-based interface.
 */
public class Ui {
    private static final String LOGO = "     _   _    ______     _____ ____  \n"
            + "    | | / \\  |  _ \\ \\   / /_ _/ ___| \n"
            + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\ \n"
            + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
            + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    /**
     * Types of standard responses.
     */
    public enum Response {
        INTRO,
        GOODBYE,
        CONFUSED,
        REJECT
    }

    private final String name;
    private final List<String> responses;
    private boolean isErrorPrinted;

    /**
     * Constructor for the Ui class.
     *
     * @param name Name of the bot.
     */
    public Ui(String name) {
        assert name != null && !name.isBlank();
        this.name = name;
        this.responses = new LinkedList<>();
        this.isErrorPrinted = false;
    }

    /**
     * Dumps the accumulated responses.
     *
     * @return String dump.
     */
    public String dumpResponses() {
        String dump = String.join("\n", this.responses);
        this.responses.clear();
        return dump;
    }

    /**
     * Sets the 'isErrorPrinted' flag and returns the previous value.
     *
     * @param flag Whether an error was printed.
     * @return The previous value.
     */
    public boolean setErrorFlag(boolean flag) {
        boolean isPrinted = isErrorPrinted;
        isErrorPrinted = flag;
        return isPrinted;
    }

    /**
     * Formats and prints the given lines of response.
     *
     * @param lines Array of response lines.
     */
    public void print(String ...lines) {
        assert lines != null;
        this.print(List.of(lines));
    }

    /**
     * Formats and prints the given lines of response.
     *
     * @param lines List of response lines.
     */
    public void print(List<String> lines) {
        assert lines != null;
        // Print bot name
        System.out.printf("| %s:%n", name);
        // Print lines
        for (String line : lines) {
            System.out.println("| \t" + line);
        }
        this.printUserPrompt();

        this.responses.addAll(lines);
    }

    /**
     * Prints one of the predefined responses.
     *
     * @param response Type of response.
     */
    public void printStandard(Response response) {
        assert response != null;
        String standard;
        switch (response) {
        case INTRO:
            standard = String.format("Hello, I'm %s, how may I help you?", this.name);
            break;
        case GOODBYE:
            standard = "Goodbye, and see you again!";
            break;
        case CONFUSED:
            standard = "I don't quite understand, please try again.";
            isErrorPrinted = true;
            break;
        case REJECT:
            standard = "Sorry, I can't handle that right now.";
            isErrorPrinted = true;
            break;
        default:
            standard = "I'm most definitely a teapot.";
        }
        print(standard);
    }

    /**
     * Prints an error response.
     *
     * @param message Error message.
     */
    public void printError(String message) {
        isErrorPrinted = true;
        print(String.format("I've a problem! %s", message));
    }

    /**
     * Prints an arrow to prompt for user input.
     */
    public void printUserPrompt() {
        System.out.print("> ");
    }

    /**
     * Prints the bot logo.
     */
    public void printLogo() {
        System.out.println(LOGO);
    }
}
