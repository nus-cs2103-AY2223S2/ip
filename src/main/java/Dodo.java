import java.util.Scanner;
public class Dodo {
    private static final String DIVIDER = "________________________________\n";
    private static final String LOGO =
              " ____  _   _ ____  _   _ \n"
            + "|  _ \\| | | |  _ \\| | | |\n"
            + "| | | | | | | | | | | | |\n"
            + "| |_| | |_| | |_| | |_| |\n"
            + "|____/ \\___/|____/ \\___/\n";
    private static final String GREETING = DIVIDER + "Hello from\n" + LOGO + "What can I do for you?\n" + DIVIDER;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("bye")) {
                System.out.println(DIVIDER);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(DIVIDER);
                break;
            }
            System.out.println(DIVIDER);
            System.out.println(input);
            System.out.println(DIVIDER);
        }
    }

}
