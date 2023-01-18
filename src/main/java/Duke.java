import java.util.Scanner;

public class Duke {
    static String HORIZONTAL = "+=".repeat(20);
    static String INDENT = "> ";
    static String ENDWORD = "bye";

    public static void main(String[] args) {
        Duke.indent();
        System.out.println("Hallo Hallo niece and nephew! My name is Uncle Roger.");
        System.out.println("What you want?");

        Scanner scanner = new Scanner(System.in);
        Duke.start(scanner, Duke.ENDWORD);
    }

    public static void putHorizontal() {
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

    public static void start(Scanner scanner, String endWord) {
        /**
         * @param scanner the scanner object already created.
         * @param endWord the trigger word to end the loop.
         * @returns void
         */
        String response = "";
        TaskList taskList = new TaskList();
        while (true) {
            response = scanner.nextLine();
            if (response.equals(endWord)) {
                break;
            }
            Duke.indent();
            Parser.parseResponse(response, taskList);
            Duke.putHorizontal();
        }
        System.out.println("Bye Bye. Leave good review please! PLEAASEEE!");
        Duke.putHorizontal();
    }
}
