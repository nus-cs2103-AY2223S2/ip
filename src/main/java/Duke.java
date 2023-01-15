import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static String formatMessage(String message) {
        return "___________________________\n" +
                message +
                "\n___________________________\n";
    }

    private static void printMessage(String message) {
        System.out.println(formatMessage(message));
    }

    private static void greet() {
        printMessage("Hello, I am Duke.\n" +
                "What can I do for you?");
    }

    public static void main(String[] args) {
        greet();
    }
}
