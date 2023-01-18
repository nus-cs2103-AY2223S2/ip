import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void printLine() {
        System.out.printf("%64s%n", "    ____________________________________________________________");
    }

    private static void greet() {
        printLine();
        System.out.printf("    %s%n", "Hello! I'm Duke");
        System.out.printf("    %s%n", "What can I do for you?");
        printLine();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printLine();

            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("    %d. %s%n", i + 1, tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.printf("    added: %s%n", input);
            }

            printLine();
            input = sc.nextLine();
        }
        printLine();
        System.out.printf("    %s%n", "Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}