import java.util.List;

public class Printer {
    public enum Response {
        INTRO,
        GOODBYE,
        CONFUSED,
        REJECT
    }

    private final String name;

    public Printer(String name) {
        this.name = name;
    }

    /**
     * Formats and prints multiple lines of response.
     * @param lines List of response lines.
     */
    public void printResponse(List<String> lines) {
        System.out.printf("| %s:%n", name);
        for (String line : lines) {
            System.out.println("| \t" + line);
        }
        this.printUserCaret();
    }

    /**
     * Formats and prints a single line of response.
     * @param line Response line.
     */
    public void printResponse(String line) {
        this.printResponse(List.of(line));
    }

    /**
     * Prints one of the predefined responses.
     * @param response Type of response.
     */
    public void printStandardResponse(Response response) {
        switch (response) {
            case INTRO:
                printResponse(String.format("Hello, I'm %s, how may I help you?", this.name));
                break;
            case GOODBYE:
                printResponse("Goodbye, and see you again!");
                break;
            case CONFUSED:
                printResponse("I don't quite understand, please try again.");
                break;
            case REJECT:
                printResponse("Sorry, I can't handle that right now.");
                break;
            default:
                printResponse("I'm most definitely a teapot.");
        }
    }

    /**
     * Prints an error response.
     * @param message Error message.
     */
    public void printErrorResponse(String message) {
        printResponse(String.format("I've a problem! %s", message));
    }

    public void printUserCaret() {
        System.out.print("> ");
    }
}
