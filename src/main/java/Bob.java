
import java.util.Scanner;
import java.util.ArrayList;
public class Bob {
    private static Scanner scanner = new Scanner(System.in);

    private static Integer spacing = 5;

    private static String wrapper = padLeft("~".repeat(20));

    // Echoes
    private static void echo() {
        String flag = "bye";
        ArrayList<String> prevInputs = new ArrayList<String>();
        String input = scanner.nextLine();

        while (!input.equals(flag)) {
            if (!input.equals("list")) {
                prevInputs.add(input);
                formattedPrint("added: " + input);
            // Output list
            } else {
                formattedPrint(prevInputs);
            }
            input = scanner.nextLine();
        }

        // Goodbye message
        formattedPrint("See you soon!");
    }

    private static String padLeft (String s) {
        return " ".repeat(spacing) + s;
    }

    // Accepts a string that can be separated by \n
    private static void formattedPrint(String s) {
        System.out.println(wrapper);
        outputList(s.split("\n"));
        System.out.println(wrapper);
    }

    private static void formattedPrint(ArrayList<String> s) {
        System.out.println(wrapper);
        outputList(s);
        System.out.println(wrapper);
    }

    private static void outputList(ArrayList<String> list) {
        for (String s : list) {
            System.out.println(padLeft(s));
        }
    }

    // Overloaded method
    private static void outputList(String[] list) {
        for (String s : list) {
            System.out.println(padLeft(s));
        }
    }

    /**
     * Runs a simple chat-bot
     * @param args
     */
    public static void main(String[] args) {
        // Introduction
        formattedPrint("Hi! I'm Bob! \n " +
                        "How can I help you?");
        echo();
    }
}
