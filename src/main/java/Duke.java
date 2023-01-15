import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    static String NAME = "Uncle Roger";
    static String HORIZONTAL = "+=".repeat(20);
    static String INDENT = "> ";
    static String ENDWORD = "bye";

    public static void main(String[] args) {
        String greeting = "Hallo Hallo! My name is %s.";
        Duke.greet(greeting);

        String question = "What you want?";
        Duke.say(question, false);

        Scanner scanner = new Scanner(System.in);

        Duke.start(scanner, Duke.ENDWORD);
    }

    public static void greet(String greeting) {
        /**
         * @param greeting what needs to be said, with placeholder for the name.
         */
        System.out.println(String.format(greeting, Duke.NAME));
    }

    public static void say(String sentence, boolean addLine) {
        /**
         * @param sentence what needs to be said.
         * @param addLine whether to add a horizontal line.
         * @return void
         */
        System.out.println(Duke.INDENT + sentence);

        if (addLine) {
            System.out.println(Duke.HORIZONTAL);
        }
    }

    public static void start(Scanner scanner, String endWord) {
        /**
         * @param scanner the scanner object already created.
         * @param endWord the trigger word to end the loop.
         * @returns void
         */
        String response = "";
        while (true) {
            response = scanner.nextLine();
            if (response.equals(endWord)) {
                break;
            }
            
            Duke.say(response, true);
        }

        Duke.say("Bye Bye. HAND.", true);
    }
}
