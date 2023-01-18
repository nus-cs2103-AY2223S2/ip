
import java.util.Scanner;
public class Bob {
    private static Scanner scanner = new Scanner(System.in);
    private static void echo() {
        String flag = "bye";
        String input = scanner.nextLine();

        while (!input.equals(flag)) {
            formattedPrint(input);
            input = scanner.nextLine();
        }

        // Goodbye message
        formattedPrint("See you soon!");
    }

    private static String padLeft (String s, Integer p) {
        return " ".repeat(p) + s;
    }
    private static void formattedPrint(String s) {
        Integer spacing = 5;
        String wrapper = padLeft("~".repeat(20), 5);
        System.out.println(wrapper);
        System.out.println(padLeft(s, 5));
        System.out.println(wrapper);
    }

    /**
     * Runs a simple chat-bot that echoes user inputs until "bye" is entered
     * @param args
     */
    public static void main(String[] args) {
        // Introduction
        formattedPrint("Hi! I'm Bob!");
        formattedPrint("How can I help you?");
        echo();
    }
}
