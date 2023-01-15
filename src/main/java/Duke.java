import java.util.Scanner;

public class Duke {

    private static Scanner scanner = new Scanner(System.in);
    private static final String GREETING = "Hello, I am Duke.\n" +
            "What can I do for you?";
    private static final String EXIT_COMMAND = "bye";
    private static final String BYE = "Goodbye. I hope to see you again.";
    private static final String PROMPT_SYMBOL = ">";

    private static String formatMessage(String message) {
        return "___________________________\n" +
                message +
                "\n___________________________";
    }

    private static void printMessage(String message) {
        System.out.println(formatMessage(message));
    }

    private static String getInputFromUser() {
        return scanner.nextLine();
    }

    private static void printPrompt() {
        System.out.print(PROMPT_SYMBOL);
    }

    private static void greet() {
        printMessage(GREETING);
    }

    private static void acceptCommands() {
        String input;
        while (true) {
            printPrompt();
            input = getInputFromUser();
            if (input.equals(EXIT_COMMAND)) {
                return;
            }
        }
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
