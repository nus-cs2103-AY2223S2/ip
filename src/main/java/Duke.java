import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greet();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            if ("bye".equalsIgnoreCase(input)) {
                exit();
                break;
            }
            System.out.println(format(input));
        }
    }

    public static String format(String input) {
        return "    ____________________________________________________________\n" +
                "    " +
                input +
                "\n    ____________________________________________________________";
    }

    public static void greet() {
        String greeting = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);
    }

    public static void exit() {
        String exitMsg = format("Bye. Hope to see you again soon!");
        System.out.println(exitMsg);
    }
}
