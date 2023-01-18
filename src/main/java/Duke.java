import java.util.Scanner;
public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo);
        System.out.println(greeting);
    }

    private static void exit() {
        String exitMessage = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(exitMessage);
    }

    private static void processCommand(String command) {
        System.out.println(command + "\n");
    }

    public static void start() {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Duke.exit();
                break;
            }
            processCommand(input);
        }
    }

    public static void main(String[] args) {
        start();
    }
}
