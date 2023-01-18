import java.util.Scanner;

public class Duke {

    private static final String LINE = "------------------------------------------------------------";

    private static final String[] TASKS = new String[100];
    private static int taskIndex = 0;

    private static void listTasks() {
        if (taskIndex == 0) {
            System.out.println("You do not have anything added to the list.");
        } else {
            System.out.println("Listing all...");
            for (int i = 0; i < taskIndex; i++) {
                System.out.println((i + 1) + ") " + TASKS[i]);
            }
        }
    }

    private static void processCommand(String cmd) {

        if (cmd.equals("list")) {
            listTasks();
        } else {
            TASKS[taskIndex] = cmd;
            taskIndex++;

            System.out.println("I have added '" + cmd + "' to the list.");
        }

        System.out.println("\n" + LINE + "\n");
    }

    private static void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You:\n");
            String cmd = sc.nextLine().toLowerCase();

            System.out.println("\nDuke:");

            if (cmd.equals("bye")) {
                System.out.println("Bye. Have a nice day!\n");
                System.out.println(LINE);
                break;

            } else {
                processCommand(cmd);
            }

        }

        sc.close();

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println("How can I help you?\n");
        System.out.println(LINE + "\n");

        start();

    }
}
