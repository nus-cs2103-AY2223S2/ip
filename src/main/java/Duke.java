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
         * Prints a greeting which includes the Duke's name.
         * @param greeting what needs to be said, with placeholder for the name.
         */
        System.out.println(String.format(greeting, Duke.NAME));
    }

    public static void horizontal() {
        /**
         * Prints a horizontal line.
         * @returns void
         */
        System.out.println(Duke.HORIZONTAL);
    }

    public static void indent() {
        /**
         * Indents the next output.
         * @returns void
         */
        System.out.print(Duke.INDENT);
    }

    public static void say(String sentence, boolean addLine) {
        /**
         * @param sentence what needs to be said.
         * @param addLine whether to add a horizontal line.
         * @returns void
         */
        Duke.indent();
        System.out.println(sentence);

        if (addLine) {
            Duke.horizontal();
        }
    }

    public static void start(Scanner scanner, String endWord) {
        /**
         * @param scanner the scanner object already created.
         * @param endWord the trigger word to end the loop.
         * @returns void
         */
        String response = "";
        MemoPad memoPad = new MemoPad();
        while (true) {
            response = scanner.nextLine();
            if (response.equals(endWord)) {
                break;
            }

            Duke.indent();

            String firstWord = (response + " ").split(" ", 2)[0];
            try {
                switch (firstWord) {
                    case "list":
                        memoPad.listItems();
                        break;
                    case "delete":
                        memoPad.deleteItem(response);
                        break;
                    case "mark":
                        memoPad.markItem(response, true);
                        break;
                    case "unmark":
                        memoPad.markItem(response, false);
                        break;
                    case "todo":
                        memoPad.addItem('T', response);
                        break;
                    case "deadline":
                        memoPad.addItem('D', response);
                        break;
                    case "event":
                        memoPad.addItem('E', response);
                        break;
                    default:
                        System.out.println("Sorry, I'm not sure what you're talking about.");
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }

            Duke.horizontal();
        }

        Duke.say("Bye Bye. HAND.", true);
    }
}
