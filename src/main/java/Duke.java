import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private static final ArrayList<String> LIST = new ArrayList<>();

    /**
     * @param txt text to indent.
     * @return indented string.
     */
    private static String autoIndent(String txt) {
        return "    " + txt.replace("\n", "\n    ");
    }

    /**
     * prints aesthetic line.
     */
    private static void aestheticLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    /**
     * Prints output in a nice format. (adds borders and indentation).
     * @param inp input string.
     */
    private static void prettifyOut(String inp) {
        aestheticLine();
        System.out.println(autoIndent(inp));
        aestheticLine();
    }

    /**
     * Checks if string is terminating string.
     * @param inp input string.
     * @return True if terminating string, false otherwise.
     */
    private static boolean checkEnd(String inp) {
        return Objects.equals(inp.toLowerCase(), "bye");
    }

    /**
     * Checks if string is command for list.
     * @param inp input string.
     * @return True if command for list, false otherwise.
     */
    private static boolean checkList(String inp) {
        return Objects.equals(inp.toLowerCase(), "list");
    }

    /**
     * Prints list of tasks.
     */
    private static void printList() {
        if (LIST.isEmpty()) prettifyOut("Your list is empty!");
        else {
            int count = 1;
            for (String item : LIST) {
                System.out.println(autoIndent(count + ". " + item));
                count++;
            }

        }
    }

    /**
     * The main function.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        prettifyOut(GREETING);

        Scanner input = new Scanner(System.in);
        while (true) {
            String userInput = input.nextLine();
            if (userInput.isBlank()) {
                aestheticLine();
                continue;
            }
            if (checkEnd(userInput)) break;

            aestheticLine();
            if (checkList(userInput)) {
                printList();
            } else {
                LIST.add(userInput);
                System.out.println(autoIndent("added: " + userInput));
            }
            aestheticLine();
        }

        prettifyOut(GOODBYE);
    }
}
