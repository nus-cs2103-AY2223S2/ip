package jarvis;

import java.util.List;

public class Ui {
    private static final String LOGO = "     _   _    ______     _____ ____  \n" +
            "    | | / \\  |  _ \\ \\   / /_ _/ ___| \n" +
            " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\ \n" +
            "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n" +
            " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    public enum Response {
        INTRO,
        GOODBYE,
        CONFUSED,
        REJECT
    }

    private final String name;

    public Ui(String name) {
        this.name = name;
    }

    /**
     * Formats and prints multiple lines of response.
     * @param lines List of response lines.
     */
    public void print(List<String> lines) {
        System.out.printf("| %s:%n", name);
        for (String line : lines) {
            System.out.println("| \t" + line);
        }
        this.printUserPrompt();
    }

    /**
     * Formats and prints a single line of response.
     * @param line Response line.
     */
    public void print(String line) {
        this.print(List.of(line));
    }

    /**
     * Prints one of the predefined responses.
     * @param response Type of response.
     */
    public void printStandard(Response response) {
        switch (response) {
        case INTRO:
            print(String.format("Hello, I'm %s, how may I help you?", this.name));
            break;
        case GOODBYE:
            print("Goodbye, and see you again!");
            break;
        case CONFUSED:
            print("I don't quite understand, please try again.");
            break;
        case REJECT:
            print("Sorry, I can't handle that right now.");
            break;
        default:
            print("I'm most definitely a teapot.");
        }
    }

    /**
     * Prints an error response.
     * @param message Error message.
     */
    public void printError(String message) {
        print(String.format("I've a problem! %s", message));
    }

    public void printUserPrompt() {
        System.out.print("> ");
    }

    public void printLogo() {
        System.out.println(LOGO);
    }
}
