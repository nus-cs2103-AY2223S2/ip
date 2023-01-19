import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greet();
        run();
    }

    public static void run() {
        DukeList records = new DukeList();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            handleInput(input, records);
        }
    }

    public static void handleInput(String input, DukeList d) {
        switch(input) {
            case "bye":
                exit();
                break;
            case "list":
                System.out.println(d.toString());
                break;
            default:
                d.insert(input);
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
        System.exit(1);
    }
}
