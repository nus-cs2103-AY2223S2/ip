import java.util.Scanner;

public class Duke {

    private static Scanner scanner = new Scanner(System.in);
    private static final String GREETING = "Hello, I am Duke.\n" +
            "What can I do for you?";
    private static final String EXIT_COMMAND = "bye";
    private static final String BYE = "Goodbye. I hope to see you again.";
    private static final String PROMPT_SYMBOL = ">";
    private static final String FORMAT_LINE = "___________________________";

    private static String formatMessage(String message) {
        return FORMAT_LINE + "\n" +
                message + "\n" +
                FORMAT_LINE;
    }

    private static void printMessage(String message) {
        System.out.println(formatMessage(message));
    }

    private static String getInputFromUser() {
        return scanner.nextLine();
    }

    private static void printPromptForInput() {
        System.out.print(PROMPT_SYMBOL);
    }

    private static void greet() {
        printMessage(GREETING);
    }

    private static boolean isExitCommand(String input) {
        return input.equals(EXIT_COMMAND);
    }

    // Loop for user input
    private static void acceptCommands() {
        String input;
        while (true) {
            printPromptForInput();
            input = getInputFromUser();
            if (isExitCommand(input)) {
                return;
            }
            executeOneCommand(input);
        }
    }

    // Executes a command, except exit command
    private static void executeOneCommand(String input) {
        printMessage(input);
    }

    private static void sayGoodbye() {
        printMessage(BYE);
    }

    public static void main(String[] args) {
        greet();
        acceptCommands();
        sayGoodbye();
    }
}
