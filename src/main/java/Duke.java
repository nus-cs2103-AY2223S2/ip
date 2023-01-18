import java.util.Scanner;

public class Duke {

    private static final String BAR =
            "    ____________________________________________________________";

    private static final String INDENTATION = "     ";
    private static final String NEW_LINE = "\n";

    private static void greet() {
        System.out.println(BAR);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(BAR);
        System.out.print(NEW_LINE);
    }

    private static void echo(String textToPrint) {
        System.out.println(BAR);
        System.out.println(INDENTATION + textToPrint);
        System.out.println(BAR);
        System.out.print(NEW_LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                echo("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else {
                echo(input);
            }
        }
    }
}
