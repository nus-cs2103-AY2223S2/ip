import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class implements a personal assistant chatbot
 * that helps a person keep track of various things.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String INDENT = "    ";
    private static final String HORIZONTAL_LINE = "______________________________";
    private static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static ArrayList<String> tasks = new ArrayList<>(100);

    private void fixedResponse(String text) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        String[] lines = text.split("\n");
        for (String s : lines) {
            System.out.println(INDENT + s);
        }
        System.out.println(INDENT + HORIZONTAL_LINE + "\n");
    }

    private void greet() {
        fixedResponse("Hello I'm\n" + LOGO + "What can I do for you?");
    }

    private void exit() {
        fixedResponse("Bye. Hope to see you again soon!");
    }

    private void add(String s) {
        fixedResponse("added: " + s);
    }

    private void list() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(INDENT + HORIZONTAL_LINE + "\n");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                duke.list();
            } else {
                tasks.add(input);
                duke.add(input);
            }
            input = scanner.nextLine();
        }
        duke.exit();
        scanner.close();
    }
}
